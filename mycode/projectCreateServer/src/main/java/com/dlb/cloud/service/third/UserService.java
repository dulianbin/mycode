package com.dlb.cloud.service.third;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dlb.cloud.entity.User;

@FeignClient(name="projectOpenServer",fallback=UserServiceFallBack.class)
public interface UserService {
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
    String insertSelective(@RequestBody User record);

	@RequestMapping(value="/getUserById", method=RequestMethod.GET)
    User queryByPrimaryKey(@RequestParam(required = true, value = "id")Integer id);
    
	@GetMapping("/getUserList")
    List<User> queryList(@RequestParam(required = true, value = "id") int id);
}
