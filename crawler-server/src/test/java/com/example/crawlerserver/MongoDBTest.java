package com.example.crawlerserver;

import com.example.crawlerserver.entity.Policy;
import com.example.crawlerserver.service.IPolicyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MongoDBTest {

    @Autowired
    private IPolicyService iPolicyService;

    @Test
    void insertPolicy() {
        Policy policy = new Policy();
        policy.setPolicyId("HEIMAOTOUSU");
        iPolicyService.addPolicy(policy);
    }

}
