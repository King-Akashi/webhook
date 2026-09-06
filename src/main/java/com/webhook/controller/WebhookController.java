package com.webhook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webhook.model.EventRequest;
import com.webhook.model.Webhook;
import com.webhook.model.WebhookRequest;
import com.webhook.service.WebhookService;

@RestController
public class WebhookController {
    private final WebhookService wService;
    public WebhookController(WebhookService webhookService){
        this.wService = webhookService;
    }
    @PostMapping("/webhooks")
    public Webhook registerWebhook(@RequestBody WebhookRequest request){
        Webhook wb = wService.registerWebhook(request);
        return wb;
    }
    @PostMapping("/events")
    public void triggerEvent(@RequestBody EventRequest request){
        // System.out.println("Controller reached");
        wService.triggerWebhook(request);
    }
    @DeleteMapping("/webhooks/{id}")
    public ResponseEntity deleteWebhook(@PathVariable("id") String id){
        boolean deleted = wService.deleteWebhook(id);
        if(deleted) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); // 404
    }
}
