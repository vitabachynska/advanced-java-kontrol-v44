package packageFiles;

public class Order {
    private String id;
    private OrderItem[] items;
    private double totalAmount;

    public Order(String id, OrderItem[] items) {
        this.id = id;
        this.items = items != null ? items.clone() : new OrderItem[0];
    }

    public OrderItem[] getItems() {
        return items.clone();
    }

    public double getTotalAmount(){return totalAmount;}
    public void setTotalAmount(double total){this.totalAmount=totalAmount;}
}
