package com.webhook.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.webhook.model.Event;
import com.webhook.model.Webhook;
import com.webhook.queue.MessageQueue;
import com.webhook.repository.WebhookRepository;

@Service 
public class ConsumerService {
    private final MessageQueue mq;
    private final WebhookRepository repo;
    private final WebhookDelivery webhookDelivery;
    public ConsumerService(MessageQueue mq, WebhookRepository repo, WebhookDelivery webhookDelivery) {
        this.mq = mq;
        this.repo = repo;
        this.webhookDelivery = webhookDelivery;
    }
    public boolean processNextEvent(){
        Event e = mq.dequeue();
        if(e==null){
            return false;
        }
        List<Webhook>ls = repo.findByEvent(e);
        for (Webhook wb : ls) {
            webhookDelivery.deliverEvent(wb, e);
        }
        return true;
    }
    
}
