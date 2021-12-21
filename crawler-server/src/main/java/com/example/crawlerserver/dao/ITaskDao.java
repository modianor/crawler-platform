package com.example.crawlerserver.dao;


import com.alibaba.fastjson.JSONObject;
import com.example.crawlerserver.entity.Task;

public interface ITaskDao {
    Task pop_task(String spiderName);

    void pushTask(JSONObject task);

    JSONObject getTaskParam(String policyId);
}
