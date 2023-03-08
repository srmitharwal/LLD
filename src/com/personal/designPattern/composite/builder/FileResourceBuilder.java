package com.personal.designPattern.composite.builder;

import com.personal.designPattern.composite.entity.FileResource;

public class FileResourceBuilder {
    FileResource fileResource;

    private String name;
    private int size;

    public FileResourceBuilder name(String name) {
        this.name = name;
        return this;
    }

    public FileResourceBuilder size(int size) {
        this.size = size;
        return  this;
    }

    public FileResource build() {
        fileResource = new FileResource();
        fileResource.setName(name);
        fileResource.setSize(size);
        return fileResource;
    }

}
