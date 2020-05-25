package com.rabbit.api.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.rabbit.api.convert.*;
import com.rabbit.api.entity.RabbitMessage;
import com.rabbit.api.type.RabbitMessageType;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 池化收益 提高效率 进行定制化收益
 * @author kevin
 */
@Component
public class RabbitContext {

    @Resource
    private ConnectionFactory connectionFactory;

    private SerializerFactory serializerFactory = JacksonSerializerFactory.INSTANCE;

    private Map<String, RabbitTemplate> templateMap = Maps.newConcurrentMap();

    public RabbitTemplate getTemplate(RabbitMessage message){
        Preconditions.checkNotNull(message.getTopic(),"topic lose...");
        RabbitTemplate rabbitTemplate = templateMap.get(message.getTopic());
        if (!ObjectUtils.isEmpty(rabbitTemplate)){
            return  rabbitTemplate;
        }else {
            RabbitTemplate rabbitTemplateNew = new RabbitTemplate(connectionFactory);
            rabbitTemplateNew.setExchange(message.getTopic());
            rabbitTemplateNew.setRetryTemplate(new RetryTemplate());
            rabbitTemplateNew.setRoutingKey(message.getRouteKey());
            GenericMessageConverter genericMessageConverter = new GenericMessageConverter(serializerFactory.create());
            rabbitTemplateNew.setMessageConverter(new RabbitMessageConverter(genericMessageConverter));
            if (!message.getMessageType().equals(RabbitMessageType.QUICK)){
                rabbitTemplateNew.setConfirmCallback(message.getRabbitConfirmCallBack());
            }
            templateMap.putIfAbsent(message.getTopic(),rabbitTemplateNew);
            return rabbitTemplateNew;
        }
    }
}
