package com.jimmy.holydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jimmy.holydemo.domain.ResponseResult;
import com.jimmy.holydemo.entity.Category;

public interface CategoryService extends IService<Category> {
    //查询文章分类的接口
    ResponseResult getCategoryList();
}
