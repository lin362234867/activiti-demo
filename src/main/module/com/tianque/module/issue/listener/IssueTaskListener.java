package com.tianque.module.issue.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

//事件任务监听器
public class IssueTaskListener  implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		   delegateTask.setAssignee("admin");
		   delegateTask.addCandidateUser("admin");
		   delegateTask.addCandidateGroup(".1.2.3.");
	}

}
