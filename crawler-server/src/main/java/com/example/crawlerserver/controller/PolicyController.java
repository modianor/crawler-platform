package com.example.crawlerserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.crawlerserver.entity.Policy;
import com.example.crawlerserver.entity.Task;
import com.example.crawlerserver.service.IPolicyService;
import com.example.crawlerserver.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.example.crawlerserver.utils.TaskUtil.getTasksFromString;


@Controller
@RequestMapping("/policy")
public class PolicyController {
    private final Logger logger = LoggerFactory.getLogger(PolicyController.class);

    @Autowired
    private IPolicyService iPolicyService;

    @RequestMapping(path = "/getPolicys", method = RequestMethod.POST)
    @ResponseBody
    public String getPolicys() {
        List<Policy> policys = iPolicyService.getPolicys();
        return JSON.toJSONString(policys);
    }

    @RequestMapping(path = "/getPolicyByPolicyId", method = RequestMethod.POST)
    @ResponseBody
    public String getPolicyByPolicyId(String policyId) {
        Policy policy = iPolicyService.getPolicyByPolicyId(policyId);
        return JSON.toJSONString(policy);
    }

    @RequestMapping(path = "/getPolicById", method = RequestMethod.POST)
    @ResponseBody
    public String getPolicById(Integer id) {
        Policy policy = iPolicyService.getPolicById(id);
        return JSON.toJSONString(policy);
    }

    @RequestMapping(path = "/addPolicy", method = RequestMethod.POST)
    @ResponseBody
    public String addPolicy(Policy policy) {
        iPolicyService.addPolicy(policy);
        return JSON.toJSONString(policy);
    }

    @RequestMapping(path = "/updatePolicy", method = RequestMethod.POST)
    @ResponseBody
    public String updatePolicy(Policy policy) {
        iPolicyService.updatePolicy(policy);
        return JSON.toJSONString(policy);
    }
}
