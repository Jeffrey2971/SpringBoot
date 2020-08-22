package com.jeffrey.amqp;

import com.jeffrey.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAmqp02ApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 创建 exchange.direct
     */
    @Test
    public void createExchange_direct(){
        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange.direct"));
        System.out.println("创建完成");
    }

    /**
     * 创建 exchange.fanout
     */
    @Test
    public void createExchange_fanout(){
        amqpAdmin.declareExchange(new FanoutExchange("amqpAdmin.exchange.fanout"));
        System.out.println("创建完成");
    }

    /**
     * 创建 exchange.topic
     */
    @Test
    public void createExchange_topic(){
        amqpAdmin.declareExchange(new TopicExchange("amqpAdmin.exchange.topic"));
        System.out.println("创建完成");
    }

    /**
     * 创建 exchange.headers
     */
    @Test
    public void createExchange_headers(){
        amqpAdmin.declareExchange(new HeadersExchange("amqp.exchange.headers"));
    }

    /**
     * 创建队列
     */
    @Test
    public void createQueue(){
        String queueName = amqpAdmin.declareQueue(new Queue("amqpAdmin.queue", true));
        System.out.println(queueName);
    }

    /**
     * 将 exchange 和 queue 相绑定
     */
    @Test
    public void binding(){
        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE, "amqpAdmin.exchange.direct", "amqp.test", null));
    }

    /**
     * 删除一个 exchange
     */
    @Test
    public void deleteExchange(){
        boolean b = amqpAdmin.deleteExchange("amqpAdmin.exchange.direct");
        System.out.println(b);
    }

    /**
     * 删除一个 Queue
     */
    @Test
    public void deleteQueue(){
        boolean b = amqpAdmin.deleteQueue("amqpAdmin.queue");
        System.out.println(b);
    }

    /**
     * 1、单播（点对点模式）
     */
    @Test
    public void contextLoads() {

        // message 需要自己构造，定义消息体内容和消息头
        // rabbitTemplate.send(exchange, routeKey, message);

        // 只需要传入要发送的对象，自动会序列化发送给 RabbitMQ，object 默认被当做消息体
        // rabbitTemplate.convertAndSend(exchange, routeKey, object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloWorld", 123, true));

        // 对象被默认序列化后发送点对点消息
        rabbitTemplate.convertAndSend("exchange.direct", "jeffrey.news", map);
    }

    /**
     * 接收数据
     */
    @Test
    public void receiveJson() {
        // 使用 receive 方法返回的数据既包含消息头也包含消息体
        // Message msg_1 = rabbitTemplate.receive("jeffrey.news");
        // System.out.println(msg_1);
        // 使用 receiveAndConvert 返回的只有存储时的消息数据
        Object msg_2 = rabbitTemplate.receiveAndConvert("jeffrey.news");
        if (msg_2 == null) {
            System.out.println("队列中没有数据！");
        } else {
            System.out.println(msg_2.getClass());
            System.out.println(msg_2);
        }
    }

    /**
     * 自定义类型存储：使用广播模式存放一个对象消息
     */
    @Test
    public void receiveBook() {
        rabbitTemplate.convertAndSend("exchange.fanout", "jeffrey", new Book("Java设计思想", "洪泽飞"));
    }

    /**
     * 自定义类型存储：取出一个对象消息
     */
    @Test
    public void getReceiveBook(){
        Object msg =  rabbitTemplate.receiveAndConvert("jeffrey.emps");
        System.out.println(msg);
    }

}
