package com.jimmy.holydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jimmy.holydemo.constants.SystemCanstants;
import com.jimmy.holydemo.dao.LinkDao;
import com.jimmy.holydemo.domain.ResponseResult;
import com.jimmy.holydemo.entity.Link;
import com.jimmy.holydemo.service.LinkService;
import com.jimmy.holydemo.utils.BeanCoryUtils;
import com.jimmy.holydemo.vo.LinkVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkDao,Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, SystemCanstants.LINK_STATUS_NORMAL);
        List<Link> lists = list(wrapper);
        List<LinkVo> linkVos = BeanCoryUtils.copyBeanList(lists, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }
}
