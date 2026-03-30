package com.node.userCenter.controller;

import com.node.userCenter.model.domain.request.UserRegisterRequest;
import com.node.userCenter.service.UserService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author leslie
 * @description:
 * @since 2026/3/27 16:21
 */
public class UserController {
    @Resource
    private UserService userService;
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest == null){
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }
}
