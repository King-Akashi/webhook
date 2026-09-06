package com.webhook.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.webhook.model.Event;
import com.webhook.model.EventRequest;
import com.webhook.model.Webhook;
import com.webhook.model.WebhookRequest;
import com.webhook.repository.WebhookRepository;

@Service
public class WebhookService {
    private final WebhookRepository repo;
    private final WebhookDelivery delivery;
    public WebhookService(WebhookRepository repo, WebhookDelivery delivery) {
        this.repo = repo;
        this.delivery = delivery;
    }
    
    public Webhook registerWebhook(WebhookRequest req){
        String id = UUID.randomUUID().toString();
        Webhook webhook = new Webhook(  req.getTargetUrl(), id, req.getEventType());
        repo.save(webhook);
        return webhook;
    }
    public void triggerWebhook(EventRequest req){
        Event event = new Event(req.getEventType(), req.getPayload());
    
        List<Webhook> webhooksList = repo.findByEvent(event);
        for (Webhook webhook : webhooksList) {
            System.out.println(webhook);
            boolean success = delivery.deliverEvent(webhook, event);
            System.out.println("Delivery to "+webhook.getUrl() + " success: "+success);
    }
    }
    public boolean deleteWebhook(String id){
        Webhook webhook = repo.findWebhook(id);
        if(webhook == null){
            return false;
        }
        return repo.delete(webhook);
    }
}
