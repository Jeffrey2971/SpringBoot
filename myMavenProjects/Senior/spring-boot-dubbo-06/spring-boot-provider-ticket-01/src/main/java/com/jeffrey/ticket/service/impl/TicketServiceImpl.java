package com.jeffrey.ticket.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.jeffrey.ticket.service.TicketService;
import org.springframework.stereotype.Component;

// 将服务发布出去
@Component
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "《星际穿越》";
    }
}
