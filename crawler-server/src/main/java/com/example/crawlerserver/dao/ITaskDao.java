package com.example.crawlerserver.dao;


import com.example.crawlerserver.entity.Task;

public interface ITaskDao {
    void push_task(Task task);

    Task pop_task(String spiderName);
}
