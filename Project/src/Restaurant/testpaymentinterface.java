//package Restaurant;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class testpaymentinterface {
//
//
//    public testpaymentinterface() throws IOException {
//    }
//
//    public static void main(String[] args) throws IOException {
//         Menu menu = new Menu();
//         Staff staff = new Staff();
//         Restaurant r = new Restaurant();
//         Membership m = new Membership();
//         ArrayList<TransHistDay> hist = new ArrayList<TransHistDay>();
//
//        OrderItem oi = new OrderItem(menu.getMenuItemFromID(101),2);
//        staff.setStaffID();
//        //Order order = new Order(staff.getStaffID(),2);
//        //order.addOrderItems(menu);
//        //r.getTableFromTableNum(2).setOrder(order);
//        PaymentInterface pay = new PaymentInterface(r,m,hist);
//        pay.selectPaymentMethod();
//        pay.selectTable();
//        pay.showAmount();
//        pay.checkMembership();
//        pay.makePayment();
//        pay.generateReceipt();
//        pay.addToHistory();
//    }
//}
