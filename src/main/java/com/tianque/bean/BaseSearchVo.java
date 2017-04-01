package com.tianque.bean;

import java.util.Date;

import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchBUI;

public class BaseSearchVo extends SearchBUI{
	protected Long id;
	protected Date createDate;
	protected String createUser;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ValueField(column ="create_user")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@ValueField(column ="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}	
