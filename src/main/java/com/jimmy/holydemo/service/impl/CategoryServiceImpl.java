package com.jimmy.holydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jimmy.holydemo.constants.SystemCanstants;
import com.jimmy.holydemo.dao.CategoryDao;
import com.jimmy.holydemo.domain.ResponseResult;
import com.jimmy.holydemo.entity.Article;
import com.jimmy.holydemo.entity.Category;
import com.jimmy.holydemo.service.ArticleService;
import com.jimmy.holydemo.service.CategoryService;
import com.jimmy.holydemo.utils.BeanCoryUtils;
import com.jimmy.holydemo.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Autowired
    //注入公共模块的ArticleService接口
    private ArticleService articleService;
    @Override
    //查询分类列表的核心代码
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        //要求查的是getStatus字段的值为1，注意SystemCanstants是我们写的一个常量类，用来解决字面值的书写问题
        articleWrapper.eq(Article::getStatus, SystemCanstants.ARTICLE_STATUS_NORMAL);
        //查询文章列表，条件就是上一行的articleWrapper
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重。使用stream流来处理上一行得到的文章表集合
        Set<Long> categoryIds = articleList.stream()
                //下面那行可以优化为Lambda表达式+方法引用
                .map(Article::getCategoryId)
                //把文章的分类id转换成Set集合
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        //注意SystemCanstants是我们写的一个常量类，用来解决字面值的书写问题
        categories = categories.stream()
                .filter(category -> SystemCanstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //封装成CategoryVo实体类后返回给前端，CategoryVo的作用是只返回前端需要的字段。BeanCopyUtils是我们写的工具类
        List<CategoryVO> categoryVos = BeanCoryUtils.copyBeanList(categories, CategoryVO.class);

        return ResponseResult.okResult(categoryVos);
    }
}
