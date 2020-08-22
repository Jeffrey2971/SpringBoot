package com.jeffrey.user.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron = "0 * * * * 1-7")
    public void hello() {

        System.out.println("Hello ...");
    }
}
