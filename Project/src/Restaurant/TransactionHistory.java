package Restaurant;

import java.util.ArrayList;

public class TransactionHistory {
    private ArrayList<Payment> payments;
    public TransactionHistory(){
        payments = new ArrayList<>();
    }

    public void addEntry(Payment payment){
        payments.add(payment);
    }
}
