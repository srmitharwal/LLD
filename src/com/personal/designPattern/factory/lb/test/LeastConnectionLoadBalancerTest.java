package com.personal.designPattern.factory.lb.test;

import com.personal.designPattern.factory.lb.src.LeastConnectionLoadBalancer;
import com.personal.designPattern.factory.lb.src.bo.Node;
import com.personal.designPattern.factory.lb.src.bo.Request;
import com.personal.designPattern.factory.lb.src.registry.ServiceRegistry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

public class LeastConnectionLoadBalancerTest {

    @Mock
    private ServiceRegistry serviceRegistry;

    private LeastConnectionLoadBalancer leastConnectionLoadBalancer;

    Request request;
    String str = "profile";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        leastConnectionLoadBalancer = new LeastConnectionLoadBalancer();
        request = new Request(1, str);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNode() throws Exception {
        when(ServiceRegistry.getInstance()).thenReturn(serviceRegistry);
        when(serviceRegistry.getNodes(str)).thenReturn(creatNode());
        Node node = leastConnectionLoadBalancer.getNode(request);
        Assert.assertNotNull(node);
        Assert.assertEquals(node.getId(), 1);
    }

    private Set<Node> creatNode(){
        Node node1 = new Node(1, "192.168.1.1",10);
        Node node2 = new Node(1, "192.168.1.1",2);
        Set<Node> set = new HashSet<>();
        set.add(node1);
        set.add(node2);
        return set;
    }
}