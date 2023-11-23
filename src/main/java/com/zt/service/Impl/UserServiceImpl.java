package com.zt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.Utils.StringEmpty;
import com.zt.dao.UserDetailMapper;
import com.zt.dao.UserLoginMapper;
import com.zt.pojo.UserDetail;
import com.zt.pojo.UserLogin;
import com.zt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/18 11:04
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDetailMapper userDetailMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Override
    public boolean addUser(UserDetail userDetail) {
        boolean result = false;
        String username = userDetail.getUsername();
//        判断用户名是否符合命名规则
        boolean matches = username.matches("^[a-zA-Z0-9]+$");
//        判断是否有重复用户名
        String s = userDetailMapper.selectUsername(username);
//        满足上述两种条件并且所有信息项不为空即可添加用户
        if(matches && s == null && StringEmpty.isNotExistEmpty(new String[]{userDetail.getPassword(),userDetail.getEmail(),userDetail.getBirthday(),userDetail.getName(),userDetail.getUsername(),userDetail.getSex()})){
            result = true;
            userDetailMapper.insertUserDetail(userDetail);
            userLoginMapper.insertUserLogin(new UserLogin(null, userDetail.getUsername(), userDetail.getPassword(), false));
        }
        return result;
    }

    @Override
    public UserLogin userLogin(UserLogin userLogin) {
        UserLogin userloginResult = null;
        userloginResult = userLoginMapper.selectUserLoginByUP(userLogin.getUsername(), userLogin.getPassword());
        return userloginResult;
    }

    @Override
    public String getidentity(UserLogin userLogin) {
        UserLogin userloginResult = userLoginMapper.selectUserLoginByUP(userLogin.getUsername(), userLogin.getPassword());
        if(userloginResult.isIdentity()){
            return "管理员";
        }else{
            return "用户";
        }
    }

//    更新用户密码业务方法
    @Override
    public boolean updatePassword(String username, String oldPassword, String newPassword, String confimPassword) {
//        首先判断输入密码和确认密码是否一致
        if (!newPassword.equals(confimPassword)){
            return false;
        }
//        通过id查询密码来判断旧密码是否正确
        String password = userLoginMapper.selectUserLoginPasswordByUsername(username);
        if (!password.equals(oldPassword)){
            return false;
        }
//        都符合就修改密码并返回正确
        userLoginMapper.updatePassword(username, newPassword);
        userDetailMapper.updatePassword(username, newPassword);
        return true;
    }

    @Override
    public UserDetail selectUserDetailByUserName(String username) {
//        根据传来的用户名查询UserDetail
        UserDetail userDetail = userDetailMapper.selectUserDetailByUserName(username);
        return userDetail;
    }

//    根据传输的用户名关键字查询相关用户并分页
    @Override
    public Map<String, Object> selectUserDetailListByUsername(String username,Integer pageNum) {
//        开启分页
        PageHelper.startPage(pageNum, 5);
        List<UserDetail> userDetails = userDetailMapper.selecUserDetailListByUserName(username);
//        获取分页信息
        PageInfo<UserDetail> userDetailPageInfo = new PageInfo<>(userDetails);
        Integer pages = userDetailPageInfo.getPages();
//        放入集合并返回
        Map<String, Object> map = new HashMap<>();
        map.put("userlist", userDetails);
        map.put("pages", pages);
        return map;
    }

    @Override
    public UserDetail selectUserDetailById(Integer id) {
        UserDetail userDetail = userDetailMapper.selectUserDetailById(id);
        return userDetail;
    }

    @Override
    public void updateUser(UserDetail userDetail) {
        userDetailMapper.updateUserDetail(userDetail);
    }

    @Override
    public void insertUser(UserDetail userDetail) {
        userDetailMapper.insertUserDetail(userDetail);
        userLoginMapper.insertUserLogin(new UserLogin(null, userDetail.getUsername(), userDetail.getPassword(), false));
    }

    @Override
    public void deleteUser(Integer id) {
        userLoginMapper.deleteUserLoginBy(id);
        userDetailMapper.deleteUseDetailById(id);
    }
}
