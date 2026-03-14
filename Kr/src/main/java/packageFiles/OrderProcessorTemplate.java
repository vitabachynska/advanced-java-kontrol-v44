package packageFiles;

public abstract class OrderProcessorTemplate {

    protected PaymentMethod paymentMethod;

    protected OrderProcessorTemplate(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public final void processOrder(Order order) {
        validate(order);
        calculate(order);
        pay(order);
    }

    protected abstract void validate(Order order);

    protected void calculate(Order order) {
        double total = 0;
        for (OrderItem item : order.getItems()) {
            total += item.getPrice();
        }
        order.setTotalAmount(total);
    }
    protected void pay(Order order) {
        paymentMethod.pay(order.getTotalAmount());
    }
}
