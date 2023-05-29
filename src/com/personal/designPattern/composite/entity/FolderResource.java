package com.personal.designPattern.composite.entity;

import java.util.ArrayList;
import java.util.List;

public class FolderResource implements Resource {
    List<Resource> resourceList;
    private String name;

    public FolderResource(){
        resourceList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        int size = 0;

        for(Resource resource : resourceList){
            size += resource.getSize();
        }
        return size;
    }

    @Override
    public List<Resource> getALLResources() {
        return resourceList;
    }

    public void addResource(Resource resource) {
        resourceList.add(resource);
    }

}
