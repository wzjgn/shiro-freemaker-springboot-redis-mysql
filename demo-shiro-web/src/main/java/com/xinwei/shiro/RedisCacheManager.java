package com.xinwei.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.xinwei.spring.boot.autoconfigure.shiro.ShiroProperties;

/**   
* @desc 该类提供redis cache。缓存 登录失败次数和用户权限。
 * 过期时间分别对应application.yml 中的
 * retry-expire-time-redis
*  authorization-expire-time-redis
* @author wangxinwei 
* @date 2018-02-07 04:23
*/ 
public class RedisCacheManager implements CacheManager, Destroyable {  
      
    
    private RedisTemplate redisTemplate; 
    private long expireTime;
    
    @Autowired
    private ShiroProperties properties;
    
    public RedisCacheManager(RedisTemplate redisTemplateTemp){
    	redisTemplate = redisTemplateTemp;
    }
    @Override  
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {  
      
    	if(name.equals("passwordRetryCache")){
    		expireTime = properties.getRetryExpireTimeRedis();
    	}else{
    		expireTime = properties.getAuthorizationExpireTimeRedis();
    	}
        return new RedisCache<K, V>(expireTime, redisTemplate,name);// 为了简化代码的编写，此处直接new一个Cache  
    }  
    
    @Override  
    public void destroy() throws Exception {  
     
    }  

}  