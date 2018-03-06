package com.hongyuji.imsystem.web;

import com.hongyuji.imsystem.domain.Response;
import com.hongyuji.imsystem.domain.UserInfo;
import com.hongyuji.imsystem.domain.request.LoginRequest;
import com.hongyuji.imsystem.domain.request.SignUpRequest;
import com.hongyuji.imsystem.service.AuthService;
import com.hongyuji.imsystem.util.CookieUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/auth/", method = {RequestMethod.GET,RequestMethod.POST})
public class AuthController {

    @Resource
    private AuthService authService;


    @RequestMapping("/login")
    public Response login(LoginRequest request, HttpServletResponse resp){

        long uid = 0;
        try{
            uid = Long.valueOf(request.getUsername());
        }catch (Exception e){
            return Response.toFail("登录信息出错");
        }

        if(authService.auth(uid, request.getPassword())){
            CookieUtil.set(resp,
                    CookieUtil.token,
                    request.getUsername(),
                    CookieUtil.ONE_DAY
            );
            return Response.toSuccess();
        }else{
            return Response.toFail("账号密码错误");
        }

    }

    @RequestMapping("/signUp")
    public Response signUp(SignUpRequest request, HttpServletResponse resp){

        if(StringUtils.isEmpty(request.getNickname()) || StringUtils.isEmpty(request.getPassword())){
            return Response.toFail("注册信息出错");
        }

        Long uid = authService.createNewUser(request.getNickname(), request.getPassword());
        if(null != uid){
            CookieUtil.set(resp,
                    CookieUtil.token,
                    String.valueOf(uid),
                    CookieUtil.ONE_DAY
            );
            return Response.toSuccess();
        }else{
            return Response.toFail("账号密码错误");
        }

    }

}
