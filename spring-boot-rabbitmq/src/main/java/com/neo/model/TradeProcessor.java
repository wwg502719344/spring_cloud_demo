package com.neo.model;

/**
 * Created by W2G on 2018/5/30.
 */

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author victor
 * @desc 死信接收处理消费者
 */
@Component
@RabbitListener(queues = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
public class TradeProcessor {

    @Autowired
    private IMessageQueueService messageQueueService;

    @RabbitHandler
    public void process(String content) {

        DLXMessage message = JSONUtils.toBean(content, DLXMessage.class);
        messageQueueService.send(message.getQueueName(), message.getContent());

    }
}