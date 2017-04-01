package com.tianque.service;

import org.durcframework.core.service.CrudService;
import org.springframework.stereotype.Service;

import com.tianque.bean.User;
import com.tianque.dao.UserDao;

@Service 
public class UserService extends CrudService<User, UserDao>{
}
