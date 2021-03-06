package com.example.crawlerserver.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.crawlerserver.entity.Task;

import java.util.List;

public interface ITaskService {
    Task pop_task(String spiderName);

    void pushTasks(List<JSONObject> tasks);

    void pushTask(JSONObject task);

    JSONArray getTaskParams(List<String> policyIds);
}
