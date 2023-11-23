package com.zt.dao;

import com.zt.pojo.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/17 21:56
 */
@Repository
public interface UserLoginMapper {
    /** 
     * @description: 向userlogin表中添加一条记录
     * @param: userLogin 
     * @return: void 
     * @author Administrator
     * @date: 2023/11/17 23:51
     */ 
    void insertUserLogin(UserLogin userLogin);
    /**
     * @description: 通过username和password查询一条记录，记录形式为UserLogin对象
     * @param: username
password
     * @return: com.zt.pojo.UserLogin
     * @author Administrator
     * @date: 2023/11/17 23:52
     */
    UserLogin selectUserLoginByUP(@Param("username") String username,@Param("password") String password);

//    通过id查询密码
    String selectUserLoginPasswordByUsername(@Param("username") String username);
    void updatePassword(@Param("username") String username,@Param("password") String password);
    void deleteUserLoginBy(@Param("id") Integer id);
}
