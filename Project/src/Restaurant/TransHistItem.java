package Restaurant;

public class TransHistItem {
    private String item;
    private int quantity;
    private double price;
    public TransHistItem(String item,int quantity,double price){
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }
    public String getItem(){return this.item;}

    public void setItem(String item){
        this.item = item;
    }

    public int getQuantity(){return this.quantity;}

    public void setQuantity(int quantity){
        this.quantity += quantity;
    }

    public double getPrice(){return this.price;}

    public void setPrice(double price){
        this.price = price;
    }

}
