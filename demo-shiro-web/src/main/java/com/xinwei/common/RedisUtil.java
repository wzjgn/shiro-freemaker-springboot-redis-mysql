package com.xinwei.common;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisUtil<T> {

	public static <T> void set(RedisTemplate<String, T> template, String key, T value) {
		ValueOperations<String, T> vopt = template.opsForValue();
		vopt.set(key, value);
	}

	public static <T> void set(RedisTemplate<String, T> template, String key, T value, long time) {
		ValueOperations<String, T> vopt = template.opsForValue();
		vopt.set(key, value, time, TimeUnit.SECONDS);
	}

	public static <T> T get(RedisTemplate<String, T> template, String key) {
		ValueOperations<String, T> vopt = template.opsForValue();
		return vopt.get(key);
	}

	public static <T> void del(RedisTemplate<String, T> template, String key) {
		template.delete(key);
	}

	public static <T> boolean hasKey(RedisTemplate<String, T> template, String key) {
		return template.hasKey(key);
	}

	public static <T> void hset(RedisTemplate<String, T> template, String key, String field, T value) {
		HashOperations<String, String, T> hopt = template.opsForHash();
		hopt.put(key, field, value);
	}

	public static <T> T hmget(RedisTemplate<String, T> template, String key, String field) {
		HashOperations<String, String, T> hopt = template.opsForHash();
		return hopt.get(key, field);
	}

	public static <T> void hdel(RedisTemplate<String, T> template, String key, String field) {
		HashOperations<String, String, T> hopt = template.opsForHash();
		hopt.delete(key, field, field);
	}

	public static <T> boolean hexists(RedisTemplate<String, T> template, String key, String field) {
		HashOperations<String, String, T> hopt = template.opsForHash();
		return hopt.hasKey(key, field);
	}
}
