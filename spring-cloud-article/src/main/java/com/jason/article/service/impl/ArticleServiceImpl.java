package com.jason.article.service.impl;

import com.jason.article.domain.ArticleEntity;
import com.jason.article.dto.ArticleDto;
import com.jason.article.domain.ArticleVo;
import com.jason.article.mapper.ArticleMapper;
import com.jason.article.service.ArticleService;
import com.jason.domain.ResultVo;
import com.jason.utils.UUidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:07
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResultVo queryArticle() {
        ResultVo result = new ResultVo();
        List<ArticleEntity> list = articleMapper.queryArticle();
        result.setData(list);
        return result;

    }

    @Override
    public ResultVo queryArticleById(String id) {
        ResultVo result = new ResultVo();
        ArticleVo articleVo = articleMapper.queryArticleById(id);
        result.setData(articleVo);
        return result;
    }

    @Override
    public ResultVo saveArticleInfo(ArticleDto dto) {
        ResultVo result = new ResultVo();
        dto.setCreateTime(new Date());
        dto.setId(UUidUtils.getUUid());
        try {
            articleMapper.saveArticleInfo(dto);
        } catch (Exception e) {
            result.setCode(500);
            result.setOk(false);
            result.setMessage("文章保存失败:"+e.getMessage());
           return result;
        }
        result.setCode(200);
        result.setOk(true);
        result.setMessage("文章保存成功");
        return result;
    }



}
