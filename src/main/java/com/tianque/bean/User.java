package com.tianque.bean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class User {
	private Long id;
	@NotEmpty(message="用户名不能为空")
	@Size(min=2,max=40,message="用户名长度范围在2-40之间")
	private String userName;
	@Size(min=6,max=40,message="密码长度范围在6-40之间")
	@NotEmpty(message="密码不能为空")
	private String passwd;
	private String orgCode;
	private Long orgId;
	private Integer isInuse;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Integer getIsInuse() {
		return isInuse;
	}
	public void setIsInuse(Integer isInuse) {
		this.isInuse = isInuse;
	}
	

	
}