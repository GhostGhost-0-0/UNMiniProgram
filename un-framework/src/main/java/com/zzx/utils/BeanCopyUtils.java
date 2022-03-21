package com.zzx.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: UNMiniProgram
 * @Description 对象复制工具类，主要作用是封装vo
 * @Author: 那个小楠瓜
 * @create: 2022-02-23 16:33
 **/
public class BeanCopyUtils {

    private BeanCopyUtils() {}

    /**
     * 传入一个对象，将其属性拷贝赋值给目标对象并返回
     * @param source 源对象 如：User
     * @param clazz 目标对象的字节码 如：UserVo
     * @param <V> 泛型
     * @return 拷贝好的目标对象
     */
    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性拷贝
            BeanUtils.copyProperties(source, result);
            //返回结果

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 传入一个集合，遍历集合将集合中的每个对象的属性拷贝赋值给目标对象并转换成集合返回
     * @param list 源集合
     * @param clazz 目标对象
     * @param <O> 源集合的泛型
     * @param <V> 目标对象的泛型
     * @return 拷贝好的集合
     */
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz) {
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
