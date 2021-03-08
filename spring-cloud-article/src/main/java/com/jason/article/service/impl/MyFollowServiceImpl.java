package com.jason.article.service.impl;

import com.jason.article.domain.ArticleEntity;
import com.jason.article.domain.MyFollowEntity;
import com.jason.article.dto.MyFollowDto;
import com.jason.article.mapper.MyFollowMapper;
import com.jason.article.service.IMyFollowService;
import com.jason.domain.ResultVo;
import com.jason.service.RedisCacheService;
import com.jason.utils.FastjsonUtil;
import com.jason.utils.RedisKeyUtil;
import com.jason.utils.UUidUtils;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @ClassName MyFollowServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2020/9/4 14:46
 */
@Service
public class MyFollowServiceImpl implements IMyFollowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFollowServiceImpl.class);

    @Autowired
    private MyFollowMapper myFollowMapper;

    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public ResultVo queryMyFollowInfoByUserId(String userId) {
        if(StringUtils.isBlank(userId)){
            return new ResultVo(false,500,"未获取到用户");
        }
        List<ArticleEntity> list = null;
        try {
            list = myFollowMapper.queryMyFollowInfoByUserId(userId);
           /* list.stream().forEach( u->{
                String browseCount = redisCacheService.mapGet(RedisKeyUtil.ARTICLE_COUNT, u.getId()).toString();
                String likedCount = redisCacheService.mapGet(RedisKeyUtil.MAP_KEY_ART_LIKED_COUNT, u.getId()).toString();
                u.setBrowseCount(Integer.valueOf(browseCount));
                u.setLikedCount(Integer.valueOf(likedCount));
            });*/
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("系统异常，未获取到关注列表"+e.getMessage());
            return new ResultVo(false,500,"系统异常，未获取到关注列表");
        }
        return new ResultVo(true,200,"查询成功",list);
    }
}
