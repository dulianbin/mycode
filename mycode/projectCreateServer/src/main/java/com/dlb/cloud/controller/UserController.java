package com.dlb.cloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlb.cloud.entity.User;
import com.dlb.cloud.manager.CacheManager;
import com.dlb.cloud.service.third.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class UserController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private CacheManager<Object, Object> cacheManager;
	
	@RequestMapping("/getUserList")
	public String getUser(@RequestParam(value="id",required=true,defaultValue="1") Integer id) {
		String aa=cacheManager.get("name", String.class);
		System.out.println("redis数据:"+aa);
		List<User> list=userService.queryList(id);
		logger.info("进来了。。。。");
		JSONArray jsonArray=JSONArray.fromObject(list);
		System.out.println("请求的数据:"+jsonArray.toString());
		JSONObject json=new JSONObject();
		json.put("code", 100);
		json.put("msg", "feign客户端加载的数据成功");
		json.put("data", jsonArray);
		return json.toString();
	}
	
	@GetMapping("/getUserById")
	public String getUserById(@RequestParam(name="id",required=true) Integer id) {
		User list=userService.queryByPrimaryKey(id);
		logger.info("进来了,id:"+id);
		JSONObject json=JSONObject.fromObject(list);
		System.out.println("请求的数据:"+json.toString());
		return json.toString();
	}
	
	@GetMapping("/saveUser")
	public String saveUser() {
		User user=new User();
		user.setEmail("dulianbin@163.com");
		user.setMobile("13088888888");
		user.setNickname("测试feign客户端");
		user.setPassword("111111");
		user.setRealName("杜某某");
		//user.setRegisterTime(new Timestamp(new Date().getTime()));
		user.setSex(1);
		String result=userService.insertSelective(user);
		return result;
	}
}
