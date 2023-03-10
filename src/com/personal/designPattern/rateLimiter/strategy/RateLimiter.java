package com.personal.designPattern.rateLimiter.strategy;

import com.personal.designPattern.rateLimiter.models.LimitResponse;
import com.personal.designPattern.rateLimiter.models.Request;

public interface RateLimiter {
    public LimitResponse acceptRequest(Request request);
}
