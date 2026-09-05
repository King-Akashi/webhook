package com.webhook.model;

public class Webhook{
    private String url;
    private String id;
    private String eventType;
    public Webhook(String url, String id, String eventType){
        this.url = url;
        this.id = id;
        this.eventType = eventType; 
    }
    public String getUrl(){
        return this.url;
    }
    
    public String getEventType(){
        return this.eventType;
    }
    
    public String getId(){
        return this.id;
    }
}
