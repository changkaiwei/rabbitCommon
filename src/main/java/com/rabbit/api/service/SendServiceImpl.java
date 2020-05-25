package com.rabbit.api.service;

import com.rabbit.api.design.SendStrategyContext;
import com.rabbit.api.entity.RabbitMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kevin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SendServiceImpl implements  ISendService{

    private final SendStrategyContext sendStrategyContext;

    @Override
    public void sendMessage(RabbitMessage message) {
        sendStrategyContext.getResource(message);
    }

    @Override
    public void sendMessage(List<RabbitMessage> messageList) {

    }
}
