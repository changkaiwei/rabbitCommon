package com.rabbit.api.controller;

import com.rabbit.api.convert.JacksonSerializer;
import com.rabbit.api.entity.RabbitConfirmCallBack;
import com.rabbit.api.entity.RabbitMessage;
import com.rabbit.api.service.ISendService;
import com.rabbit.api.type.RabbitMessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author kevin
 */
@RestController
@RequestMapping("/test/")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Resource
    private ISendService sendService;

    @GetMapping("test")
    public Object test(@RequestParam("msg") String msg){
        LOGGER.info("业务处理完成，记录日志状态：发布待确认");
        sendService.sendMessage(RabbitMessage.builder()
                                                .id("797979")
                                                .messageType(RabbitMessageType.CONFIRM)
                                                .topic("exchange-1")
                                                .routeKey("key-1")
                                                .rabbitConfirmCallBack(new RabbitConfirmCallBack())
                                                .messageBody(msg).build());
        return HttpStatus.OK;
    }
}
