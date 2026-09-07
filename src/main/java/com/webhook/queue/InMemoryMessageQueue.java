package com.webhook.queue;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.webhook.model.Event;

@Component
public class InMemoryMessageQueue implements MessageQueue {
    private final Queue<Event> q = new LinkedList<>();
    
    @Override
    public boolean enqueue(Event e){
        return q.add(e);
    }

    @Override 
    public Event dequeue(){
        return q.poll();
    }
}
