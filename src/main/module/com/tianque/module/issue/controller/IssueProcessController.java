package com.tianque.module.issue.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianque.controller.ActivitiController;
import com.tianque.handler.AbstractHandler;

@Controller
@RequestMapping("/issueProcess")
public class IssueProcessController extends ActivitiController {
	@Resource(name="issueHandler") private AbstractHandler issueHandler;
}
