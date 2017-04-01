package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestOneTaskProcess {
	public static void main(String[] args) {
		try {
			startProcess();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void startProcess() throws Exception {
		
		ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
			     .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
			     .setJdbcUrl("jdbc:mysql://192.168.100.239:3306/activiti?useUnicode=true&amp;characterEncoding=utf8")
			     .setJdbcUsername("activiti")
			     .setJdbcPassword("activiti")
			     .setJdbcDriver("com.mysql.jdbc.Driver")
			     .setJobExecutorActivate(true)
			     .buildProcessEngine();
		DeploymentBuilder builder = processEngine.getRepositoryService().createDeployment();
		Deployment deployment = builder.addClasspathResource("workflow/demoProcess.bpmn").deploy();
	}
}