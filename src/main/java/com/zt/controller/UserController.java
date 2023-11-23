package com.zt.controller;

import com.zt.pojo.UserDetail;
import com.zt.pojo.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/18 10:53
 */
public interface UserController {
    String UserRegister(UserDetail userDetail);
    String UserLogin(UserLogin userLogin,HttpSession session);
    String UserSeacher(String bookname,Integer pageNum,HttpServletRequest request);
    String UserInfo(HttpServletRequest request);
    String UserReturn(String bookname,Integer pageNum,HttpServletRequest request);
    String UpdateUserPassword();
    String bookBorrow(Integer uid,Integer bid,HttpSession session);
    String UserExit(HttpSession session);
    String ReturnBook(Integer uid,Integer bid);
    void UpdatePassword(HttpServletRequest request, HttpServletResponse response,String oldPassword, String newPassword, String configPassword) throws IOException;
}
