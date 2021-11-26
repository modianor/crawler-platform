package com.example.crawlerserver.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装kafka事件类
 */
public class Event {

    // 主题：评论 点赞 关注 发帖 删帖 分享
    private String topic;
    // 事件生产者ID
    private int userId;
    // 事件类型
    private int entityType;
    // 事件ID
    private int entityId;
    // 事件消费者ID
    private int entityUserId;
    // 事件包含的数据
    private Map<String, Object> data = new HashMap<>();

    public String getTopic() {
        return topic;
    }

    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Event setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public Event setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public Event setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityUserId() {
        return entityUserId;
    }

    public Event setEntityUserId(int entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Event setData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
