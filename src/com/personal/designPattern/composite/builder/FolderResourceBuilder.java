package com.personal.designPattern.composite.builder;

import com.personal.designPattern.composite.entity.FolderResource;

public class FolderResourceBuilder {
    FolderResourceBuilder folderResourceBuilder;
    private String name;

    public FolderResourceBuilder name(String name) {
        this.name = name;
        return this;
    }


    public FolderResource build() {
        FolderResource folderResource = new FolderResource();
        folderResource.setName(name);
        return folderResource;
    }
}
