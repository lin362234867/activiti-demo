package com.tianque.test;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianque.bean.User;
import com.tianque.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)   
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})  
public class Test {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
