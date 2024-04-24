package com.jimmy.holydemo.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCoryUtils {
    private BeanCoryUtils(){}
    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性拷贝。也就是把source的属性拷贝到这个目标对象。BeanUtils是spring提供的工具类
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){
        //不使用for循环，使用stream流进行转换
        return list.stream()
                .map(o -> copyBean(o, clazz))
                //把结果转换成集合
                .collect(Collectors.toList());
    }

}
