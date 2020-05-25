package com.rabbit.api.convert;


import com.google.common.base.Preconditions;
import com.rabbit.api.entity.RabbitMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author kevin
 */
public class RabbitMessageConverter  implements MessageConverter {

    private GenericMessageConverter delegate;

//	private final String delaultExprie = String.valueOf(24 * 60 * 60 * 1000);

    public RabbitMessageConverter(GenericMessageConverter genericMessageConverter) {
        Preconditions.checkNotNull(genericMessageConverter);
        this.delegate = genericMessageConverter;
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties)  {
//		messageProperties.setExpiration(delaultExprie);
        RabbitMessage message = (RabbitMessage) object;
        messageProperties.setDelay(message.getDelay());
        return this.delegate.toMessage(object, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) {
        RabbitMessage msg = (RabbitMessage) this.delegate.fromMessage(message);
        return msg;
    }
}
