package com.rabbit.api.convert;

import com.rabbit.api.entity.RabbitMessage;

/**
 * @author kevin
 */
public class JacksonSerializerFactory implements SerializerFactory{

    public static final SerializerFactory INSTANCE = new JacksonSerializerFactory();

    @Override
    public Serializer create() {
        return JacksonSerializer.createParametricType(RabbitMessage.class);
    }
}
