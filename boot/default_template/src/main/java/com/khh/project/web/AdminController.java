package com.khh.project.web;

import com.khh.project.service.admin.AdminService;
import com.khh.project.service.admin.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(AdminController.PATH_ROOT)
@Transactional
@javax.transaction.Transactional(rollbackOn = { Exception.class })
@Slf4j
public class AdminController {

    public static final String PATH_ROOT = "/admin";

    @Autowired
    public AdminService service;
    String home() {
        return "admin main";
    }

    @RequestMapping("/save")
    @ResponseBody
    String save() {
        return service.save();
    }

    @RequestMapping("/list")
    @ResponseBody
    List<User> list() {
        return service.list();
    }

    @RequestMapping("/list2")
    @ResponseBody
    List<User> list2() {
        return service.list2();
    }

    @RequestMapping("/list3")
    @ResponseBody
    User list3() {
        return service.list3();
    }
}
