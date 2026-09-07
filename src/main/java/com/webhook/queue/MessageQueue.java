package com.webhook.queue;

import org.springframework.stereotype.Component;

import com.webhook.model.Event;

/**
 * MessageQueue
 */
@Component
public interface MessageQueue {
    public boolean enqueue(Event e);
    public Event dequeue();
}