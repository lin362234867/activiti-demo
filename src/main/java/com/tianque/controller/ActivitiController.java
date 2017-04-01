package com.tianque.controller;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.log4j.Logger;
import org.durcframework.core.Edit;
import org.durcframework.core.controller.CrudController;
import org.durcframework.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ActivitiController extends BaseController{
	//流程引擎的创建通过ProcessEngines.init()实现，关闭由ProcessEngines.destroy()实现。
	@Autowired public ProcessEngine processEngine;
	@Autowired public RuntimeService runtimeService ;
	@Autowired public RepositoryService repositoryService;
	@Autowired public TaskService taskService;
	@Autowired public ManagementService managementService;
	@Autowired public HistoryService historyService;
	@Autowired public FormService formService;
	public Logger logger = Logger.getLogger(ActivitiController.class); 

}
