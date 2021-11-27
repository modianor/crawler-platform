package com.example.crawlerserver.dao.daoImp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.crawlerserver.dao.ITaskDao;
import com.example.crawlerserver.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TaskDao implements ITaskDao {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void push_task(Task task) {
        String spiderName = task.spider_name;
        String task_type = task.task_type;
        int score = 1;
        if ("Detail".equals(task_type)) {
            score = 10;
        }
        String taskKey = spiderName + ":" + "requests";
        log.info("push task: " + task);
        redisTemplate.opsForList().leftPush(taskKey, task);
        //redisTemplate.opsForZSet().add(taskKey, task, score);
    }

    @Override
    public Task pop_task(String spiderName) {
        String taskKey = spiderName + ":" + "requests";
        Task task = (Task) redisTemplate.opsForList().leftPop(taskKey);
        /*Set<Object> objs = redisTemplate.opsForZSet().range(taskKey, 0, 1);

        if (objs != null && objs.size() > 0) {
            for (Object obj : objs) {
                Task task = (Task) (obj);
                log.info("pop task: " + task);
                //redisTemplate.opsForZSet().removeRange(taskKey, 0, 1);
                return task;
            }
        }*/
        return task;
    }

    @Override
    public void pushTask(JSONObject task) {
        String policyId = task.getString("policyId");
        String taskType = task.getString("taskType");
        String redisKey = policyId + ":" + taskType;
        redisTemplate.opsForList().leftPush(redisKey, task.toJSONString());
    }

    @Override
    public JSONObject getTaskParam(String policyId) {
        String redisListKey = policyId + ":List";
        String redisDetailKey = policyId + ":Detail";
        Long detailTaskSize = redisTemplate.opsForList().size(redisDetailKey);
        if (detailTaskSize != null && detailTaskSize > 0) {
            String jsonStr = (String) redisTemplate.opsForList().leftPop(redisDetailKey);
            JSONObject task = JSON.parseObject(jsonStr);
            return task;
        } else {
            Long listTaskSize = redisTemplate.opsForList().size(redisListKey);
            if (listTaskSize != null && listTaskSize > 0) {
                String jsonStr = (String) redisTemplate.opsForList().leftPop(redisListKey);
                JSONObject task = JSON.parseObject(jsonStr);
                return task;
            }
        }
        return null;
    }
}
