package com.tianque.module.issue.vo;

import org.durcframework.core.Sortable;
import org.durcframework.core.expression.annotation.LikeDoubleField;
import org.durcframework.core.expression.annotation.ValueField;

import com.tianque.bean.BaseSearchVo;

public class IssueTaskSearchVo extends BaseSearchVo{
	private String issueId;
	private String taskId;
	private String issueName;
	private String taskName;
	private String keywords;
	private String processInstanceId;
	@Override
	protected void initSortMap(Sortable sort) {
		sort.addSortField("id", "id")
		;
	}
	@ValueField(column = "id")
	public Long getId() {
		return id;
	}
	@ValueField(column ="issue_id")
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	@ValueField(column ="issue_id")
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@LikeDoubleField(column ="issue_name")
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	
	@LikeDoubleField(column ="task_name")
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@ValueField(column ="task_name")
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@ValueField(column ="process_instance_Id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
}
