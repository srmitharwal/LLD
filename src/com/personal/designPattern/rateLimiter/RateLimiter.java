package com.personal.designPattern.rateLimiter;

import com.personal.designPattern.rateLimiter.models.LimitResponse;
import com.personal.designPattern.rateLimiter.models.Request;

public interface RateLimiter {
    public LimitResponse grantAccess();
}
