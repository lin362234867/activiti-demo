package com.tianque.controller;

import org.durcframework.core.MessageResult;
import org.durcframework.core.expression.ExpressionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianque.bean.User;
import com.tianque.service.UserService;
import com.tianque.vo.UserSearchVo;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	@Autowired private UserService userService;
	
    @RequestMapping("/login")
    public @ResponseBody String login(UserSearchVo searchVo) {
    	return userService.getByExpression(new ExpressionQuery().addAnnotionExpression(searchVo))!=null?SUCCESS:FAIL;
    }
}
