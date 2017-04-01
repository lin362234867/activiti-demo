package com.tianque.vo;

import org.durcframework.core.Sortable;
import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.LikeLeftField;
import org.durcframework.core.expression.annotation.ValueField;
import org.durcframework.core.support.SearchBUI;

public class UserSearchVo extends SearchBUI{
	private Long id;
	private String userName;
	private String passwd;
	private String orgCode;
	private Long orgId;
	private Integer isInuse;
	
	@Override
	protected void initSortMap(Sortable sort) {
		sort.addSortField("id", "id")
		;
	}
	@ValueField(column = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ValueField(column = "passwd")
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@LikeLeftField(column = "org_code")
	public String getOrgCode() {
		return orgCode;
	}
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	@ValueField(column = "org_id")
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	
	@ValueField(column = "is_inUse")
	public Integer getIsInuse() {
		return isInuse;
	}
	public void setIsInuse(Integer isInuse) {
		this.isInuse = isInuse;
	}

	@ValueField(column = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
