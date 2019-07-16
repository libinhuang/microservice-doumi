package com.eureka.doumi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

//redis操作
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static RedisUtils redisUtils;

    @PostConstruct
    public void init() {
        redisUtils = this;
        redisUtils.redisTemplate = this.redisTemplate;
    }

    /**
     * 设置key有效期
     * @param key
     * @param timeout
     * @return
     */
    public static boolean expire(String key,long timeout) {
        try {
            if (timeout>0) {
                redisUtils.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static boolean hasKey(String key) {
        try {
            return redisUtils.redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key删除
     * @param keys
     */
    public static void del(String...keys) {
        if (keys!=null&&keys.length>0) {
            if (keys.length==1) {
                redisUtils.redisTemplate.delete(keys[0]);
            }else {

                redisUtils.redisTemplate.delete(Arrays.asList(keys));
            }
        }
    }

    /**
     * 根据key获取数据
     * @param key
     * @return
     */
    public static Object get(String key) {
        return key==null?null:redisUtils.redisTemplate.opsForValue().get(key);
    }

    /**
     * 保存数据
     * @param key
     * @param value
     * @return
     */
    public static boolean set(String key,Object value) {
        System.out.println("redisUtils=========>"+redisUtils);
        try {
            redisUtils.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
