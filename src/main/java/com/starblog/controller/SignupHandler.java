package com.starblog.controller;

import com.starblog.entry.ContentEntry;
import com.starblog.entry.UserEntry;
import com.starblog.service.Processor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupHandler {
    @Resource
    Processor processor;

    @RequestMapping({"/signup", "/signup.html"})
    public String signup(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "email", required = false) String email,
            Model model
    ) {
        if (processor.isEmptyOrNull(username, password, email)) {
            return "../signup.html";
        }
        if (processor.isUserNameAvailable(username)) {
            processor.addUser(username, password, email);
            UserEntry user = processor.getUserByName(username);
            model.addAttribute("uidInf", user.getUid());
            model.addAttribute("userNameInf", user.getName());
            model.addAttribute("passwordInf", user.getPassword());
            model.addAttribute("emailInf", user.getEmail());
            model.addAttribute("registerTimeInf", user.getRegisterTime());
            model.addAttribute("activeTimeInf", user.getActiveTime());
            ContentEntry[] contents = processor.getRecentContents(username);
            if (contents != null) {
                int index = 1;
                for (ContentEntry content : contents) {
                    model.addAttribute("t"+index, content.getTitle());
                    model.addAttribute("f"+index,content.getContent());
                    index++;
                }
            }
            return "../userindex.html";
        } else {
            model.addAttribute("userNameInf", "用户名已经存在");
            return "../signup.html";
        }
    }

}
