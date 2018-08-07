package com.dlb.cloud.manager;

import java.util.Collection;

/**
 * @ClassName: CacheManager
 * @Description: 缓存管理器
 * @author amir.guo
 * @date 2018年7月4日
 * 
 * @param <K>
 * @param <V>
 */
public interface CacheManager<K, V> {

	/**
	 * @Title: set  
	 * @Description: 写入缓存 
	 * @param key
	 * @param value    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	boolean set(final K key, V value);

	/**
	 * @Title: set  
	 * @Description: 写入缓存
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return    参数  
	 * @return boolean    返回类型  
	 * @throws
	 */
	boolean set(final K key, V value, Long expireTime);

	/**
	 * @Title: get  
	 * @Description: 读取缓存 
	 * @param key
	 * @return    参数  
	 * @return T    返回类型  
	 * @throws
	 */
	<T> T get(final K key,Class<T> clz);

	/**
	 * 批量删除对应的value
	 *
	 * @param keys
	 */
	void remove(final Collection<K> keys);

	/**
	 * @Title: removePattern  
	 * @Description: 批量删除key
	 * @param pattern    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	void removePattern(final K pattern);

	/**
	 * @Title: remove  
	 * @Description: 删除对应的value 
	 * @param key    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	void remove(final K key);

	/**
	 * @Title: exists  
	 * @Description: 判断缓存中是否有对应的value 
	 * @param key
	 * @return    参数  
	 * @return boolean    返回类型  
	 * @throws
	 */
	public boolean exists(final K key);
}
