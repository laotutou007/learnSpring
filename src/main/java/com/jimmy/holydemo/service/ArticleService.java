package com.jimmy.holydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jimmy.holydemo.domain.ResponseResult;
import com.jimmy.holydemo.entity.Article;

public interface ArticleService extends IService<Article> {
    //文章列表
    ResponseResult hotArticleList();
    //分类查询文章列表
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
