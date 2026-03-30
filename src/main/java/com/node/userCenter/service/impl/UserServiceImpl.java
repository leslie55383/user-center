package com.node.userCenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.node.userCenter.model.domain.User;
import com.node.userCenter.service.UserService;
import com.node.userCenter.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Arrays;

/**
* @author leslie
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2026-03-25 16:34:08
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private static final byte[] SALT = "leslie".getBytes();
    private static final String USER_LOGIN_STATE = "userLoginState";
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1.校验
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        if(userAccount.length()<4){
            return -1;
        }
        if(userPassword.length()<8||checkPassword.length()<8){
            return -1;
        }

        //账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(validPattern);
        java.util.regex.Matcher matcher = pattern.matcher(userAccount);
        if(matcher.find()){
            return -1;
        }
        //密码和校验密码相同
        if(!userPassword.equals(checkPassword)){
            return -1;
        }
        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = count(queryWrapper);
        if(count>0){
            return -1;
        }
        //加密
        String encryptPassword = DigestUtils.md5DigestAsHex((Arrays.toString(SALT) +userPassword).getBytes());
        //插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = save(user);

        if(!saveResult){
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1.校验
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        if(userAccount.length()<4){
            return null;
        }
        if(userPassword.length()<8){
            return null;
        }
        //账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(validPattern);
        java.util.regex.Matcher matcher = pattern.matcher(userAccount);
        if(matcher.find()){
            return null;
        }
        //加密
        String encryptPassword = DigestUtils.md5DigestAsHex((Arrays.toString(SALT) +userPassword).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User user =  this.getOne(queryWrapper);
        if(user==null){
            return null;
        }
        request.getSession().setAttribute("USER_LOGIN_STATE",user);
        return user;
    }
}




