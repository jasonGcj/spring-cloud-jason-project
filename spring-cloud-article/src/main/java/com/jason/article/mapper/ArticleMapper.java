package com.jason.article.mapper;

import com.jason.article.domain.ArticleEntity;
import com.jason.article.dto.ArticleDto;
import com.jason.article.domain.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.HashSet;
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
    int queryArticleCount(ArticleDto dto);

    List<ArticleEntity> queryArticle(ArticleDto dto);

    ArticleEntity queryArticleById(@Param("id") String id);

    void saveArticleInfo(ArticleDto dto);

    List<Map<String,String>> queryIndexImage();

    void saveArticleRelation(Map<String,Object> map);

    int deleteArticleRelationByAccount(long account);

    void updateArticleInfo(Map<String, Object> map);

    void deleteArticleRelation(Map<String, Object> map);

    List<ArticleEntity> showArticleBrowseCount();

    List<ArticleEntity> showArticleLikedCount();

    List<ArticleEntity> showArticleOrderTimeCount();

}
