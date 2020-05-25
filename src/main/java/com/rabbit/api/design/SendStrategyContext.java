package com.rabbit.api.design;

import com.google.common.collect.Maps;
import com.rabbit.api.entity.RabbitMessage;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author kevin
 */
@Component
public class SendStrategyContext {

    @Resource
    private final Map<String,Strategy> strategyMap = Maps.newConcurrentMap();

    public SendStrategyContext(Map<String, Strategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach(this.strategyMap::put);
    }

    public void getResource(RabbitMessage message){
        strategyMap.get(message.getMessageType()).sendMsg(message);
    }
}
