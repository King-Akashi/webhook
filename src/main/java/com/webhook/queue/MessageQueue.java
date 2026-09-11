package com.webhook.queue;

import org.springframework.stereotype.Component;

import com.webhook.model.DeliveryAttempt;

/**
 * MessageQueue
 */
@Component
public interface MessageQueue {
    public boolean enqueue(DeliveryAttempt e);
    public DeliveryAttempt dequeue();
    public boolean Acknowledge(DeliveryAttempt e);
    public boolean requeue(DeliveryAttempt e);
}