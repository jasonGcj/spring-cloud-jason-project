package com.jason.article.mapper;

import com.jason.article.domain.ArticleDto;
import com.jason.article.domain.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleMapper
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:13
 */
@Mapper
public interface ArticleMapper {
    List<ArticleVo> queryArticle();

    ArticleVo queryArticleById(@Param("id") String id);

    void addSomeOneArticle(ArticleDto dto);

    List<Map<String,String>> queryIndexImage();

}
