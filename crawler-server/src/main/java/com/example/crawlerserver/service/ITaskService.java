package com.example.crawlerserver.service;

import com.example.crawlerserver.entity.Task;

public interface ITaskService {
    void push_task(Task task);

    Task pop_task(String spiderName);
}
