package com.zt.service;

import com.zt.pojo.UserDetail;
import com.zt.pojo.UserLogin;

import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/18 10:55
 */
public interface UserService {
    boolean addUser(UserDetail userDetail);
    UserLogin userLogin(UserLogin userLogin);
    String getidentity(UserLogin userLogin);
    boolean updatePassword(String username,String oldPassword,String newPassword,String confimPassword);
//    查询用户详情的业务方法，返回一个用户详情对象
    UserDetail selectUserDetailByUserName(String username);
//    通过用户名查询相关的用户的业务方法
    Map<String,Object> selectUserDetailListByUsername(String username,Integer pageNum);
    UserDetail selectUserDetailById(Integer id);

    void updateUser(UserDetail userDetail);
    void insertUser(UserDetail userDetail);
    void deleteUser(Integer id);
}
