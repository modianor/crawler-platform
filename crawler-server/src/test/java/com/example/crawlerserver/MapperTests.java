package com.example.crawlerserver;


import com.example.crawlerserver.dao.IPolicyDao;
import com.example.crawlerserver.entity.Policy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CrawlerServerApplication.class)
public class MapperTests {

    @Autowired
    private IPolicyDao iPolicyDao;

    @Test
    public void testAddPolicy() {
        Policy policy = Policy.builder()
                .policyId("CPWS")
                .policyName("裁判文书网")
                .clusterId("cluster1")
                .deduplicationServerId("crServer2_2")
                .listExpress("{\"compNameFilterStr\":[],\"regularExp\":\"\\\\[\"(?<compName>[\\\\w\\\\W]*?)\",\\n\"(?<creditCode>[\\\\w\\\\W]*?)\",\"(?<urlSign>[\\\\w\\\\W]*?)\"(,\"(?<createTaskParams>[\\\\w\\\\W]*?)\"\\n)?\\\\]\"}")
                .dataExpress("{\"columnNames\":[\"code\"],\"pkName\":\"code\",\"tableName\":\"BDG_DATA_KZ_EFFECTIVE_CO\\nDE\",\"update\":true}")
                .policyState(false)
                .interval(3.0f)
                .periodTime(180)
                .proxy(false)
                .retryTimes(3)
                .taskTypes("List|Detail|Data")
                .timeout(60.0f)
                .build();

        Policy p = iPolicyDao.getPolicyByPolicyId(policy.getPolicyId());
        if (p == null) {
            iPolicyDao.addPolicy(policy);
        }
    }

    @Test
    public void testGetPolicyByPolicyId() {
        String policyId = "HEIMAOTOUSU";
        Policy policy = iPolicyDao.getPolicyByPolicyId(policyId);
        System.out.println(policy.toString());
    }

    @Test
    public void testGetPolicyById() {
        Integer id = 6;
        Policy policy = iPolicyDao.getPolicyById(id);
        System.out.println(policy.toString());
    }

    @Test
    public void testUpdatePolicy() {
        Policy policy = iPolicyDao.getPolicyByPolicyId("HEIMAOTOUSU");
        policy.setRetryTimes(60);
        iPolicyDao.updatePolicy(policy);
    }

    @Test
    public void testGetAllPolicy() {
        List<Policy> policies = iPolicyDao.getAllPolicy(0, 1);
        for (Policy policy : policies) {
            System.out.println(policy.toString());
        }
    }

    @Test
    public void testDeletePolicyById() {
        Policy policy = iPolicyDao.getPolicyByPolicyId("XUEQIU");
        iPolicyDao.deletePolicyById(policy.getId());
    }
}
