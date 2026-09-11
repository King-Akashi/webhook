package com.webhook.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.webhook.model.DeliveryAttempt;

@Component
public class InMemoryMessageQueue implements MessageQueue {
    private final Queue<DeliveryAttempt> q = new LinkedList<>();
    private final List<DeliveryAttempt> InFlight = new ArrayList<>();
    @Override
    public boolean enqueue(DeliveryAttempt deliveryAttempt){
        return q.add(deliveryAttempt);
    }

    @Override 
    public DeliveryAttempt dequeue(){
        DeliveryAttempt e = q.poll();
        if (e != null) {
            InFlight.add(e);
        }
        return e;
    }

    @Override
    public boolean Acknowledge(DeliveryAttempt e) {
        return InFlight.remove(e);
    }
    @Override 
    public boolean requeue(DeliveryAttempt e){
        boolean removed = InFlight.remove(e);
        if (!removed) {
            return false;
        }
        return q.add(e);
    }
}
