package com.tianque.common.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.apache.log4j.Logger;

/** 事件动作监听器
*isFailOnException()方法决定了当事件分发后，onEvent(..)方法抛出异常时的行为。若返回false，忽略异常；返回true，
*异常不会被忽略而会被上抛，使当前执行的命令失败。如果事件是API调用（或其他事务操作，例如作业执行）的一部分，事务将被回滚。如果事件监听器中并不是重要的业务操作，建议返回false。
**/
public class EventLoggerListener implements ActivitiEventListener {
	
	public Logger logger = Logger.getLogger(EventLoggerListener.class); 

	@Override
	public boolean isFailOnException() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onEvent(ActivitiEvent event) {
	    switch (event.getType()) {

	      case JOB_EXECUTION_SUCCESS:
	    	logger.debug("A job well done!");
	        break;

	      case JOB_EXECUTION_FAILURE:
		    logger.debug("A job has failed...");
	        break;

	      default:
			logger.debug("Event received: " + event.getType());
	    }
	}

}
