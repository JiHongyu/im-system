package com.hongyuji.imsystem.service;

import com.hongyuji.imsystem.domain.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthService {


    @Resource
    private UserInfoService userInfoService;

    public boolean auth(long uid, String password){

        if(userInfoService.getValidUserInfo(uid, password)!=null){
            return true;
        }else{
            return false;
        }
    }

    public Long createNewUser(String nickname, String password){

        UserInfo newUser = new UserInfo();
        long uid = 1001L;
        newUser.setUid(uid);
        newUser.setNickname(nickname);
        newUser.setPassword(password);

        if(userInfoService.insertUserInfo(newUser)){
            long id = newUser.getId();
            return uid;
        }else{
            return null;
        }

    }
}
