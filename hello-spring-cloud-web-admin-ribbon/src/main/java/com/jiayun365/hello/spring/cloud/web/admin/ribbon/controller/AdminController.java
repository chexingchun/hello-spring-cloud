package com.jiayun365.hello.spring.cloud.web.admin.ribbon.controller;

import com.jiayun365.hello.spring.cloud.web.admin.ribbon.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService service;

    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String hi(){
        return service.sayHi("hi");
    }
}
