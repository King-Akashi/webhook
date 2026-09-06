package com.webhook.repository;

import java.util.List;

import com.webhook.model.Event;
import com.webhook.model.Webhook;

public interface WebhookRepository {
    public void save(Webhook webhook);
    public List<Webhook> findByEvent(Event e);
    public Webhook findWebhook(String id);
    public boolean delete(Webhook webhook);
}
