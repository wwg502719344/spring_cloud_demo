package com.neo.model;

/**
 * Created by W2G on 2018/5/30.
 */
public interface IMessageQueueService {
    /**
     * 发送消息到队列
     * @param message 消息内容
     */
    public void send(String queueName,String message);

    /**
     * 延迟发送消息到队列
     * @param queueName 队列名称
     * @param message 消息内容
     * @param times 延迟时间 单位毫秒
     */
    public void send(String queueName,String message,long times);


}
