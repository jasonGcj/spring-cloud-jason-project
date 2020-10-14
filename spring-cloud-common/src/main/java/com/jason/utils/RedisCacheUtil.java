package com.jason.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisCacheUtil
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/14 9:17
 */
public class RedisCacheUtil {

    private static Logger log = LoggerFactory.getLogger(RedisCacheUtil.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 缓存key 是否存在
     * @param redisKey
     * @return
     */
    public boolean exists(String redisKey) {
        return this.stringRedisTemplate.hasKey(redisKey);
    }
    /**
     * 删除缓存key
     * @param key
     * @return
     */
    public void del(String key) {
        this.stringRedisTemplate.delete(key);
    }

    /**
     * 计数器默认自增1
     * @param key
     * @return
     */
    public Long incr(String key) {
        return this.incrBy(key, 1);
    }

    /**
     * 计数器自增
     * @param key
     * @param val
     * @return
     */
    public Long incrBy(String key, long val) {
        Long v = null;

        try {
            v = stringRedisTemplate.opsForValue().increment(key, val);
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key, ex);
        }
        return v;
    }

    /**
     * reids锁
     * @param key
     * @return
     */
    public Boolean set(String key, Strings value, int interval, TimeUnit unit) {
        Boolean v = false;
        try {
            stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value, new SerializerFeature[]{SerializerFeature.WriteClassName}), interval, unit);
            v = true;
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key, ex);
        }
        return v;
    }

    /**
     * reids锁
     * @param key
     * @return
     */
    public Object get (String key){
        Object v = null;

        try {
            v = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception ex) {
            //ex.printStackTrace();
            //remove(key);
            log.error("取缓存异常, key = {}, ex = {}", key, ex);
        }
        return v;
    }


}
