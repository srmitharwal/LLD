package com.personal.designPattern.rateLimiter.sw;

import com.personal.designPattern.rateLimiter.RateLimiter;
import com.personal.designPattern.rateLimiter.models.LimitResponse;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingRateLimiter implements RateLimiter {
    private int limit;
    private long timeLimit;
    private Queue<Long> slidingWindow;

    public SlidingRateLimiter(int limit, long timeLimit) {
        this.limit = limit;
        this.timeLimit = timeLimit;
        slidingWindow = new ConcurrentLinkedDeque<>();
    }

    @Override
    public LimitResponse grantAccess() {
        long requestTime  = System.currentTimeMillis();
        updateQueue(requestTime);
        //System.out.println(Thread.currentThread().getName() + " " + slidingWindow.size() + " " + System.currentTimeMillis());
        LimitResponse limitResponse = new LimitResponse();
        if (slidingWindow.size() >= limit) {
            limitResponse.setCode(429);
            limitResponse.setMessage("Too many request");
            return  limitResponse;
        }

        slidingWindow.offer(requestTime);
        limitResponse.setMessage("request success");
        limitResponse.setCode(200);
        return limitResponse;
    }

    private void updateQueue(Long requestTime) {
        while ( !slidingWindow.isEmpty() && (requestTime - slidingWindow.peek())/1000 >= timeLimit) {
            slidingWindow.poll();
        }
        return;
    }


}
