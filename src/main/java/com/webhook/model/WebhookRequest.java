package com.webhook.model;

public class WebhookRequest {
    private String eventType;
    private String targetUrl;

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getEventType() {
        return eventType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }
    
}
