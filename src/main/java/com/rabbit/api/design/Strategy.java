package com.rabbit.api.design;

import com.rabbit.api.entity.RabbitMessage;

/**
 * @author kevin
 */
public interface Strategy {

    /**
     * 策略模式发送消息
     * @param message 消息体
     * @return*/
    void sendMsg(RabbitMessage message);
}
