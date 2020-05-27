package com.xb.service.impl;

import com.xb.dao.StudentDao;
import com.xb.domain.LoginForm;
import com.xb.domain.Student;
import com.xb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao dao;

    @Override
    public Student login(LoginForm loginForm) {
        return dao.login(loginForm);
    }

    @Override
    public List<Student> selectList(Student student) {

        return dao.selectList(student);
    }

    @Override
    public Student fingBySno(Student student) {
        return null;
    }

    @Override
    public int insert(Student student) {

        return dao.insert(student);
    }

    @Override
    public int update(Student student) {
        return dao.update(student);
    }

    @Override
    public int updatePassowrd(Student student) {
        return 0;
    }

    @Override
    public int deleteById(Integer[] ids) {
        return dao.deleteById(ids);
    }
}
