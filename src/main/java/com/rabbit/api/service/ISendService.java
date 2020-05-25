package com.rabbit.api.service;

import com.rabbit.api.entity.RabbitMessage;

import java.util.List;

/**
 * @author kevin
 */
public interface ISendService {

    /**
     * rabbit 发送消息
     * @param message rabbit消息
     * */
    void sendMessage(RabbitMessage message);

    /**
     * rabbit 发送消息
     * @param messageList rabbit批量消息
     * */
    void sendMessage(List<RabbitMessage> messageList);
}
