package com.webhook.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webhook.model.EventRequest;
import com.webhook.model.WebhookRequest;
import com.webhook.service.WebhookService;

@RestController
public class WebhookController {
    private final WebhookService wService;
    public WebhookController(WebhookService webhookService){
        this.wService = webhookService;
    }
    @PostMapping("/webhooks")
    public void registerWebhook(@RequestBody WebhookRequest request){
        wService.registerWebhook(request);
    }
    @PostMapping("/events")
    public void triggerEvent(@RequestBody EventRequest request){
        // System.out.println("Controller reached");
        wService.triggerWebhook(request);
    }
}
