package com.xb.service.impl;

import com.xb.dao.ClazzDao;
import com.xb.domain.Clazz;
import com.xb.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("classService")
@Transactional
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDao dao;

    @Override
    public List<Clazz> selectList(Clazz clazz) {
        return null;
    }

    @Override
    public List<Clazz> selectAll() {

        return dao.selectAll();
    }

    @Override
    public int insert(Clazz clazz) {
        return 0;
    }

    @Override
    public int deleteById(Integer[] ids) {
        return 0;
    }

    @Override
    public Clazz findByName(String name) {
        return null;
    }

    @Override
    public int update(Clazz clazz) {
        return 0;
    }
}
