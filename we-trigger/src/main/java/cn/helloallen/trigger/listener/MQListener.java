package cn.helloallen.trigger.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * RocketMQ 接收消息
 * @author wanghailun helloallen.cn @小傅哥
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "xfg-mq", consumerGroup = "xfg-group")
@ConditionalOnProperty(name = "rocketmq.enabled", havingValue = "true", matchIfMissing = false)
public class MQListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("接收到RocketMQ消息 {}", s);
    }

}
