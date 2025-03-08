package com.mmwwtt.serviceproduct8003.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class SentinelRuleConfig {
    public static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("testResource");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(10); // 每秒最多 10 次请求
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
