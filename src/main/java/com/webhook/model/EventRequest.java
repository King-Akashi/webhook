package com.webhook.model;

public class EventRequest {
    private String eventType;
    private String payload;

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPayload() {
        return payload;
    }
    
}
