package com.personal.designPattern.factory.lb.src.bo;

public class Node {
    int id;

    String ip;

    int threshold;

    int requestServed;

    public Node(int id, String ip, int threshold) {
        this.id = id;
        this.ip = ip;
        this.threshold = threshold;
        requestServed = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getRequestServed() {
        return requestServed;
    }

    public void setRequestServed(int requestServed) {
        this.requestServed = requestServed;
    }

    public boolean addRequest() throws InterruptedException {

        if (requestServed < threshold) {
            requestServed++;
            Thread.sleep(1 * 100);
            return true;
        } else {
            return false;
        }
    }

    public void deleteRequest(Request request) {
        requestServed--;
        return;
    }

}
