package com.example.crawlerserver.controller;

import com.example.crawlerserver.entity.Task;
import com.example.crawlerserver.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/task")
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private ITaskService iTaskService;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadTask(Task task) {
        iTaskService.push_task(task);
        return task.toString();
    }

    @RequestMapping(path = "/getTask", method = RequestMethod.GET)
    @ResponseBody
    public Task getTask(@RequestParam("spider_name") String spiderName) {
        return iTaskService.pop_task(spiderName);
    }
}
