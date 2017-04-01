package com.tianque.module.issue.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.tianque.bean.BaseDomain;

public class IssueTask extends BaseDomain{
	@NotEmpty(message="事件ID不能为空")
	private String issueId;
	@NotEmpty(message="任务ID不能为空")
	private String taskId;
	private String issueName;
	private String taskName;
	private String keywords;
	@NotEmpty(message="流程实例ID不能为空")
	private String processInstanceId;

	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
}
