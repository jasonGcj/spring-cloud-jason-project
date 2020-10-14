package com.jason.article.service.impl;

import com.jason.article.domain.ArticleEntity;
import com.jason.article.dto.ArticleDto;
import com.jason.article.domain.ArticleVo;
import com.jason.article.dto.ArticleLikeDto;
import com.jason.article.mapper.ArticleMapper;
import com.jason.article.service.ArticleService;
import com.jason.domain.ResultVo;
import com.jason.enums.LikedStatusEnum;
import com.jason.utils.RedisKeyUtil;
import com.jason.utils.UUidUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:07
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFollowServiceImpl.class);

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultVo queryArticle() {
        List<ArticleEntity> list = articleMapper.queryArticle();
        return new ResultVo(true,200,"查询成功",list);


    }

    @Override
    public ResultVo queryArticleById(String id) {
        ArticleEntity articleEntity = articleMapper.queryArticleById(id);
        return new ResultVo(true,200,"查询成功",articleEntity);
    }

    @Override
    public ResultVo saveArticleInfo(ArticleDto dto) {
        ResultVo result = new ResultVo();
        dto.setCreateTime(new Date());
        dto.setId(UUidUtils.getUUid());
        try {
            articleMapper.saveArticleInfo(dto);
        } catch (Exception e) {
            LOGGER.error("文章保存失败"+e.getMessage());
            return new ResultVo(false,500,"文章保存失败");
        }
        return new ResultVo(true,200,"文章保存成功");
    }

    @Override
    public ResultVo operateArticle(ArticleLikeDto dto) {
        if(null == dto){
            return new ResultVo(false,500,"未获取到文章");
        }
        if(StringUtils.isBlank(dto.getArticleId())){
            return new ResultVo(false,500,"未获取到文章");
        }
        if(StringUtils.isBlank(dto.getAccount())){
            return new ResultVo(false,500,"用户未登录");
        }

        if(1==dto.getOpeType()){
            return articleLiked(dto);
        }else if(2==dto.getOpeType()){
            return articleCollect(dto);
        }
        return new ResultVo(false,500,"操作类型不对");
    }

    /**
     * 收藏
     * @param dto
     * @return
     */
    private ResultVo articleCollect(ArticleLikeDto dto) {
        /**
         * 收藏 / 取消收藏
         */
        Set<String> str = new HashSet<>();
        str.add(dto.getArticleId());
        if(LikedStatusEnum.COLLECT.getCode().equals(dto.getLikeStatus())){
            //stringRedisTemplate.opsForHash().put(RedisKeyUtil.MAP_KEY_ART_COLLECT,dto.getAccount());
        }else{
        }

        return null;
    }

    /**
     * 点赞
     * @param dto
     * @return
     */
    private ResultVo articleLiked(ArticleLikeDto dto) {
        String key = RedisKeyUtil.getLikedKey(dto.getArticleId(),dto.getAccount());
        /**
         * 点赞 / 取消点赞
         */
        if(LikedStatusEnum.LIKE.getCode().equals(dto.getLikeStatus())){
            Object o = stringRedisTemplate.opsForHash().get(RedisKeyUtil.MAP_KEY_ART_LIKED, key);
            if(null != o ){
                return new ResultVo(false,500,"你已经点过赞了");
            }
            stringRedisTemplate.opsForHash().put(RedisKeyUtil.MAP_KEY_ART_LIKED,key, LikedStatusEnum.LIKE.getCode());
            //统计文章的点赞数
            stringRedisTemplate.opsForHash().increment(RedisKeyUtil.MAP_KEY_ART_LIKED_COUNT,dto.getArticleId(),1);
            return new ResultVo(true,200,"点赞成功");
        }else{
            Object o = stringRedisTemplate.opsForHash().get(RedisKeyUtil.MAP_KEY_ART_LIKED, key);
            if(null == o ){
                return new ResultVo(true,200,"你还未点赞");
            }
            stringRedisTemplate.opsForHash().delete(RedisKeyUtil.MAP_KEY_ART_LIKED,key);
            //统计文章的点赞数
            stringRedisTemplate.opsForHash().increment(RedisKeyUtil.MAP_KEY_ART_LIKED_COUNT,dto.getArticleId(),-1);
            return new ResultVo(true,200,"取消点赞");
        }
    }


}
