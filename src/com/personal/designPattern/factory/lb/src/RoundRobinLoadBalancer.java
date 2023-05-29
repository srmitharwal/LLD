package com.personal.designPattern.factory.lb.src;

import com.personal.designPattern.factory.lb.src.bo.Node;
import com.personal.designPattern.factory.lb.src.bo.Request;
import com.personal.designPattern.factory.lb.src.registry.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoundRobinLoadBalancer implements LoadBalancer {

    static int lastNode = -1;

    @Override
    public Node getNode(Request request) throws Exception {
        Set<Node> nodes = ServiceRegistry.getInstance().getNodes(request.getType());

        List<Node> nodesList = new ArrayList<>();

        nodesList.addAll(nodes);


        synchronized (RoundRobinLoadBalancer.class) {
  //          System.out.println(Thread.currentThread().getName());
//            for(int i = 0; i < nodesList.size(); i++) {
//                System.out.print(nodesList.get(i).getIp() + " ");
//            }

            lastNode += 1;
            lastNode = lastNode % nodesList.size();
           // System.out.println("Thread Name: " + Thread.currentThread().getName() + " prevNode: " + prevNode + " currentNode " + lastNode);
            return nodesList.get(lastNode);
        }


    }
}
