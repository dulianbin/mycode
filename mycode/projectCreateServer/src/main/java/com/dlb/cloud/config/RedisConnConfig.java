package com.dlb.cloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: RedisConn  
 * @Description: redis连接配置数据  
 * @author lianbin.du  
 * @date 2018年7月4日  
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConnConfig {
	private String host;
	// prefix+参数名 对应于配置文件config.properties中的spring.redis.*信息
	private int port;
	
	private String password;

	private int timeout;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
