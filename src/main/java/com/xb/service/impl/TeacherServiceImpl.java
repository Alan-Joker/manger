package com.xb.service.impl;

import com.xb.dao.TeacherDao;
import com.xb.domain.LoginForm;
import com.xb.domain.Teacher;
import com.xb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao dao;
    @Override
    public Teacher login(LoginForm loginForm) {
        return null;
    }

    @Override
    public List<Teacher> selectList(Teacher teacher) {
        return null;
    }

    @Override
    public Teacher findByTno(Teacher teacher) {
        return null;
    }

    @Override
    public int insert(Teacher teacher) {
        return 0;
    }

    @Override
    public int update(Teacher teacher) {
        return 0;
    }

    @Override
    public int deleteById(Integer[] ids) {
        return 0;
    }

    @Override
    public int updatePassowrd(Teacher teacher) {
        return 0;
    }
}
