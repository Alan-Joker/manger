package com.xb.service;

import com.xb.domain.Admin;
import com.xb.domain.LoginForm;

import java.util.List;

public interface AdminService {
    // TODO:  验证登录信息是否正确
    Admin login(LoginForm loginForm);

    // TODO:  根据用户名查询指定管理员信息
    Admin findByName(String name);

    // TODO: 添加管理员信息
    int insert(Admin admin);

    // TODO:  根据姓名查询指定/所有管理员信息列表
    List<Admin> selectList(Admin admin);

    // TODO:  根据id更新指定管理员信息
    int update(Admin admin);

    // TODO:  根据id修改指定用户密码
    int updatePassowrd(Admin admin);

    // TODO:  根据id删除管理员信息
    int deleteById(Integer[] ids);
}
