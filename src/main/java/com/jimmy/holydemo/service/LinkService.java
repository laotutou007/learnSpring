package com.jimmy.holydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jimmy.holydemo.domain.ResponseResult;
import com.jimmy.holydemo.entity.Link;

public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}
