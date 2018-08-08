package com.neo.model;

/**
 * Created by W2G on 2018/5/30.
 */

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author victor
 * @desc 消息队列服务接口实现
 */
@Service("messageQueueService")
public class MessageQueueServiceImpl implements IMessageQueueService{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String queueName, String msg) {
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.HELLO_QUEUE_NAME, msg);
    }

    @Override
    public void send(String queueName, String msg, long times) {
        //延迟消息队列设置
        DLXMessage dlxMessage = new DLXMessage(queueName,msg,times);
        MessagePostProcessor processor = new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, JSONUtils.toJson(dlxMessage), processor);
    }


}

