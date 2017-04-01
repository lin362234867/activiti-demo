package com.tianque.module.issue.handler;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.common.context.TQActivitiContext;
import com.tianque.constant.TaskType;
import com.tianque.constant.WorkFlowConstant;
import com.tianque.constant.WorkflowKey;
import com.tianque.handler.AbstractHandler;
import com.tianque.handler.BaseActivitiHandler;
import com.tianque.module.issue.domain.IssueTask;

public class IssueHandler extends BaseActivitiHandler implements AbstractHandler<IssueTask>{
	@Autowired private RuntimeService runtimeService;
	
	private  ProcessDefinition processDefinition;	
	@Transactional
	@Override
	public void start() { 
		IssueTask issue = (IssueTask)TQActivitiContext.get(TaskType.ISSUE_TASK_TYPE);
		processMap.put(WorkFlowConstant.TASK_TYPE, TaskType.ISSUE_TASK_TYPE);
		processMap.put(WorkFlowConstant.TASK_ID, issue.getId());
		processMap.put(WorkFlowConstant.TASK_NAME, issue.getIssueName());
		processMap.put(WorkFlowConstant.START_USERNAME, issue.getCreateUser());

		//设置发起人，这个方法是通过线程判断是一个了流程实例的--->对应流程定义里面的activiti:initiator="applyUserId"  
        this.processEngine.getIdentityService().setAuthenticatedUserId(issue.getCreateUser());        
        
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(WorkflowKey.ISSUESIMPLE_KEY,processMap);
		logger.debug("流程实例ID:" + processInstance.getId());  
		logger.debug("流程定义ID:" + processInstance.getProcessDefinitionId());  
	}
	@Transactional
	@Override
	public void end() {
		// TODO Auto-generated method stub
	}
	@Transactional
	@Override
	public void startWithForm() {
		// TODO Auto-generated method stub
		IssueTask issue = (IssueTask)TQActivitiContext.get(TaskType.ISSUE_TASK_TYPE);
		formDataMap.put(WorkFlowConstant.TASK_TYPE, TaskType.ISSUE_TASK_TYPE);
		formDataMap.put(WorkFlowConstant.TASK_ID, String.valueOf(issue.getId()));
		formDataMap.put(WorkFlowConstant.TASK_NAME, issue.getIssueName());
		formDataMap.put(WorkFlowConstant.START_USERNAME, issue.getCreateUser());
		
        this.processEngine.getIdentityService().setAuthenticatedUserId(issue.getCreateUser());    
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(TaskType.ISSUE_TASK_TYPE).singleResult();
        ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), formDataMap);
        
		logger.debug("表单流程实例ID:" + processInstance.getId());  
		logger.debug("表单流程定义ID:" + processInstance.getProcessDefinitionId());  
	}
	
	@Transactional
	@Override
	public List<IssueTask> queryUndoTask(String userName) {
		// TODO Auto-generated method stub
		List<Task> taskList = taskService.createTaskQuery().taskAssignee(userName).list();
		List<IssueTask> issueTaskList = new ArrayList<>(taskList.size());
		for (Task task : taskList) {
			IssueTask issueTask = new IssueTask();
			issueTask.setTaskId(task.getId());
			issueTask.setTaskName(task.getName());
			task.getProcessVariables();
			issueTaskList.add(issueTask);
		}
		return issueTaskList;
	}
	
	@Transactional
	@Override
	public List<IssueTask> queryDoneTask(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
