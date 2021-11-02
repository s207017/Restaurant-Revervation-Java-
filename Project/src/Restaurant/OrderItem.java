<<<<<<< Updated upstream
package Restaurant;

public class OrderItem {
    private MenuItem item;
    private int quantityOrdered=0;
    private int quantityCompleted=0;

    //constructor. assigns item to item and quantityOrdered to quantityOrdered
    public OrderItem(MenuItem _item, int quantityOrdered) {
        item = _item;
        this.quantityOrdered = quantityOrdered;
    }


    public MenuItem getItem() {
        return item;
    }

    public int getQuantityOrdered(){
        return quantityOrdered;
    }

    public int getQuantityCompleted() {
        return quantityCompleted;
    }
    public int orderCompleted(){
        if (quantityOrdered-quantityCompleted==0){
            return 1;
        }
        else return 0;
        //returns 1 when all orders are completed
        //returns 0 when orders are not completed
    }

    //adds quantity of orders added to quantityOrdered
    public void addQuantityOrdered(int qty){
        this.quantityOrdered=this.quantityOrdered+qty;
    }

    //subtracts quantity of orders for this order item
    public void subtractQuantityOrdered(int qty) {this.quantityOrdered = this.quantityOrdered - qty;}

    //adds quantity of orders completed to quantityCompleted
    public void setQuantityCompleted(int qty){
        this.quantityCompleted=this.quantityCompleted+qty;
    }
}
=======
//package Restaurant;
//
//public class OrderItem {
//    private MenuItem item;
//    private int quantityOrdered=0;
//    private int quantityCompleted=0;
//
//    //constructor. assigns item to item and quantityOrdered to quantityOrdered
//    public OrderItem(MenuItem _item, int quantityOrdered) {
//        item = _item;
//        this.quantityOrdered = quantityOrdered;
//    }
//
//    public MenuItem getItem() {
//        return item;
//    }
//
//    public int getQuantityOrdered(){
//        return quantityOrdered;
//    }
//
//    public int getQuantityCompleted() {
//        return quantityCompleted;
//    }
//    public int orderCompleted(){
//        if (quantityOrdered-quantityCompleted==0){
//            return 1;
//        }
//        else return 0;
//        //returns 1 when all orders are completed
//        //returns 0 when orders are not completed
//    }
//
//    //adds quantity of orders added to quantityOrdered
//    public void setQuantityOrdered(int qty){
//        this.quantityOrdered=this.quantityOrdered+qty;
//    }
//
//    //adds quantity of orders completed to quantityCompleted
//    public void setQuantityCompleted(int qty){
//        this.quantityCompleted=this.quantityCompleted+qty;
//    }
//}
>>>>>>> Stashed changes
