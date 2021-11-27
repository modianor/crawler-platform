package com.example.crawlerserver.service.serviceImp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.crawlerserver.dao.ITaskDao;
import com.example.crawlerserver.entity.Task;
import com.example.crawlerserver.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskService implements ITaskService {

    @Autowired
    private ITaskDao iTaskDao;

    @Override
    public void push_task(Task task) {
        String task_type = task.task_type;
        if ("List".equals(task_type)) {
            //List任务包含爬虫生成的List或者Detail任务
            log.info("Service: List任务");
            iTaskDao.push_task(task);
        } else if ("Detail".equals(task_type)) {
            //Detail任务包含爬虫任务处理结果以及日志信息
            if (task.data == null) {
                iTaskDao.push_task(task);
            } else {
                log.info("Service: Detail任务结果");
            }
        }
    }

    @Override
    public Task pop_task(String spiderName) {
        return iTaskDao.pop_task(spiderName);
    }

    @Override
    public void pushTasks(List<JSONObject> tasks) {
        for (JSONObject task : tasks) {
            iTaskDao.pushTask(task);
        }
    }

    @Override
    public void pushTask(JSONObject task) {
        iTaskDao.pushTask(task);
    }

    @Override
    public JSONArray getTaskParams(List<String> policyIds) {
        JSONArray tasks = new JSONArray();
        for (String policyId : policyIds) {
            JSONObject task = iTaskDao.getTaskParam(policyId);
            tasks.add(task);
        }
        return tasks;
    }
}
