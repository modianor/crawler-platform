package com.example.crawlerserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import java.util.List;

import static com.example.crawlerserver.utils.TaskUtil.getTasksFromString;


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

    @RequestMapping(path = "/uploadTaskParams", method = RequestMethod.POST)
    @ResponseBody
    public String uploadTask(String task, String result) {
        JSONObject taskObj = JSON.parseObject(task);
        List<JSONObject> childTasks = getTasksFromString(result);
        iTaskService.pushTasks(childTasks);
        return "{\"status\":\"ok\"}";
    }

    @RequestMapping(path = "/getTaskParams", method = RequestMethod.POST)
    @ResponseBody
    public String getTaskParams(@RequestParam(value = "policyIds") List<String> policyIds) {
        JSONArray tasks = iTaskService.getTaskParams(policyIds);
        return tasks.toJSONString();
    }

    @RequestMapping(path = "/generateTaskParam", method = RequestMethod.POST)
    @ResponseBody
    public String generateTaskParam(String taskParam) {
        JSONObject task = JSON.parseObject(taskParam);
        iTaskService.pushTask(task);
        return "{\"status\":\"ok\"}";
    }


    @RequestMapping(path = "/getTask", method = RequestMethod.GET)
    @ResponseBody
    public Task getTask(@RequestParam("spider_name") String spiderName) {
        return iTaskService.pop_task(spiderName);
    }
}
