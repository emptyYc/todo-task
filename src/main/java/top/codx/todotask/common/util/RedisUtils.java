package top.codx.todotask.common.util;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * redis工具类
 *
 * @author Liuch
 * @since 2023-04-03 14:22
 */
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 将实体类存入redis
     *
     * @param key  {@link String}
     * @param bean {@link T}
     */
    public <T> void setBean(String key, T bean) {
        // 将实体类转为json字符串
        String beanJson = JSONUtil.toJsonStr(bean);
        redisTemplate.opsForValue().set(key, beanJson);
    }

    /**
     * 将实体类存入redis,并设置过期时间
     *
     * @param key        {@link String} 键名
     * @param bean       {@link T} 实体类
     * @param expiration {@link int} 过期时间
     */
    public <T> void setBean(String key, T bean, int expiration) {
        // 将实体类转为json字符串
        String beanJson = JSONUtil.toJsonStr(bean);

        redisTemplate.opsForValue().set(key, beanJson, expiration, TimeUnit.SECONDS);
    }

    /**
     * 从redis取出bean
     *
     * @param key       {@link String}
     * @param beanClass {@link Class<T>}
     * @return T
     */
    public <T> T getBean(String key, Class<T> beanClass) {
        String beanJSON = redisTemplate.opsForValue().get(key);
        return JSONUtil.toBean(beanJSON, beanClass);
    }

    /**
     * 存入String
     *
     * @param key {@link String}key
     * @param s   {@link String}字符串
     */
    public void setString(String key, String s) {
        redisTemplate.opsForValue().set(key, s);
    }

    /**
     * 存入String,并设置过期时间
     *
     * @param key        {@link String}key
     * @param s          {@link String}字符串
     * @param expiration {@link Long}过期时间,单位:秒
     */
    public void setString(String key, String s, Long expiration) {
        redisTemplate.opsForValue().set(key, s, expiration, TimeUnit.SECONDS);
    }

    /**
     * 获取String
     *
     * @param key {@link String}key
     * @return {@link String}字符串
     */
    public String getString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 从redis删除key
     *
     * @param key {@link String}
     */
    public void delKey(String key) {
        redisTemplate.delete(key);
    }
}
