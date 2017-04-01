package com.tianque.module.issue.controller;

import javax.annotation.Resource;

import org.durcframework.core.GridResult;
import org.durcframework.core.MessageResult;
import org.durcframework.core.controller.CrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianque.bean.User;
import com.tianque.handler.AbstractHandler;
import com.tianque.module.issue.domain.IssueTask;
import com.tianque.module.issue.service.IssueTaskService;
import com.tianque.module.issue.vo.IssueTaskSearchVo;
import com.tianque.vo.UserSearchVo;

@Controller
@RequestMapping("/issueTask")
public class IssueTaskController extends CrudController<IssueTask, IssueTaskService>{

    @RequestMapping("/list")
    public @ResponseBody GridResult listIssueTask(IssueTaskSearchVo searchEntity) {
        return this.query(searchEntity);
    }
}
