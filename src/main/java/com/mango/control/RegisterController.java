package com.mango.control;


import com.mango.constant.WebConstant;
import com.mango.dao.BaseDao;
import com.mango.pojo.Student;
import com.mango.service.Impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 注册接口，进行注册判断
 */
@Controller
public class RegisterController {
    @Autowired
    StudentServiceImpl service;

    @Autowired
    BaseDao baseDao;

    @GetMapping("/register")
    public String register() {
        return "register"; // 返回注册页面视图
    }
    @GetMapping("/registercheck")
    public String check(@RequestParam("username") String s_id,
                        @RequestParam("sname") String s_name,
                        @RequestParam("sclass") String s_class,
                        @RequestParam("syear") String s_year,
                        @RequestParam("smajor") String s_major,
                        @RequestParam("sphonenumber") String s_phone_number,
                        @RequestParam("password") String psw,
                        @RequestParam("password2") String psw2,
                        Model model, HttpServletRequest request) {


        Student student = service.getStudentById(s_id);
        if (student != null) {
            model.addAttribute("msg","该用户已经存在!");
            return "register";
        }else {
            if (!psw.equals(psw2)) {
                model.addAttribute("msg","两次输入的密码不一致!");
                return "register";
            }
            service.addStudent(new Student(s_id,s_name,psw,s_class,s_year,s_major,s_phone_number));
            model.addAttribute("msg","注册成功!");
            return "redirect:login";

        }
    }
}
