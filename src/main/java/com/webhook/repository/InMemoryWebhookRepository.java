package com.webhook.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.webhook.model.Event;
import com.webhook.model.Webhook;

@Repository
public class InMemoryWebhookRepository implements WebhookRepository {
    private final List<Webhook> webhooks;

    public InMemoryWebhookRepository() {
        webhooks = new ArrayList<>();
    }
    @Override
    public void save(Webhook webhook){
        webhooks.add(webhook);
    }
    @Override
    public List<Webhook> findByEvent(Event e) {
        List<Webhook>matchingWebhooks = new ArrayList<>();
        for (int idx = 0; idx < webhooks.size(); idx++) {
            Webhook webhook = webhooks.get(idx);
            System.out.println(webhook.getEventType() + ":"+e.getEventType());
            if(webhook.getEventType().equals(e.getEventType())){
                matchingWebhooks.add(webhook);
            }
        }
        return matchingWebhooks;
    }
}
