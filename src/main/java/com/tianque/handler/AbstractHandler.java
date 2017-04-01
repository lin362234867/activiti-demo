package com.tianque.handler;

import java.util.List;

public interface AbstractHandler<T> {
	public void startWithForm();
	public void start();
	public void end();
	public List<T> queryUndoTask(String userName);
	public List<T> queryDoneTask(String userName);

	public void completeTask(String taskId);
	
}
