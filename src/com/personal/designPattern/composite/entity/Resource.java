package com.personal.designPattern.composite.entity;

import java.util.List;

public interface Resource {
    public String getName();
    public int getSize();
    public List<Resource> getALLResources();
}
