package com.example.crawlerserver;


import com.example.crawlerserver.dao.IPolicyDao;
import com.example.crawlerserver.entity.Policy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CrawlerServerApplication.class)
public class MapperTests {

    @Autowired
    private IPolicyDao iPolicyDao;

    @Test
    public void testAddPolicy() {
        Policy policy = new Policy();
        policy.setPolicyId("HEIMAOTOUSU");
        policy.setPolicyName("黑猫投诉");
        iPolicyDao.addPolicy(policy);
    }

}
