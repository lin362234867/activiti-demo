package com.tianque.module.issue.listener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.BaseEntityEventListener;
/** 事件实体类监听器
 * org.activiti.engine.delegate.event.BaseEntityEventListener: 事件监听器基类，可用来监听实体（entity）相关事件，特定或所有实体的事件都可以。
 * 它隐藏了类型检测，提供了4个需要覆盖的方法：onCreate(..), onUpdate(..)与onDelete(..)在实体创建、更新及删除时调用；对所有其他实体相关事件，onEntityEvent(..)会被调用。
 * **/
public class IssueEntityEventListenter extends BaseEntityEventListener{
	@Override
	protected void onCreate(ActivitiEvent event) {
		// TODO Auto-generated method stub
		super.onCreate(event);
	}
	@Override
	protected void onDelete(ActivitiEvent event) {
		// TODO Auto-generated method stub
		super.onDelete(event);
	}
	@Override
	protected void onUpdate(ActivitiEvent event) {
		// TODO Auto-generated method stub
		super.onUpdate(event);
	}
	@Override
	protected void onEntityEvent(ActivitiEvent event) {
		// TODO Auto-generated method stub
		super.onEntityEvent(event);
	}
}
