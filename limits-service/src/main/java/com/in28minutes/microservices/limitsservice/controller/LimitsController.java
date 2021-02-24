package com.in28minutes.microservices.limitsservice.controller;

import com.in28minutes.microservices.limitsservice.bean.Limits;
import com.in28minutes.microservices.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@RestController
public class LimitsController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }


    @GetMapping("/send")
    public String sendMessage(){
        jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello i am aniket");
        return "OK";
    }

    @GetMapping("/receive")
    public String receiveMessage(){
        return  jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
    }
}
