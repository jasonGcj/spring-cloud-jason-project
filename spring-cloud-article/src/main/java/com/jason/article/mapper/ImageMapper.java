package com.jason.article.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ImageMapper
 * @Description TODO
 * @Author GCJ
 * @Date 2020/8/25 14:52
 */
@Mapper
public interface ImageMapper {
    List<Map<String, String>> queryIndexImage();
}
