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
@RequestMapping("/content")
public class ContentHandler {
    @Resource
    Processor processor;

    @RequestMapping("/add")
    public String addContent(
            @RequestParam(required = false,value = "userName") String name,
            @RequestParam(required = false,value = "title") String title,
            @RequestParam(required = false,value = "text") String text,
            Model model
    ){
        processor.addContent(name, title,text);
        UserEntry user = processor.getUserByName(name);
        model.addAttribute("uidInf", user.getUid());
        model.addAttribute("userNameInf", user.getName());
        model.addAttribute("passwordInf", user.getPassword());
        model.addAttribute("emailInf", user.getEmail());
        model.addAttribute("registerTimeInf", user.getRegisterTime());
        model.addAttribute("activeTimeInf", user.getActiveTime());
        ContentEntry[] contents = processor.getRecentContents(user.getName());
        if (contents != null) {
            int index = 1;
            for (ContentEntry content : contents) {
                model.addAttribute("t"+index, content.getTitle());
                model.addAttribute("f"+index,content.getContent());
                index++;
            }
        }
        return "../userindex.html";
    }
}
