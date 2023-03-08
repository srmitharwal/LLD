package com.personal.designPattern.factory.lb.src.bo;

import java.util.HashMap;
import java.util.Map;

public class Request {
    String type;
    int id;
    Map<String, String> map;

    public Request(int id, String type) {
        this.id = id;
        this.type = type;
        map = new HashMap<>();
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
