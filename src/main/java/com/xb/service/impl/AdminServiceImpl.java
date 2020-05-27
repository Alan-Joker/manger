package com.xb.service.impl;

import com.xb.dao.AdminDao;
import com.xb.domain.Admin;
import com.xb.domain.LoginForm;
import com.xb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao dao;

    @Override
    public Admin login(LoginForm loginForm) {

        return dao.login(loginForm);
    }

    @Override
    public Admin findByName(String name) {

        return dao.findByName(name);
    }

    @Override
    public int insert(Admin admin) {

        return dao.insert(admin);
    }

    @Override
    public List<Admin> selectList(Admin admin) {

        return dao.selectList(admin);
    }

    @Override
    public int update(Admin admin) {

        return dao.update(admin);
    }

    @Override
    public int updatePassowrd(Admin admin) {
        return 0;
    }

    @Override
    public int deleteById(Integer[] ids) {

        return dao.deleteById(ids);
    }
}
