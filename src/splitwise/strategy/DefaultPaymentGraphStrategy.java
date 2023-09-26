package splitwise.strategy;

import org.graalvm.collections.Pair;
import splitwise.models.Group;
import splitwise.models.User;
import splitwise.models.UserBalanceInfo;

import java.util.*;

public class DefaultPaymentGraphStrategy implements PaymentGraphStrategy {

    static Map<String, Map<String, Double>> currencyMap = new HashMap<>();

    class Node {
        private String userId;

        private Double balance;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }
    }

    class CustomComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.getBalance() > o2.getBalance()) return -1;
            if (o1.getBalance() < o2.getBalance()) return 1;
            return 0;
        }
    }
    @Override
    public void makePaymentGraph(Group group) {

        PriorityQueue<Node> firstCategory = new PriorityQueue<>(new CustomComparator());
        PriorityQueue<Node> secondCategory = new PriorityQueue<>(new CustomComparator()); //We are storing the absoulte value

        Map<String, Map<String,Double>> userBalanceMap = group.getUserBalanceMap();

        for (Map.Entry<String, Map<String,Double>> entry : userBalanceMap.entrySet()) {
            Node node = new Node();
            node.setUserId(entry.getKey());
            double totalMoney = 0;
            Map<String, Double> balanceMap = userBalanceMap.get(entry.getKey());
            for(Map.Entry<String, Double> balanceEntry : balanceMap.entrySet()) {
                totalMoney += currencyMap.get(balanceEntry.getKey()).get(group.getDefaultCurrency())*balanceEntry.getValue();
            }
            node.setBalance(totalMoney);
            if (node.getBalance() >= 0 )secondCategory.add(node);
            else firstCategory.add(node);
        }

        Map<String, List<UserBalanceInfo>> graph = new HashMap<>();

        while (firstCategory.size() > 0 ) {
            Node receiver  = secondCategory.peek();
            Node sender = firstCategory.peek();
            firstCategory.poll();
            secondCategory.poll();

            double amountToTransfer = Math.min(sender.getBalance(), receiver.getBalance());

            UserBalanceInfo forSender = new UserBalanceInfo();
            forSender.setBalance(amountToTransfer);
            forSender.setCreditor(sender.getUserId());
            forSender.setDebitor(receiver.getUserId());


            UserBalanceInfo forReciever = new UserBalanceInfo();
            forReciever.setBalance(amountToTransfer*-1);
            forReciever.setCreditor(receiver.getUserId());
            forReciever.setDebitor(sender.getUserId());

            List<UserBalanceInfo> userBalanceInfos = graph.getOrDefault(sender.getUserId(), new ArrayList<>());
            userBalanceInfos.add(forSender);
            graph.put(sender.getUserId(), userBalanceInfos);
            userBalanceInfos = graph.getOrDefault(receiver.getUserId(), new ArrayList<>());
            userBalanceInfos.add(forReciever);
            graph.put(receiver.getUserId(), userBalanceInfos);

            sender.setBalance(sender.getBalance() - amountToTransfer);
            receiver.setBalance(receiver.getBalance() + amountToTransfer);

            if (sender.getBalance() != 0) secondCategory.add(sender);
            if (receiver.getBalance() != 0 )firstCategory.add(receiver);
        }

        group.setBalanceMap(graph);

    }
}
