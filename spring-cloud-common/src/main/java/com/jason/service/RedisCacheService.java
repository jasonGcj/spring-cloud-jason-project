package com.jason.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jason.utils.FastjsonUtil;
import com.jason.utils.RedisKeyUtil;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.jason.utils.RedisKeyUtil.MAP_KEY_ART_LIKED;

/**
 * @ClassName RedisCacheService
 * @Description TODO
 * @Author GCJ
 * @Date 2020/10/16 16:12
 */
@Component
public class RedisCacheService {
    private static Logger log = LoggerFactory.getLogger(RedisCacheService.class);

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
     * string
     * @param key
     * @return
     */
    public Boolean set (String key,String value){
        try {
            stringRedisTemplate.opsForValue().set(key,value);
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key, ex);
            return false;
        }
        return true;
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

    /**
     * map
     * @param key1 ,key2  ,value
     * @return
     */
    public Boolean put(String key1, String key2, String value) {
        Boolean v = false;
        try {
            stringRedisTemplate.opsForHash().put(key1,key2,value);
            v = true;
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key1, ex);
        }
        return v;
    }

    /**
     * map默认自增1
     * @param key1
     * @param key2
     * @return
     */
    public Long mapIncrBy(String key1, String key2) {
        Long v = null;
        try {
            stringRedisTemplate.opsForHash().increment(key1,key2,1);
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key1, ex);
        }
        return v;
    }
    /**
     * map
     * @param key1
     * @param key2
     * @param val
     * @return
     */
    public Long mapIncrBy(String key1, String key2,long val) {
        Long v = null;
        try {
            stringRedisTemplate.opsForHash().increment(key1,key2,val);
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key1, ex);
        }
        return v;
    }

    /**
     * map
     * @param key1 key2
     * @return
     */
    public Object mapGet (String key1,String key2){
        Object v = null;
        try {
            v = stringRedisTemplate.opsForHash().get(key1,key2);
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key1, ex);
        }
        return v;
    }

    /**
     * 删除缓存key map
     * @param key1 key2
     * @return
     */
    public void mapDel(String key1,String key2) {
        stringRedisTemplate.opsForHash().delete(key1,key2);
    }

    public HashOperations<String, Object, Object> opsHash(){
        return stringRedisTemplate.opsForHash();
    }

    /**
     * set 获取key是否存在
     * @param key1 key2
     * @return
     */
    public Boolean isMember(String key1,String key2){
        boolean v = false;
        try {
            v = stringRedisTemplate.opsForSet().isMember(key1,key2);
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key1, ex);
        }
        return v;

    }

    /**
     * set 获取key是否存在
     * @param key1 key2
     * @return
     */
    public Object setAdd(String key1,String key2){
        Object v = null;
        try {
            v = stringRedisTemplate.opsForSet().add(key1, key2);
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key1, ex);
        }
        return v;
    }

    /**
     * set 获取key是否存在
     * @param key1 key2
     * @return
     */
    public Object setRemove(String key1,String key2){
        Object v = null;
        try {
            v = stringRedisTemplate.opsForSet().remove(key1, key2);
        } catch (Exception ex) {
            log.error("取缓存异常, key = {}, ex = {}", key1, ex);
        }
        return v;
    }

}
