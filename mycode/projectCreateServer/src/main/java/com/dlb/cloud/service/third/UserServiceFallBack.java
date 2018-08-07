package com.dlb.cloud.service.third;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dlb.cloud.entity.User;

import net.sf.json.JSONObject;

@Component
public class UserServiceFallBack implements UserService{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Override
	public String insertSelective(User record) {
		logger.info("保存数据时，因为调用第三方服务失败，所以调用hystrix熔断器备用方法");
		JSONObject json=new JSONObject();
		json.put("code", 1100);
		json.put("msg", "保存失败，调用了hystrix容错方法");
		return json.toString();
	}

	@Override
	public User queryByPrimaryKey(Integer id) {
		logger.info("查询数据时，因为调用第三方服务失败，所以调用hystrix熔断器备用方法");
		User user=new User();
		return user;
	}

	@Override
	public List<User> queryList(int id) {
		logger.info("获取数据时，因为调用第三方服务失败，所以调用hystrix熔断器备用方法");
		List<User> list=new ArrayList<User>();
		return list;
	}

}
