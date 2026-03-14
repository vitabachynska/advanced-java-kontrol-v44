package packageFiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface PaymentMethod {
    void pay(double amount);
}

class PayPalPayment implements PaymentMethod {
    private static final Logger log = LoggerFactory.getLogger(PayPalPayment.class);
    public void pay(double amount) {
        if(amount<200){
            log.warn("Log: amount<200");
            throw new PaymentException("Мінімальна сума 200 грн");}
        System.out.println("Оплата через PayPal: " + amount);
    }
}
class CardPayment implements PaymentMethod {
    public void pay(double amount) {
        if(amount>25000){
            throw new PaymentException("Сума має бути менша 25000 грн");}
        System.out.println("Оплата картою: " + amount);
    }
}
class BankTransferPayment implements PaymentMethod {
    public void pay(double amount) {
        double total = amount * 1.015;
        System.out.println("Банківський переказ на суму " + total + " (з комісією 1,5%)");
    }
}

