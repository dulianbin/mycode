package com.dlb.cloud.manager;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisCacheManager  
 * @Description: Redis缓存管理器
 * @author amir.guo 
 * @date 2018年7月4日  
 *
 */
@Component
public class RedisCacheManager<K,V> implements CacheManager<K,V> {
	
	@Autowired
	private RedisTemplate<K,V> redisTemplate;

	@Override
	public boolean set(K key, V value) {
		boolean result = true;
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			result = false;
			// TODO 写日志输出，不要直接打印堆栈
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean set(K key, V value, Long expireTime) {
		boolean result = false;
		try {
			redisTemplate.opsForValue().set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public <T> T get(K key,Class<T> clz) {
		return clz.cast(redisTemplate.opsForValue().get(key));
	}

	@Override
	public void remove(Collection<K> keys) {
		redisTemplate.delete(keys);	
	}

	@Override
	public void removePattern(K pattern) {
		Set<K> keys = redisTemplate.keys(pattern);
		if (CollectionUtils.isNotEmpty(keys)) {
			this.remove(keys);
		}
	}

	@Override
	public void remove(K key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	@Override
	public boolean exists(K key) {
		return redisTemplate.hasKey(key);
	}

	
}
