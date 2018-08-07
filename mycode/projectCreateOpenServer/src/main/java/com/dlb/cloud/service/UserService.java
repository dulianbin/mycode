package com.dlb.cloud.service;

import java.util.List;

import com.dlb.cloud.entity.User;

public interface UserService {

    int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User queryByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);
    
    List<User> queryList();
}
