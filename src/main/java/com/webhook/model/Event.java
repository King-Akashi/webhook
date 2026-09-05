package com.webhook.model;

public class Event {
    private String eventType;
    private String payload;
    public Event(String eventType, String payload){
        this.eventType = eventType;
        this.payload = payload;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPayload() {
        return payload;
    }
    
}
