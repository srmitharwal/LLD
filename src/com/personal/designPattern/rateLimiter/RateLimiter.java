package com.personal.designPattern.rateLimiter;

import com.personal.designPattern.rateLimiter.models.LimitResponse;

public interface RateLimiter {
    public LimitResponse grantAccess();
}
