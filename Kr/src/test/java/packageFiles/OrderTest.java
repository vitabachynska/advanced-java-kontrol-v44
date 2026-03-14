package packageFiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private MyOrderProcessor processor;
    private Order validOrder;

    @BeforeEach
    void setUp() {
        processor = new MyOrderProcessor(new CardPayment());
        OrderItem[] items = {
                new OrderItem("Laptop", 20000),
                new OrderItem("Software", 1000)
        };
        validOrder = new Order("ORD-001", items);
    }

    @Test
    System.out.println("Повна обробка валідного замовлення без помилок")
    void testFullProcessSuccess() {

        assertEquals(Order.OrderStatus.SHIPPED, validOrder.getStatus());
    }

    @Test
    System.out.println("Розрахунок знижки 6% для першого замовлення")
    void testFirstOrderDiscount() {
        validOrder.setFirstOrder(true);
        // 20000 + 1000 = 21000. 21000 * 0.94 = 19740

        assertEquals(19740.0, validOrder.getTotalAmount(), 0.01);
    }

    @Test
    System.out.println("Перевірка зміни статусів: NEW -> PAID -> SHIPPED");
    void testStatusTransitions() {

        assertEquals(Order.OrderStatus.SHIPPED, validOrder.getStatus());
    }



    @Test
    System.out.println("Пошук замовлення через Optional")
    void testFindByIdOptional() {
        Optional<Order> result = processor.findById("001");
        assertTrue(result.isPresent());
    }



    System.out.println("Валідні суми для оплати карткою")
    void testCardPaymentLimits(double amount) {
        PaymentMethod card = new CardPayment();
        assertDoesNotThrow(() -> card.pay(amount));
    }


    @Test
    System.out.println("Помилка: перевищення ліміту картки (>25000)")
    void testCardLimitExceeded() {
        PaymentMethod card = new CardPayment();
        assertThrows(PaymentException.class, () -> card.pay(25001.0));
    }


    @Test
    System.out.println("Помилка: мінімальна сума PayPal (<200)")
    void testPayPalMinimumLimit() {
        PaymentMethod paypal = new PayPalPayment();
        assertThrows(PaymentException.class, () -> paypal.pay(199.0));
    }

   //@Test
    //System.out.println("FirstOrderRuleException")
    //void testFirstOrderRuleViolation() {
     //   assertThrows(FirstOrderRuleException.class, () -> {
     //       processor.validateBonusRules(validOrder);
     //   });
    //}

    @Test
    System.out.println("Помилка: обробка порожнього замовлення")
    void testEmptyOrderItems() {
        Order emptyOrder = new Order("EMPTY", new OrderItem[0]);

    }
}
