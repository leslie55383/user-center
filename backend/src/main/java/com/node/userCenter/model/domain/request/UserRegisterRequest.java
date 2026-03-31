package com.node.userCenter.model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author leslie
 * @description: 用户注册请求类
 * @since 2026/3/27 16:31
 */
@Data
public class UserRegisterRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = 7654631990420995602L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
}

