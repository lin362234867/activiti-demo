package com.tianque.module.issue.service;

import org.durcframework.core.service.CrudService;
import org.springframework.stereotype.Service;

import com.tianque.module.issue.dao.IssueTaskDao;
import com.tianque.module.issue.domain.IssueTask;

@Service 
public class IssueTaskService extends  CrudService<IssueTask, IssueTaskDao>{

}
