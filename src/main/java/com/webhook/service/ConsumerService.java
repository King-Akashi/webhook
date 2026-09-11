package com.webhook.service;


import org.springframework.stereotype.Service;

import com.webhook.model.DeliveryAttempt;
import com.webhook.model.Event;
import com.webhook.model.Webhook;
import com.webhook.queue.MessageQueue;
import com.webhook.retry.RetryPolicy;

@Service 
public class ConsumerService {
    private final MessageQueue mq;
    private final WebhookDelivery webhookDelivery;
    private final RetryPolicy retryPolicy; 
    public ConsumerService(MessageQueue mq, WebhookDelivery webhookDelivery, RetryPolicy retryPolicy) {
        this.mq = mq;
        this.webhookDelivery = webhookDelivery;
        this.retryPolicy = retryPolicy;
    }
    public boolean processNextEvent(){
        DeliveryAttempt da = mq.dequeue();
        if(da == null){
            return false;
        }
        Webhook wb = da.getWebhook();
        Event e = da.getEvent();
        boolean ack = webhookDelivery.deliverEvent(wb, e);
        if(!ack){
            DeliveryAttempt nextAttempt = new DeliveryAttempt(e, wb, da.getAttemptNumber()+1);
            if(retryPolicy.shouldRetry(nextAttempt)){
                mq.requeue(nextAttempt);
            }
            return false;
        }
        return mq.Acknowledge(da);
    }
    
}
