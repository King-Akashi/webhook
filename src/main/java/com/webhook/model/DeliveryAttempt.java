package com.webhook.model;

/**
 * DeliveryAttempt
 */
public class DeliveryAttempt {
    private final Event event;
    private final Webhook webhook;
    private final int attemptNumber;
    public DeliveryAttempt(Event event, Webhook webhook, int attemptNumber) {
        this.attemptNumber = attemptNumber;
        this.event = event;
        this.webhook = webhook;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public Event getEvent() {
        return event;
    }

    public Webhook getWebhook() {
        return webhook;
    }


    
}