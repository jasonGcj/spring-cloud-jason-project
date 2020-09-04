package com.jason.article.service.impl;

import com.jason.article.mapper.ArticleMapper;
import com.jason.article.mapper.ImageMapper;
import com.jason.article.service.IImageService;
import com.jason.domain.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ImageServiceImpl
 * @Description TODO
 * @Author GCJ
 * @Date 2020/8/25 14:50
 */
@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ImageMapper imageMapper;


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 读取缓存的图片
     */
    private static final  String CACHE_INDEX_IMAGE = "CACHE_INDEX_IMAGE";

    @Override
    public ResultVo queryIndexImage() {
        ResultVo result = new ResultVo();
        List<Map<String, String>> redisList = new ArrayList<>();
        /**
         * 验证缓存是否存在
         */
        try {
            if(redisTemplate.hasKey(CACHE_INDEX_IMAGE)){
                redisList= redisTemplate.opsForList().range(CACHE_INDEX_IMAGE, 0, -1);
                if(!CollectionUtils.isEmpty(redisList)){
                    result.setData(redisList);
                }else{
                    redisList = operateReids();
                }
            }else{
                redisList = operateReids();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setOk(true);
            result.setMessage("系统异常:"+e.getMessage());
            return result;
        }
        result.setData(redisList);
        result.setCode(200);
        result.setOk(true);
        return result;
    }

    /**
     * 取出数据放入数据
     * @return
     */
    private List<Map<String, String>> operateReids(){
        List<Map<String, String>> list = imageMapper.queryIndexImage();
        if(!CollectionUtils.isEmpty(list)){
            redisTemplate.opsForList().rightPushAll(CACHE_INDEX_IMAGE,list);
        }
        return list;
    }
}
