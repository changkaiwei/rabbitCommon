package com.rabbit.api.design;

import com.rabbit.api.entity.RabbitMessage;
import com.rabbit.api.util.SendUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 */
@Component("2")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfirmSend implements Strategy {

    private final SendUtil sendUtil;

    @Override
    public void sendMsg(RabbitMessage message) {
        sendUtil.sendKernel(message);
    }
}
