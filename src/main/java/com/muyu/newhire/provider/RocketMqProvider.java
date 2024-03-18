package com.muyu.newhire.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Component
public class RocketMqProvider implements InitializingBean {

    @Value("${rocketmq.host}")
    private String host;

    @Value("${rocketmq.topic}")
    private String topic;

    private Producer producer;
    private final ClientServiceProvider provider = ClientServiceProvider.loadService();

    @Override
    public void afterPropertiesSet() throws ClientException {
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
                .setEndpoints(host)
                .enableSsl(false)
                .build();
        this.producer = provider.newProducerBuilder()
                .setClientConfiguration(clientConfiguration)
                .setTopics(topic)
                .build();
    }

    public void send(String body) {
        byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
        String tag = "tag-hire";
        final var message = provider.newMessageBuilder()
                .setTopic(topic)
                .setTag(tag)
                .setKeys(UUID.randomUUID().toString())
                .setBody(bodyBytes)
                .build();
        try {
            final SendReceipt sendReceipt = producer.send(message);
            log.info("[Mq] 成功发送消息, messageId={}", sendReceipt.getMessageId());
        } catch (Throwable t) {
            log.error("[Mq] 发送消息失败", t);
        }
    }

}
