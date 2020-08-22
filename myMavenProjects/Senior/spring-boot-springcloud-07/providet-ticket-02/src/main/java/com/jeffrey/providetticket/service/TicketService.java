package com.jeffrey.providetticket.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    public String getTicket(){
        System.out.println(8001);
        return "《星际穿越》";
    }
}
