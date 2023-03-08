package com.personal.designPattern.factory.lb.src;

import com.personal.designPattern.factory.lb.src.bo.Node;
import com.personal.designPattern.factory.lb.src.bo.Request;

public interface LoadBalancer {
    public Node getNode(Request request) throws Exception;
}
