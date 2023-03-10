package com.personal.designPattern.rateLimiter.models;

import java.util.Map;

public class Request {
   int id;

   String ip;

   Map<String, String> map;

   public Request (int id, String ip){
       this.id = id;
       this.ip = ip;

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

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
