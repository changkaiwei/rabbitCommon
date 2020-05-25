package com.rabbit.api.util;

import com.rabbit.api.entity.RabbitMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author kevin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SendUtil {

    private final AsyncUtil asyncUtil;

    private final RabbitContext rabbitContext;

    public void sendKernel(RabbitMessage message){
        asyncUtil.send(() -> rabbitContext.getTemplate(message).convertAndSend(message.getTopic(),
                                                                                message.getRouteKey(),
                                                                                message,
                                                                                new CorrelationData(message.getId()))
        );
    }
}
