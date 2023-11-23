package com.zt.controller.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.controller.UserController;
import com.zt.pojo.Book;
import com.zt.pojo.UserDetail;
import com.zt.pojo.UserLogin;
import com.zt.service.BookService;
import com.zt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/18 10:53
 */
@Controller
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    /**
     * @description:
     * @param: userDetail
     * @return: 视图名称
     * @author Administrator
     * @date: 2023/11/18 10:54
     */
    @RequestMapping("/register.do")
    @Override
    public String UserRegister(UserDetail userDetail) {
        boolean result = userService.addUser(userDetail);
        if(result == true) {
//            成功返回到登录页面
            return "login";
        }else{
//            失败则在原页面
            return "register";
        }
    }
    @RequestMapping("/login.do")
    @Override
    public String UserLogin(UserLogin userLogin,HttpSession session) {
//        获取账号密码获得完整的用户登录信息
        UserLogin userLoginResult = userService.userLogin(userLogin);
//        根据结果进行判断，如果返回的用户登录信息为空则说明没有此用户
        if(userLoginResult == null) {
            return "login";
        }else{
//            根据用户的身份跳转不同的页面
            String getidentity = userService.getidentity(userLoginResult);
//            下面将用户的信息放入会话域中，来模拟用户登录状态
            if(getidentity.equals("用户")){
                session.setAttribute("user", userLoginResult);
                return "redirect:/userseacher";
            }else {
                session.setAttribute("admin", userLoginResult);
                return "redirect:/managebook";
            }
        }
    }
    @RequestMapping("/userseacher")
    @Override
    public String UserSeacher(String bookname,Integer pageNum,HttpServletRequest request) {
//        通过相关业务方法查询到书本的集合，如果bookname为空就会查询所有的书本
//        查询过来的书本会根据前端传来的参数分页
        Map<String, Object> map = bookService.seachBookBybookname(bookname, pageNum);
//        通过返回业务方法返回的map集合获取该页的书本集合和总页数
        List<Book> books = (List<Book>) map.get("booklist");
        Integer pages = (Integer) map.get("pages");
        request.setAttribute("pages", pages);
//        如果是第一访问，PageNum肯定是null，说明用户正处于刚进入查询图书页面
//        显示的页码设置为第一页即可
        if(pageNum == null){
            request.setAttribute("pageNum", 1);
        }else{
//            如果不是第一次访问，就将页码原封不动放入请求域中，这一步必须要，因为请求域数据只存在于一次请求中
            request.setAttribute("pageNum", pageNum);
        }
//        将数据都放入请求域中
        request.setAttribute("booklist", books);
//        如果bookname不为空，则以bookname为标准继续分页
        if (bookname != null){
            request.setAttribute("bookname", bookname);
        }
        return "userSeacher";
    }

    @RequestMapping("/userinfo")
    @Override
    public String UserInfo(HttpServletRequest request) {
//        获取该用户的用户名
        UserLogin user = (UserLogin)request.getSession().getAttribute("user");
        String username = user.getUsername();
//        调用查询用户详情的业务方法
        UserDetail userDetail = userService.selectUserDetailByUserName(username);
        request.setAttribute("userdetail", userDetail);
        return "userInfo";
    }

    @RequestMapping("/userreturn")
    @Override
    public String UserReturn(String bookname,Integer pageNum,HttpServletRequest request) {
//        如果第一次到归还页面，需要手动设置页码为1
        if(pageNum == null){
            pageNum = 1;
        }
//        获取用户id
        UserLogin user = (UserLogin)request.getSession().getAttribute("user");
        int uid = user.getId();
//         开启分页
        PageHelper.startPage(pageNum, 5);
//        获得业务方法返回的集合
//        bookname作为检索条件,可有可无
        List<Map<String, Object>> maps = bookService.returnRecodeBook(bookname,uid, pageNum);
//        一个map集合是一条数据库记录，list就是多条记录
//        获得总页数
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(maps);
        int pages = mapPageInfo.getPages();
//        将数据共享到请求域
        request.setAttribute("list", maps);
        request.setAttribute("pages", pages);
        request.setAttribute("pageNum", pageNum);
        if (bookname != null){
            request.setAttribute("bookname", bookname);
        }
        return "userReturn";
    }

    @RequestMapping("/updateuserpassword")
    @Override
    public String UpdateUserPassword() {
        return "updateUserPassword";
    }

    @RequestMapping("/usersearcher/borrow")
    @Override
    public String bookBorrow(Integer uid, Integer bid,HttpSession session) {
//        调用业务方法
        boolean result = bookService.borrowBook(uid, bid);
//        重定向到查询图书的首页
        return "redirect:/userseacher";
    }

    @RequestMapping("/userexit")
    @Override
    public String UserExit(HttpSession session) {
        session.invalidate();
        return "login";
    }

//    处理还书请求
//    id为归还书本编号
    @RequestMapping("/returnbook")
    @Override
    public String ReturnBook(Integer uid,Integer bid){
//        调用归还图书的业务方法
        bookService.returnBook(uid, bid);
        return "redirect:/userreturn";
    }

//    更新用户密码
    @RequestMapping("/updatepassword")
    @Override
    public void UpdatePassword(HttpServletRequest request, HttpServletResponse response,String oldPassword, String newPassword, String confirmPassword) throws IOException {
//        首先获得用户名
        UserLogin user = (UserLogin)request.getSession().getAttribute("user");
        String username = user.getUsername();
//        调用更新密码业务方法
        boolean result = userService.updatePassword(username, oldPassword, newPassword, confirmPassword);
        if (result == false){
            response.getWriter().write("false");
        }
    }
}
