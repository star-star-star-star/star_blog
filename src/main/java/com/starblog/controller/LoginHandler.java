package com.starblog.controller;

import com.starblog.entry.ContentEntry;
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
        if (b) {
            processor.updateUserActiveTime(name);
            UserEntry user = processor.getUserByName(name);
            model.addAttribute("uidInf", user.getUid());
            model.addAttribute("userNameInf", user.getName());
            model.addAttribute("passwordInf", user.getPassword());
            model.addAttribute("emailInf", user.getEmail());
            model.addAttribute("registerTimeInf", user.getRegisterTime());
            model.addAttribute("activeTimeInf", user.getActiveTime());
            ContentEntry[] contents = processor.getRecentContents(name);
            if (contents != null) {
                int index = 1;
                for (ContentEntry content : contents) {
                    model.addAttribute("t"+index, content.getTitle());
                    model.addAttribute("f"+index,content.getContent());
                    index++;
                }
                System.out.println(model.asMap());
            }
            return "../userindex.html";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "../login.html";
        }
    }
}
