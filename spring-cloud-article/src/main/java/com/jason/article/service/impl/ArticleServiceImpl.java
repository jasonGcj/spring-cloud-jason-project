package com.jason.article.service.impl;

import com.jason.article.domain.ArticleEntity;
import com.jason.article.dto.ArticleDto;
import com.jason.article.dto.ArticleLikeDto;
import com.jason.article.mapper.ArticleMapper;
import com.jason.article.service.IArticleService;
import com.jason.domain.ResultVo;
import com.jason.enums.LikedStatusEnum;
import com.jason.service.RedisCacheService;
import com.jason.utils.FastjsonUtil;
import com.jason.utils.RedisKeyUtil;
import com.jason.utils.UUidUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.jason.utils.RedisKeyUtil.MAP_KEY_ART_LIKED;
import static com.jason.utils.RedisKeyUtil.MAP_KEY_ART_LIKED_COUNT;

/**
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2019/12/2 11:07
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFollowServiceImpl.class);

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public ResultVo queryArticle(ArticleDto dto) {
        if(0==dto.getStart()){
            dto.setStart(1);
        }
        //设置分页读取记录开始位置
        dto.setStart((dto.getStart() - 1) * dto.getLimit());
        //设置分页最大数
        dto.setLimit(dto.getLimit() == 0 ? 10 : dto.getLimit());
        int count = articleMapper.queryArticleCount(dto);
        List<ArticleEntity> list = null;
        if(count>0){
             list = articleMapper.queryArticle(dto);
        }
        return new ResultVo(true,200,"查询成功",count,list);


    }

    @Override
    public ResultVo queryArticleById(String id) {
        ArticleEntity articleEntity = articleMapper.queryArticleById(id);
        if(null !=articleEntity){
            redisCacheService.mapIncrBy(RedisKeyUtil.ARTICLE_COUNT,id);
        }
        return new ResultVo(true,200,"查询成功",articleEntity);
    }

    @Override
    public ResultVo saveArticleInfo(ArticleDto dto) {
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
            LOGGER.info("文章开始点赞");
            return articleLiked(dto);
        }else if(2==dto.getOpeType()){
            LOGGER.info("文章开始收藏");
            return articleCollect(dto);
        }
        return new ResultVo(false,500,"操作类型不对");
    }

    /**
     * 异步刷新个人收藏的文章
     * @param map
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void asyncArticleRelation(long account,Map<String, Object> map) {
        articleMapper.deleteArticleRelationByAccount(account);
        articleMapper.saveArticleRelation(map);
    }

    /**
     * 异步刷新文章点赞数量
     * @param map
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void asyncArticleInfo(Map<String, Object> map) {
        articleMapper.updateArticleInfo(map);
    }

    @Override
    public ResultVo showArticle(String flag) {
        List<ArticleEntity> list = null;
        if("LIKED_COUNT".equals(flag)){
             list = articleMapper.showArticleLikedCount();
        }else if("BROWSE_COUNT".equals(flag)){
             list = articleMapper.showArticleBrowseCount();
        }else if("ORDERTIME_COUNT".equals(flag)){
            list = articleMapper.showArticleOrderTimeCount();
        }
        return new ResultVo(true,200,"查询成功",list);
    }

    /**
     * 收藏
     * @param dto
     * @return
     */
    private ResultVo articleCollect(ArticleLikeDto dto) {
        String articleId = dto.getArticleId();
        String account = dto.getAccount();
        String key = RedisKeyUtil.MAP_KEY_ART_COLLECT +account;

        Boolean isOk = redisCacheService.isMember(key, articleId);
        /**
         * 收藏 / 取消收藏
         */
        if(LikedStatusEnum.COLLECT.getCode().equals(dto.getLikeStatus())){
            if(isOk){
                return new ResultVo(false,500,"已经收藏过了");
            }
            redisCacheService.setAdd(key,articleId);
            Map<String, Object> map = new HashMap<>();
            map.put("id",UUidUtils.getUUid());
            map.put("account",dto.getAccount());
            map.put("articleId",dto.getArticleId());
            map.put("createTime",new Date());
            articleMapper.saveArticleRelation(map);
            return new ResultVo(true,200,"收藏成功");
        }else{
            if(!isOk){
                return new ResultVo(false,500,"此文章你还未收藏");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("account",dto.getAccount());
            map.put("articleId",dto.getArticleId());
            articleMapper.deleteArticleRelation(map);
            redisCacheService.setRemove(key, articleId);
            return new ResultVo(false,500,"取消收藏成功");
        }
    }

    /**
     * 点赞
     * @param dto
     * @return
     */
    private ResultVo articleLiked(ArticleLikeDto dto) {
        String articleId = dto.getArticleId();
        String account = dto.getAccount();
        /**
         * 点赞 / 取消点赞
         */
        Boolean isOk = redisCacheService.isMember(RedisKeyUtil.MAP_KEY_ART_LIKED + articleId, account);
        if(LikedStatusEnum.LIKE.getCode().equals(dto.getLikeStatus())){
            if (isOk){
                 return new ResultVo(true,500,"你已经点过赞了");
            }
            redisCacheService.setAdd(RedisKeyUtil.MAP_KEY_ART_LIKED+articleId,account);
            redisCacheService.mapIncrBy(RedisKeyUtil.MAP_KEY_ART_LIKED_COUNT,articleId);
            return new ResultVo(true,200,"点赞成功");
        }else{
            if (!isOk){
                return new ResultVo(true,500,"你还未点赞");
            }
            redisCacheService.setRemove(RedisKeyUtil.MAP_KEY_ART_LIKED+articleId,account);
            redisCacheService.mapIncrBy(RedisKeyUtil.MAP_KEY_ART_LIKED_COUNT,articleId,-1);
            return new ResultVo(true,200,"取消点赞");
        }
    }


}
