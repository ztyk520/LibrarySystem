package com.zt.controller;

import com.zt.pojo.Book;
import com.zt.pojo.UserDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/18 15:09
 */
public interface AdminController {
    String mangerBook(HttpServletRequest request,String bookname,Integer pageNum);
    void addBook(Book book, HttpServletResponse response) throws IOException;

    String editBookDisPlay(Integer id,HttpServletRequest request);

    String editBookUpdate(Book book);
    String deleteBook(Integer id);

    String manageUser(String username,Integer pageNum,HttpServletRequest request);
    String editUser(Integer id,HttpServletRequest request);
    String updateUser(UserDetail userDetail);
    String addUser(UserDetail userDetail);
    String deleteUser(Integer id);

    String manageBorrow(Integer uid,String bookname,Integer bid,Integer pageNum,HttpServletRequest request);

    String deleteBorrow(String borrowdate);
    String updatePassword();
    void updatePassword(HttpServletRequest request, HttpServletResponse response,String oldPassword, String newPassword, String confirmPassword) throws IOException;
}
