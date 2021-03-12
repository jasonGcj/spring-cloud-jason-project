package com.jason.article.mapper;

import com.jason.article.domain.ArticleEntity;
import com.jason.article.domain.ArticleLIkeEntity;
import com.jason.article.dto.ArticleDto;
import com.jason.article.domain.ArticleVo;
import com.jason.article.dto.ArticleLikeDto;
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

    /**
     * 查询文章关系表
     * @param dto
     */
    int queryArticleRelationCount(ArticleLikeDto dto);
    /**
     * 根据文章id 查询文章的 收藏 点赞 详情
     * @param id
     */
    ArticleLIkeEntity queryArticleLikeAndCollectCount(String id);
    /**
     * 根据文章id 用户账号 查询文章的 收藏 点赞 详情
     * @param
     */
    ArticleLIkeEntity queryArticleLikeAndCollectInfo(Map<String, String> map);
}
