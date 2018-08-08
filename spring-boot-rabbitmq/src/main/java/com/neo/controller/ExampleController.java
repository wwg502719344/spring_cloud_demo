package com.neo.controller;

import com.neo.model.MQConstant;
import com.neo.model.MessageQueueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by W2G on 2018/5/31.
 */
@RestController
@RequestMapping("/w2g/*")
public class ExampleController {

    @Autowired
    private MessageQueueServiceImpl messageQueueService;

    @RequestMapping("/send/{seconds}")
    public String send(@PathVariable("seconds") int seconds){

        System.out.println("发送延迟消息...");
        long times = seconds * 1000;
        messageQueueService.send(MQConstant.HELLO_QUEUE_NAME,"test delay queue",times);

        return "ok";
    }
}
