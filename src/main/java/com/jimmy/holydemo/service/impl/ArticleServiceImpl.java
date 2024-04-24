package com.jimmy.holydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jimmy.holydemo.dao.ArticleDao;
import com.jimmy.holydemo.domain.ResponseResult;
import com.jimmy.holydemo.entity.Article;
import com.jimmy.holydemo.service.ArticleService;
import com.jimmy.holydemo.utils.BeanCoryUtils;
import com.jimmy.holydemo.vo.HotArticleVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService{
    @Override
    public ResponseResult hotArticleList(){
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //查询的不能是草稿。也就是Status字段不能是0
        queryWrapper.eq(Article::getStatus,0);
        //按照浏览量进行排序。也就是根据ViewCount字段降序排序
        queryWrapper.orderByDesc(Article::getStatus);
        //最多只能查询出来10条消息。当前显示第一页的数据，每页显示10条数据
        Page<Article> page = new Page<>(1, 10);
        page(page,queryWrapper);
        //获取最终的查询结果
        List<Article> articles = page.getRecords();
        List<HotArticleVO> articleVos = BeanCoryUtils.copyBeanList(articles, HotArticleVO.class);

        return ResponseResult.okResult(articleVos);
    }
}
