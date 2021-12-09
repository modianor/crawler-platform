package com.example.crawlerserver.service;

import com.example.crawlerserver.entity.Policy;

import java.util.List;

public interface IPolicyService {
    List<Policy> getPolicys();

    Policy getPolicyByPolicyId(String policyId);

    Policy getPolicById(Integer id);

    void addPolicy(Policy policy);

    void updatePolicy(Policy policy);
}
