package com.xb.controller;

import com.xb.domain.Admin;
import com.xb.domain.LoginForm;
import com.xb.domain.Student;
import com.xb.domain.Teacher;
import com.xb.service.AdminService;
import com.xb.service.StudentService;
import com.xb.service.TeacherService;
import com.xb.utils.CreateVerifiCodeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/system")
public class SystemController {

    /**
     * 登录，跳转 控制器
     */

    //储存反馈给前端得信息
    private Map<String, Object> result = new HashMap<>();
    @Autowired
    private AdminService service;

    @Autowired
    private StudentService stuService;

    @Autowired
    private TeacherService teacherService;

    /**
     * @description: 跳转到用户登录页面
     * @return: java.lang.String
     */
    @GetMapping("/goLogin")
    public String goLogin() {
        return "login";
    }

    /**
     * 验证码
     * @param request
     * @param response
     */
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
        // 验证码图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        // 验证码
        String verifiCode = String.valueOf(CreateVerifiCodeImage.getVerifiCode());
        // 将验证码图片输出到登录页面
        try {
            ImageIO.write(verifiCodeImage, "PNG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 存储验证码Session
        request.getSession().setAttribute("verifiCode", verifiCode);
    }

    /**
     * 登录方法
     */

    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(LoginForm loginForm, HttpSession session){

        //1.校验验证码信息
        //1.1获取session中得验证码
        String verifiCode = (String) session.getAttribute("verifiCode");
        //1.2验证用户输入得验证码与系统生成的验证码是否相同
        if("".equals(loginForm.getVerifiCode())){
            result.put("success",false);
            result.put("msg","长时间未操作，验证码失效，请刷新页面");
            return result;
        }else if (!verifiCode.equalsIgnoreCase(loginForm.getVerifiCode())&& loginForm.getVerifiCode()==null){
            result.put("success",false);
            result.put("msg","验证码错误，请重新输入");
            return result;
        }
        //1.3删除session中存储得验证码
        session.removeAttribute("verifiCode");


        //校验用户登录信息
        switch (loginForm.getUserType()){

            //管理员身份
            case 1:
                //1.验证用户名密码是否存在
                try {
                    Admin admin = service.login(loginForm);
                    if(admin!=null){
                        //1.1用户名，密码正确
                        //1.2将用户信息储存到session
                        session.setAttribute("userInfo",admin);
                        session.setAttribute("userType",loginForm.getUserType());
                        result.put("success",true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败! 服务器端发生异常!");
                    return result;
                }
                break;
                //学生身份
            case 2:
                //1.验证用户名密码是否存在
                try {
                    Student admin = stuService.login(loginForm);
                    if(admin!=null){
                        //1.1用户名，密码正确
                        //1.2将用户信息储存到session
                        session.setAttribute("userInfo",admin);
                        session.setAttribute("userType",loginForm.getUserType());
                        result.put("success",true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败! 服务器端发生异常!");
                    return result;
                }

                break;
                //教师身份
            case 3:

                //1.验证用户名密码是否存在
                try {
                    Teacher admin = teacherService.login(loginForm);
                    if(admin!=null){
                        //1.1用户名，密码正确
                        //1.2将用户信息储存到session
                        session.setAttribute("userInfo",admin);
                        session.setAttribute("userType",loginForm.getUserType());
                        result.put("success",true);
                        return result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result.put("success", false);
                    result.put("msg", "登录失败! 服务器端发生异常!");
                    return result;
                }
                break;
        }
            //1.3用户名密码错误
            result.put("success",false);
            result.put("msg","用户名或密码错误，请重新输入");
            return result;
    }

    /**
     * 跳转到主页面
     * @return
     */
    @GetMapping("/goSystemMainView")
    public String goSystemMainView() {
        return "main";
    }

    /**
     * 安全退出,退出后跳转到登录界面
     */

    @GetMapping("/loginOut")
    public void loginOut(HttpSession session,HttpServletResponse response){

        session.removeAttribute("userInfo");
        session.removeAttribute("userType");

        //重定向到登录界面
        try {
            response.sendRedirect("../index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
