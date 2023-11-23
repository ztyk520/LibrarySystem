package com.zt.controller.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.controller.AdminController;
import com.zt.pojo.Book;
import com.zt.pojo.UserDetail;
import com.zt.pojo.UserLogin;
import com.zt.service.BookService;
import com.zt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/18 15:11
 */
@Controller
public class AdminControllerImpl implements AdminController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("/managebook")
    @Override
    public String mangerBook(HttpServletRequest request,String bookname,Integer pageNum) {
//        第一次查询手动设置页码
        if(pageNum == null){
            pageNum = 1;
        }
//        图书查询分页
        Map<String, Object> map = bookService.seachBookBybookname(bookname, pageNum);
//        将分页信息放入请求域
        request.setAttribute("booklist", map.get("booklist"));
        request.setAttribute("pages", map.get("pages"));
        request.setAttribute("pageNum", pageNum);
        if (bookname != null){
            request.setAttribute("bookname", bookname);
        }
        return "manageBook";
    }

    @RequestMapping("/addbook")
    @Override
    public void addBook(Book book, HttpServletResponse response) throws IOException {
//      将传入的参数传递给业务方法
        boolean result = bookService.addBook(book);
//        根据返回的结果做判断
//            从响应中返回false
        if (result == false) {
            response.getWriter().write("false");
        }
    }
//  编辑图书
    @RequestMapping("/editBook")
    @Override
    public String editBookDisPlay(Integer id,HttpServletRequest request) {
        Book book = bookService.selectBookById(id);
        request.setAttribute("book", book);
        return "editBook";
    }
//  对图书状态更新
    @RequestMapping("/updateBook")
    @Override
    public String editBookUpdate(Book book) {
        bookService.updateBook(book);
        return "redirect:/managebook";
    }

//    删除图书
    @RequestMapping("/deleteBook")
    @Override
    public String deleteBook(Integer id) {
        bookService.deleteBook(id);
        return "redirect:/managebook";
    }

    @RequestMapping("/manageuser")
    @Override
    public String manageUser(String username, Integer pageNum,HttpServletRequest request) {
//        设置默认值
        if(username == null){
            username = "";
        }
        if (pageNum == null){
            pageNum = 1;
        }
//        获取业务方法返回的数据
        Map<String, Object> map = userService.selectUserDetailListByUsername(username, pageNum);
//        拿到列表和总页数
        List<UserDetail> userDetails = (List<UserDetail>) map.get("userlist");
        Integer pages = (Integer) map.get("pages");
//        将数据共享到请求域中
        request.setAttribute("userlist", userDetails);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("pages", pages);
        if(username != null){
            request.setAttribute("username", username);
        }
//        跳转页面
        return "manageUser";
    }

    @RequestMapping("/edituserdisplay")
    @Override
    public String editUser(Integer id, HttpServletRequest request) {
        UserDetail userDetail = userService.selectUserDetailById(id);
        request.setAttribute("user", userDetail);
        return "editUser";
    }

    @RequestMapping("/updateuser")
    @Override
    public String updateUser(UserDetail userDetail) {
        userService.updateUser(userDetail);
        return "redirect:/manageuser";
    }

    @RequestMapping("/adduser")
    @Override
    public String addUser(UserDetail userDetail) {
//        设置默认密码为123456
        userDetail.setPassword("123456");
        userService.insertUser(userDetail);
        return "redirect:/manageuser";
    }

    @RequestMapping("/deleteuser")
    @Override
    public String deleteUser(Integer id) {
        userService.deleteUser(id);
        return "redirect:/manageuser";
    }

    @RequestMapping("/manageborrow")
    @Override
    public String manageBorrow(Integer uid, String bookname, Integer bid, Integer pageNum,HttpServletRequest request) {
//        如果pageNum为空，置为1
        if(pageNum == null){
            pageNum = 1;
        }
//        开启分页
        PageHelper.startPage(pageNum, 5);
        List<Map<String, Object>> maps = bookService.selectBorrowCodeByUidAndBid(uid, bookname, bid, pageNum);
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(maps);
        Integer pages = mapPageInfo.getPages();
        request.setAttribute("list", maps);
        request.setAttribute("pages", pages);
        request.setAttribute("pageNum", pageNum);
        return "manageBorrow";
    }

    @RequestMapping("/deleteborrow")
    @Override
    public String deleteBorrow(String borrowdate) {
        bookService.deleteBorrowByBorrowdate(borrowdate);
        return "redirect:/manageborrow";
    }

    @RequestMapping("/updatemanagerpassword")
    @Override
    public String updatePassword() {
        return "updateManagerPassword";
    }

    @RequestMapping("/updatepassword/manager")
    @Override
    public void updatePassword(HttpServletRequest request, HttpServletResponse response,String oldPassword, String newPassword, String confirmPassword) throws IOException {
//        首先获得用户名
        UserLogin user = (UserLogin)request.getSession().getAttribute("admin");
        String username = user.getUsername();
//        调用更新密码业务方法
        boolean result = userService.updatePassword(username, oldPassword, newPassword, confirmPassword);
        if (result == false){
            response.getWriter().write("false");
        }
    }

}
