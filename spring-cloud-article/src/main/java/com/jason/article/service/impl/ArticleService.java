package com.jason.article.service.impl;

import com.jason.article.domain.ArticleDto;
import com.jason.domain.ResultVo;

/**
 * @ClassName ArticleService
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:07
 */
public interface ArticleService {
    ResultVo queryArticle();

    ResultVo queryArticleById(String id);

    ResultVo addSomeOneArticle(ArticleDto dto);

    ResultVo queryIndexImage();

}
