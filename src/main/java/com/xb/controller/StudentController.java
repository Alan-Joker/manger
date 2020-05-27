package com.xb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xb.domain.Student;
import com.xb.service.ClazzService;
import com.xb.service.StudentService;
import com.xb.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClazzService clazzService;
    //存储预返回页面的数据对象
    private Map<String, Object> result = new HashMap<>();

    /**
     * @description: 跳转到学生信息管理页面
     * @param: modelAndView
     * @return: org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/goStudentListView")
    public ModelAndView goStudentListView(ModelAndView modelAndView) {

        //向页面发送一个存储着Clazz的List对象
        modelAndView.addObject("clazzList", clazzService.selectAll());
        modelAndView.setViewName("studentList");
        return modelAndView;
    }

    /**
     * @description: 分页查询学生信息列表:根据学生与班级名查询指定/全部学生信息列表
     * @param: page
     * @param: rows
     * @param: studentname
     * @param: clazzname
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */

    @PostMapping("/getStudentList")
    @ResponseBody
    public Map<String, Object> getStudentList(Integer page, Integer rows, String studentname, String clazzname) {

        //封装数据
        Student student=new Student();
        student.setClazz_name(clazzname);
        student.setName(studentname);

        //使用分页工具
        PageHelper.startPage(page,rows);

        //根据学生名与班级名查询
        List<Student> students = studentService.selectList(student);

        //封装查询结果
        PageInfo<Student> info=new PageInfo<>(students);

        //获取总记录数
        long total = info.getTotal();

        //获取分页数据
        List<Student> list = info.getList();

        //封装json数据返回前端
        result.put("total", total);
        result.put("rows", list);

        return result;
    }



    /**
     * @description: 添加学生信息
     * @param: student
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */

    @PostMapping("/addStudent")
    @ResponseBody
    public Map<String, Object> addStudent(Student student) {
        //判断学号是否存在
        Student sno = studentService.fingBySno(student);
        if (sno != null){
            //学号存在
            result.put("success",false);
            result.put("msg","学好已存在，请核对后重新输入");
            return result;
        }
        //学号不存在，可以添加
        int mark = studentService.insert(student);
        if (mark>0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("msg","服务器异常");
        }
        return result;
    }

    /**
     * @description: 根据id修改指定的学生信息
     * @param: student
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostMapping("/editStudent")
    @ResponseBody
    public Map<String, Object> editStudent(Student student) {
        if (studentService.update(student) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg", "添加失败! (ಥ_ಥ)服务器端发生异常!");
        }
        return result;
    }

    /**
     * @description: 根据id删除指定的学生信息
     * @param: ids
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostMapping("/deleteStudent")
    @ResponseBody
    public Map<String, Object> deleteStudent(@RequestParam(value = "ids[]", required = true) Integer[] ids) {

        if (studentService.deleteById(ids) > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
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
        final String dirPath = request.getServletContext().getRealPath("/upload/student_portrait/");
        //存储头像的项目发布目录
        final String portraitPath = request.getServletContext().getContextPath() + "/upload/student_portrait/";
        //返回头像的上传结果
        return UploadFile.getUploadResult(photo, dirPath, portraitPath);
    }
}
