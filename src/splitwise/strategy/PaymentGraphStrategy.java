package splitwise.strategy;

import splitwise.models.Group;

public interface PaymentGraphStrategy {

    public void makePaymentGraph(Group group);
}
