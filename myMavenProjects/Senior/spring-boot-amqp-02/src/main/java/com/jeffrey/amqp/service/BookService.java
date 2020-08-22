package com.jeffrey.amqp.service;

import com.jeffrey.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class BookService {
    @RabbitListener(queues = "mable.news")
    public void receive(Book book) throws IOException {
        System.out.println("收到消息：" + book);
        FileWriter fw = new FileWriter("/Users/jeffrey/test/found.txt", true);
        fw.write(book + "\n");
        fw.close();
    }

    @RabbitListener(queues = "jeffrey.news")
    public void receive(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());

    }
}
