package com.jimmy.holydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jimmy.holydemo.domain.ResponseResult;
import com.jimmy.holydemo.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();
}
