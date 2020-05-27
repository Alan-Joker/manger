package com.xb.dao;

import com.xb.domain.Clazz;

import java.util.List;

public interface ClazzDao {
    // TODO:  根据年级与班级名查询指定/全部班级信息列表
    List<Clazz> selectList(Clazz clazz);

    // TODO:  查询所有班级信息列表(用于在学生管理页面中获取班级信息)
    List<Clazz> selectAll();

    // TODO:  添加班级信息
    int insert(Clazz clazz);

    // TODO:  根据id删除指定班级信息
    int deleteById(Integer[] ids);

    // TODO:  根据班级名获取指定班级信息
    Clazz findByName(String name);

    // TODO:  根据id修改指定班级信息
    int update(Clazz clazz);
}
