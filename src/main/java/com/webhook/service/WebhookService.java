package com.webhook.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.webhook.model.Event;
import com.webhook.model.EventRequest;
import com.webhook.model.Webhook;
import com.webhook.model.WebhookRequest;
import com.webhook.queue.MessageQueue;
import com.webhook.repository.WebhookRepository;

@Service
public class WebhookService {
    private final WebhookRepository repo;
    private final MessageQueue mq;
    public WebhookService(WebhookRepository repo, MessageQueue mq) {
        this.repo = repo;
        this.mq = mq;
    }
    
    public Webhook registerWebhook(WebhookRequest req){
        String id = UUID.randomUUID().toString();
        Webhook webhook = new Webhook(  req.getTargetUrl(), id, req.getEventType());
        repo.save(webhook);
        return webhook;
    }
    public boolean triggerWebhook(EventRequest req){
        Event event = new Event(req.getEventType(), req.getPayload());
        return mq.enqueue(event); 
    }
    public boolean deleteWebhook(String id){
        Webhook webhook = repo.findWebhook(id);
        if(webhook == null){
            return false;
        }
        return repo.delete(webhook);
    }
    
}
