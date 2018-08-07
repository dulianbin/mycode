package com.dlb.cloud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlb.cloud.entity.User;
import com.dlb.cloud.service.RedisService;
import com.dlb.cloud.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@Api(tags ="用户信息相关的api文档",hidden=false)
public class UserController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	private UserService userService;
	
	@Autowired
    private RedisService redisService ; 
	
	@ApiOperation(value = "查询所有用户信息", notes = "查询所有用户信息")
	@GetMapping(value="/getUserList",produces="application/json")
	public String getAllUser(@RequestParam Integer id) {
		redisService.set("test123", "dlb666");
		Object name=redisService.get("test123");
		System.out.println("name:"+name);
		List<User> list=userService.queryList();
		logger.info("进来了。。。。");
		JSONArray json=JSONArray.fromObject(list);
		System.out.println("请求的数据:"+json.toString());
		return json.toString();
	}
	
	@ApiOperation(value = "根据id查询用户信息", notes = "查询数据库中某个的用户信息")
	@GetMapping("/getUserById")
	public String getUserById(@RequestParam(name="id",required=true) Integer id) {
		User list=userService.queryByPrimaryKey(id);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("进来了,id:"+id);
		JSONObject json=JSONObject.fromObject(list);
		System.out.println("请求的数据:"+json.toString());
		return json.toString();
	}
	
	@ApiOperation(value = "新增用户", notes = "新增一个用户信息")
	@PostMapping("/saveUser")
	public String saveUser(@RequestBody() User user) {
		int result=userService.insertSelective(user);
		if(result>0) {
			List<User> list=userService.queryList();
			JSONArray json=JSONArray.fromObject(list);
			System.out.println("请求的数据:"+json.toString());
			return json.toString();
		}else {
			return "保存失败";
		}
		
	}
}
