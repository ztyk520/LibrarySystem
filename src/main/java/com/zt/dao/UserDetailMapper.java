package com.zt.dao;

import com.zt.pojo.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/17 23:36
 */
@Repository
public interface UserDetailMapper {
    String selectUsername(@Param("username") String username);
    void insertUserDetail(UserDetail userDetail);
    void updatePassword(@Param("username") String username,@Param("password") String password);
    UserDetail selectUserDetailByUserName(@Param("username") String username);
//    通过用户名查询用户列表
    List<UserDetail> selecUserDetailListByUserName(@Param("username") String username);
    UserDetail selectUserDetailById(@Param("id") Integer id);
    void updateUserDetail(UserDetail userDetail);
    void addUserDetail(UserDetail userDetail);
    void deleteUseDetailById(@Param("id") Integer id);
}
