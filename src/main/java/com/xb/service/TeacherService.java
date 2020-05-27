package com.xb.service;

import com.xb.domain.LoginForm;
import com.xb.domain.Teacher;
import java.util.List;


public interface TeacherService {

    // TODO:  验证登录信息是否正确
    Teacher login(LoginForm loginForm);

    // TODO:  根据教师与班级名查询指定/全部教师列表信息
    List<Teacher> selectList(Teacher teacher);

    // TODO:  根据工号查询指定教师信息
    Teacher findByTno(Teacher teacher);

    // TODO:  添加教师信息
    int insert(Teacher teacher);

    // TODO:  根据id修改指定教师信息
    int update(Teacher teacher);

    // TODO:  根据id删除指定教师信息
    int deleteById(Integer[] ids);

    // TODO:  根据id修改指定教师密码
    int updatePassowrd(Teacher teacher);

}
