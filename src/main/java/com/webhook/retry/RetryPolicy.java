package com.webhook.retry;
import java.time.Duration;

import org.springframework.stereotype.Component;

import com.webhook.model.DeliveryAttempt;
/**
 * RetryPolicy
 */
@Component 
public interface RetryPolicy {
    public boolean shouldRetry(DeliveryAttempt attempt);
    public Duration getBackoff(DeliveryAttempt attempt);
}