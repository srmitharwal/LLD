package com.personal.designPattern.rateLimiter.strategy;

import com.personal.designPattern.rateLimiter.models.LimitResponse;
import com.personal.designPattern.rateLimiter.models.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class SlidingRateLimiter implements RateLimiter {
    static int limit = 10;
    static Map<String, BlockingDeque<Long>> requestMap = new HashMap<>();

    @Override
    public LimitResponse acceptRequest(Request request) {
        String ip = request.getIp();

        if(! requestMap.containsKey(ip)) {
            requestMap.put(ip, new LinkedBlockingDeque<>());
        }

        BlockingDeque blockingDeque = requestMap.get(ip);
        long epoch = System.currentTimeMillis();
        removeExpiredRequest(epoch, blockingDeque);

        LimitResponse limitResponse = new LimitResponse();
        if (blockingDeque.size() >= limit) {
            limitResponse.setCode(429);
            limitResponse.setMessage("Too many request");
            return  limitResponse;
        }

        blockingDeque.addFirst(System.currentTimeMillis());
        limitResponse.setMessage("request success");
        limitResponse.setCode(200);
        return limitResponse;
    }

    private void removeExpiredRequest(long epoch, BlockingDeque<Long> blockingDeque) {
       // long epoch =
        while (blockingDeque.getLast() < epoch - 1000L) {
            blockingDeque.removeLast();
        }
    }


}
