package com.webhook.retry;
import java.time.Duration;

import org.springframework.stereotype.Component;

import com.webhook.model.DeliveryAttempt;

@Component 
public class FixedRetryPolicy implements RetryPolicy{
    public final int maxAttempts = 3;

    @Override
    public boolean shouldRetry(DeliveryAttempt attempt) {
        return (attempt.getAttemptNumber()<=maxAttempts);
    }
    @Override
    public Duration getBackoff(DeliveryAttempt attempt){
        return Duration.ofSeconds(120);
    }
}
