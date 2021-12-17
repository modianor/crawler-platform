package com.example.crawlerserver.dao;


import com.alibaba.fastjson.JSONObject;
import com.example.crawlerserver.entity.Policy;
import com.example.crawlerserver.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPolicyDao {
    void addPolicy(Policy policy);

    void updatePolicy(Policy policy);

    void deletePolicyById(Integer id);

    void deletePolicyByPolicyId(String policyId);

    Policy getPolicyById(Integer id);

    Policy getPolicyByPolicyId(String policyId);

//    List<Policy> getPolicies();
}
