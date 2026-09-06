package com.webhook.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.webhook.model.Event;
import com.webhook.model.Webhook;

@Service
public class WebhookDelivery {
    private final RestClient restClient; // for dependency injection, we do not create the dependency in the same class. [the laws of cs engineering]
    public WebhookDelivery(RestClient restClient){
        this.restClient = restClient;
    }
    public boolean deliverEvent(Webhook webhook, Event event){
        try {
            ResponseEntity<String> response = restClient.post().uri(webhook.getUrl()).body(event.getPayload()).retrieve().toEntity(String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println("exception : "+e);
            return false;
        }
      

    }
}
