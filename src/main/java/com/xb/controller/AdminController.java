package com.xb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xb.domain.Admin;
import com.xb.service.AdminService;
import com.xb.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //存储预返回页面的结果对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * 跳转到管理员界面
     *
     * @return
     */
    @GetMapping("/goAdminListView")
    public String getAdminList() {
        return "adminList";
    }


    /**
     * 分页查询
     * @param page 当前页码
     * @param rows 每页几条数据
     * @param username 用户名
     * @return
     */
    @RequestMapping("/getAdminList")
    @ResponseBody
    public Map<String, Object> getAdminList(Integer page, Integer rows, String username){

        //获取查询的用户名
        Admin admin = new Admin();
        admin.setName(username);
        //设置每页的记录数
        PageHelper.startPage(page, rows);
        //根据姓名获取指定或所有管理员列表信息
        List<Admin> list = adminService.selectList(admin);
        //封装查询结果
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        //获取总记录数
        long total = pageInfo.getTotal();
        //获取当前页数据列表
        List<Admin> adminList = pageInfo.getList();
        //存储对象数据
        result.put("total", total);
        result.put("rows", adminList);

        return result;
    }


    /**
     * 添加管理员信息
     *
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    @ResponseBody
    public Map<String, Object> addAdmin(Admin admin) {

        //判断用户是否存在
        String name = admin.getName();
        //从数据库中查找该用户
        try {
            Admin username = adminService.findByName(name);

            if (username != null) {
                //用户存在
                result.put("success", false);
                result.put("msg", "用户名已存在");

                return result;
            }
            if (username == null) {
                //用户不存在，可以添加
                //调用添加方法
                int insert = adminService.insert(admin);
                if (insert > 0) {
                    result.put("success", true);
                    result.put("msg","添加成功");
                } else {
                    result.put("success", false);
                    result.put("msg", "服务器异常，请重新输入");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @description: 上传头像-原理:将头像上传到项目发布目录中,通过读取数据库中的头像路径来获取头像
     * @param: photo
     * @param: request
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostMapping("/uploadPhoto")
    @ResponseBody
    public Map<String, Object> uploadPhoto(MultipartFile photo, HttpServletRequest request) {
        //存储头像的本地目录
        final String dirPath = request.getServletContext().getRealPath("/upload/admin_portrait/");
        //存储头像的项目发布目录
        final String portraitPath = request.getServletContext().getContextPath() + "/upload/admin_portrait/";
        //返回头像的上传结果
        return UploadFile.getUploadResult(photo, dirPath, portraitPath);
    }

    /**
     * 删除指定id用户
     */

    @PostMapping("/deleteAdmin")
    @ResponseBody
    public Map<String,Object> deleteAdmin(@RequestParam(value = "ids[]",required = true) Integer ids[]){

        int id = adminService.deleteById(ids);
        if(id>0){
            result.put("success",true);
        }else {
            result.put("success",false);
        }
        return result;
    }

    /**
     * 根据id修改用户信息
     */

    @PostMapping("/editAdmin")
    @ResponseBody
    public Map<String,Object> editAdmin(Admin admin){
        //需排除用户只修改账户名以外的信息
//        Admin user = adminService.findByName(admin.getName());
//        if (user != null) {
//            if (!(admin.getId().equals(user.getId()))) {
//                result.put("success", false);
//                result.put("msg", "该用户名已存在! 请修改后重试!");
//                return result;
//            }
//        }
        //添加操作
        if (adminService.update(admin) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }
}

