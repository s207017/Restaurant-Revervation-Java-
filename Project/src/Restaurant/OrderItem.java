package Restaurant;

public class OrderItem {
    /**
     * Declaring a reference of MenuItem class
     */
    private MenuItem item;
    /**
     * Declaring an integer for quantity ordered
     */
    private int quantityOrdered;
    /**
     * Declaring an integer for quantity of completed order
     */
    private int quantityCompleted;

    /**
     * Constructor. assigns item to item and quantityOrdered to quantityOrdered
     */
    public OrderItem(MenuItem item, int quantityOrdered) {
        this.item = item;
        this.quantityOrdered = quantityOrdered;
        this.quantityCompleted = 0;
    }

    /**
     * Getter
     * @return item object stored in this class
     */
    public MenuItem getItem() {
        return item;
    }

    /**
     * Getter
     * @return integer quantityOrdered stored in this class
     */
    public int getQuantityOrdered(){
        return quantityOrdered;
    }

    /**
     * Getter
     * @return  integer quantityCompleted stored in this class
     */
    public int getQuantityCompleted() {
        return quantityCompleted;
    }


    /**
    *   Adds quantity of orders added to quantityOrdered
     * */
    public void addQuantityOrdered(int qty){
        this.quantityOrdered=this.quantityOrdered+qty;
    }

    /**
     *  subtracts quantity of orders for this order item
     */
    public void subtractQuantityOrdered(int qty) {this.quantityOrdered = this.quantityOrdered - qty;}


}
