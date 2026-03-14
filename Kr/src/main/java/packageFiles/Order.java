package packageFiles;

import java.util.Arrays;
import java.util.Optional;

public class Order {
    private String id;
    private OrderItem[] items;
    private double totalAmount;
    private OrderStatus status = OrderStatus.NEW;
    private boolean isFirstOrder;

    public Order(String id, OrderItem[] items) {
        this.id = id;
        this.items = items != null ? items.clone() : new OrderItem[0];
    }
    private Order[] orders; // Ваш масив замовлень (вимога про роботу з масивом)

    public Optional<Order> findById(String id) {
        return Arrays.stream(orders)
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }
    public String getId() { return id; }

    public OrderItem[] getItems() {
        return items.clone();
    }
    public boolean isFirstOrder() { return isFirstOrder; }
    public void setFirstOrder(boolean firstOrder) { isFirstOrder = firstOrder; }

    public void setStatus(OrderStatus status) { this.status = status; }
    public OrderStatus getStatus() { return status; }

    public double getTotalAmount() { this.totalAmount = totalAmount;
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public enum OrderStatus {
        NEW,
        PAID,
        SHIPPED,
        DELIVERED
    }
}
