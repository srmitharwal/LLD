package com.personal.designPattern.factory.lb.src;

import com.personal.designPattern.factory.lb.src.bo.Node;
import com.personal.designPattern.factory.lb.src.bo.Request;
import com.personal.designPattern.factory.lb.src.registry.ServiceRegistry;

import java.util.Set;

public class LeastConnectionLoadBalancer implements LoadBalancer {

    @Override
    public Node getNode(Request request) throws Exception {
        String serviceType = request.getType();
        return getLeastNode(serviceType);
    }

    private Node getLeastNode(String serviceType) throws Exception {
        Set<Node> nodeSet = ServiceRegistry.getInstance().getNodes(serviceType);
        if ( nodeSet.size() == 0) {
            throw new Exception("There is no service to serve this request type ");
        }

        int totalConnection  = Integer.MAX_VALUE;
        Node nodeWithLeastConnection = null;
        for (Node node : nodeSet) {
            if(node.getRequestServed() < totalConnection) {
                nodeWithLeastConnection = node;
                totalConnection = node.getRequestServed();
            }
        }

        nodeWithLeastConnection.addRequest();
        return nodeWithLeastConnection;
    }

}
