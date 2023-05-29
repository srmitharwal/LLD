package com.personal.designPattern.rateLimiter.sw;

import com.personal.designPattern.rateLimiter.models.LimitResponse;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {
    Map<Integer, SlidingRateLimiter> bucket;

    public UserBucketCreator(int userId) {
        bucket = new HashMap<>();
        bucket.put(userId, new SlidingRateLimiter(2, 1));
    }

    public LimitResponse grantAccess(int userId) {
        LimitResponse limitResponse = bucket.get(userId).grantAccess();
        System.out.println(Thread.currentThread().getName() + " " +limitResponse.getMessage());

        return limitResponse;
    }

}
