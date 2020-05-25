package com.rabbit.api.entity;

import com.rabbit.api.type.RabbitMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author kevin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMessage {

    private String id;

    private String messageBody;

    private String topic;

    private String messageType =  RabbitMessageType.QUICK;

    private Integer delay = 0;

    private String routeKey;

    private Map<String,Object> properties;

    private RabbitConfirmCallBack rabbitConfirmCallBack;
}
