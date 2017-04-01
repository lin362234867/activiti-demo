package com.tianque.bean;

import java.util.Date;

public class BaseDomain {
	protected Long id;
	protected Date createDate;
	protected String createUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}	
