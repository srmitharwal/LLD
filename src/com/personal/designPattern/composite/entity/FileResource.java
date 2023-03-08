package com.personal.designPattern.composite.entity;

import java.util.List;

public class FileResource implements Resource {
    private String name;
    private int size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public List<Resource> getALLResources() {
        return null;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
