package com.rabbit.api.entity;

import com.rabbit.api.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author kevin
 */
public class RabbitConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String errCause) {
        System.out.println("信息唯一标识----"+correlationData.getId());
        if (ack){
            LOGGER.info("消息确认发送，状态：确认已发送");
        }else {
            LOGGER.info("消息确认发送，状态：发送失败原因"+errCause);
        }
    }
}
