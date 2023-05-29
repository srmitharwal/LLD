package com.personal.designPattern.factory.lb.src.registry;

import com.personal.designPattern.factory.lb.src.bo.Node;

import java.util.*;

public class ServiceRegistry {
    private static Map<String, Set<Node>> serviceMap;

    private static ServiceRegistry serviceRegistry = null;

    private ServiceRegistry (){

    }

    public static ServiceRegistry getInstance() {
        if (serviceRegistry == null) {

            synchronized (ServiceRegistry.class) {
                if (serviceRegistry == null) {
                    serviceRegistry = new ServiceRegistry();
                    serviceMap = new HashMap<>();
                }
            }
        }
        return serviceRegistry;
    }

    public boolean addService(Node node, String serviceType) {
        if ( !serviceMap.containsKey(serviceType) ) {
            serviceMap.put(serviceType, new HashSet<>());
        }

        return serviceMap.get(serviceType).add(node);
    }

    public boolean removeService(Node node, String serviceType) {
        if ( serviceMap.containsKey(serviceType) ) {
            return serviceMap.get(serviceType).remove(node);
        }
        return false;
    }

    public Set<Node> getNodes(String serviceType) {
        HashSet<Node> set = new HashSet<>();
        set.addAll(serviceMap.get(serviceType));

        return set;
    }
}
