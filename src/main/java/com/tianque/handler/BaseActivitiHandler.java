package com.tianque.handler;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.constant.WorkFlowConstant;
import com.tianque.util.StringUtil;

public abstract class BaseActivitiHandler {
	@Autowired public ProcessEngine processEngine;
	@Autowired public RuntimeService runtimeService ;
	@Autowired public RepositoryService repositoryService;
	@Autowired public TaskService taskService;
	@Autowired public ManagementService managementService;
	@Autowired public HistoryService historyService;
	@Autowired public FormService formService;
	@Autowired public IdentityService identityService;

	public Logger logger = Logger.getLogger(BaseActivitiHandler.class); 
	public Map<String, Object> processMap=new HashMap<String, Object>();
	public Map<String, String> formDataMap=new HashMap<String, String>();

	public User saveActivitiUser(Map<String,String> userMap){
		String userId = userMap.get(WorkFlowConstant.PROCESS_USERID);
		String passwd = userMap.get(WorkFlowConstant.PROCESS_PASSWORD);
		String groupId = userMap.get(WorkFlowConstant.PROCESS_GROUPID);
		IdentityService identityService = processEngine.getIdentityService();
		User user = identityService.newUser(userId);
		if(StringUtil.isEmpty(userId)){
			return null;
		}
		if(StringUtil.isNotEmpty(passwd)){
			user.setPassword(passwd);
		}
		identityService.saveUser(user);
		user = identityService.createUserQuery().userId(userId).singleResult();
		
		if(StringUtil.isNotEmpty(groupId)&&null!=identityService.createGroupQuery().groupId(groupId).singleResult()){
				identityService.createMembership(userId, groupId);
		}
		return user!=null?user:null;
	}
	
	public void deleteActivitiUser(String userId){
		IdentityService identityService = processEngine.getIdentityService();
		identityService.deleteUser(userId);
	}
	
	public Group saveActivitiGroup(Map<String,String> groupMap){
		String groupId = groupMap.get(WorkFlowConstant.PROCESS_GROUPID);
		String groupName = groupMap.get(WorkFlowConstant.PROCESS_GROUPNAME);

		IdentityService identityService = processEngine.getIdentityService();
		Group group = identityService.newGroup(groupId);
		group.setName(groupName);
		group.setType(WorkFlowConstant.GROUPTYPE_ASSIGNMENT);
		identityService.saveGroup(group);
		group = identityService.createGroupQuery().groupId(groupId).singleResult();
		return group!=null?group:null;
	}
	
	public void deleteActivitiGroup(String groupId){
		IdentityService identityService = processEngine.getIdentityService();
		identityService.deleteGroup(groupId);
	}
	
	public String queryUserGroup(String userId){
		IdentityService identityService = processEngine.getIdentityService();
		Group group = identityService.createGroupQuery().groupMember(userId).singleResult();
		return group.getId();
	}
	
	public void completeTask(String taskId,Map<String, Object> map) {
		// TODO Auto-generated method stub
		if(map==null||map.size()<1){
			taskService.complete(taskId);
		}else{
			taskService.complete(taskId,map);
		}
	}
	public void completeTask(String taskId) {
		// TODO Auto-generated method stub
		taskService.complete(taskId);
	}
	
	public void completeFormTask(String taskId,Map<String, String> map) {
		// TODO Auto-generated method stub
		formService.submitTaskFormData(taskId, map);
	}
}
