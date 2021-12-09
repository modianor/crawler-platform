package com.example.crawlerserver.service.serviceImp;

import com.example.crawlerserver.entity.Policy;
import com.example.crawlerserver.service.IPolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class PolicyService implements IPolicyService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<Policy> getPolicys() {
        return null;
    }

    @Override
    public Policy getPolicyByPolicyId(String policyId) {
        return null;
    }

    @Override
    public Policy getPolicById(Integer id) {
        return null;
    }

    @Override
    public void addPolicy(Policy policy) {
        mongoTemplate.save(policy);
    }

    @Override
    public void updatePolicy(Policy policy) {
        Query query = new Query(Criteria.where("id").is(policy.id));

        Update update = new Update();
        update.set("policyName", policy.policyName);
        mongoTemplate.updateFirst(query, update, Policy.class);
    }
}
