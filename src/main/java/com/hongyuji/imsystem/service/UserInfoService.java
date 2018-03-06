package com.hongyuji.imsystem.service;

import com.hongyuji.imsystem.dao.UserInfoMapper;
import com.hongyuji.imsystem.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;


    public UserInfo getValidUserInfo(long uid, String password){

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(uid);
        userInfo.setPassword(password);

        return userInfoMapper.selectValidUser(userInfo);
    }

    public boolean insertUserInfo(UserInfo userInfo){
        return userInfoMapper.insert(userInfo) > 0;
    }

}
