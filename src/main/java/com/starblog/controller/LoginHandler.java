package com.starblog.controller;

import com.starblog.entry.UserEntry;
import com.starblog.service.Processor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户登录
 */
@Controller
public class LoginHandler {
    @Resource
    Processor processor;

    @RequestMapping({"/login", "/login.html"})
    public String login(
            @RequestParam(required = false, value = "username") String name,
            @RequestParam(required = false, value = "password") String password,
            Model model
    ) {
        if (processor.isEmptyOrNull(name,password)) {
            return "../login.html";
        }
        boolean b = processor.isAllowToLogin(name, password);
        System.out.println("p1");
        if (b) {
            processor.updateUserActiveTime(name);
            UserEntry user = processor.getUserByName(name);
            System.out.println("p2");
            model.addAttribute("uidInf", user.getUid());
            model.addAttribute("userNameInf", user.getName());
            model.addAttribute("passwordInf", user.getPassword());
            model.addAttribute("emailInf", user.getEmail());
            model.addAttribute("registerTimeInf", user.getRegisterTime());
            model.addAttribute("activeTimeInf", user.getActiveTime());
            model.addAttribute("instructionInf", user.getInstruction());
            System.out.println("p3");
            return "../userindex.html";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            System.out.println("p4");
            return "../login.html";
        }
    }
}
