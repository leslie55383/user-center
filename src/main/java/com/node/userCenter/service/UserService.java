package com.node.userCenter.service;

import com.node.userCenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2026-03-25 16:34:08
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册的业务逻辑
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return 注册成功返回用户id，注册失败返回-1
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


    /**
     * 用户登录的业务逻辑
     * @param userAccount
     * @param userPassword
     * @param request
     * @return 登录成功返回用户信息，登录失败返回null
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);
}
