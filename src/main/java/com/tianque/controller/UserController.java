package com.tianque.controller;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianque.bean.User;
import com.tianque.service.UserService;
import com.tianque.vo.UserSearchVo;

@Controller
@RequestMapping("/user")
public class UserController extends CrudController<User, UserService>{	
	
    @RequestMapping("/add")
    public @ResponseBody MessageResult addUser(User entity) {
        return this.save(entity);
    }

    @RequestMapping("/list")
    public @ResponseBody GridResult listUser(UserSearchVo searchEntity) {
        return this.query(searchEntity);
    }

    @RequestMapping("/update")
    public @ResponseBody MessageResult updateUser(User entity) {
        return this.update(entity);
    }

    @RequestMapping("/delete")
    public @ResponseBody MessageResult delUser(User entity) {
        return this.delete(entity);
    }
    
    @RequestMapping("/get/{id}")
    public @ResponseBody User listUser(@PathVariable("id") Long id) {
        return this.get(id);
    }
}
