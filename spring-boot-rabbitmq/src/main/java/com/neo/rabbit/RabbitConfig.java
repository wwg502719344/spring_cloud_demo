package com.neo.rabbit;

import com.neo.model.MQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue repeatTradeQueue() {
        Queue queue = new Queue(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME,true,false,false);
        return queue;
    }

    //信道配置
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(MQConstant.DEFAULT_EXCHANGE, true, false);
    }

    @Bean
    public Binding  drepeatTradeBinding() {
        return BindingBuilder.bind(repeatTradeQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
    }

    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", MQConstant.DEFAULT_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
        Queue queue = new Queue(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME,true,false,false,arguments);
        System.out.println("arguments :" + queue.getArguments());
        return queue;
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(defaultExchange()).with(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME);
    }



    /////////////////////队列测试//////////////////////////
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }



    @Bean
    public Binding binding() {
        return BindingBuilder.bind(helloQueue()).to(defaultExchange()).with(MQConstant.HELLO_QUEUE_NAME);
    }


    ////////////////////////其余队列////////////////////////

    @Bean
    public Queue neoQueue() {
        return new Queue("neo");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }
}
