package Restaurant;

public class Reservation {
    private String name;
    private int pax;
    private int tel;

    public Reservation(String name, int pax, int tel) {
        this.name = name;
        this.tel = tel;
        this.pax = pax;
    }

    //getters

    public int getPax() {
        return pax;
    }

    public String getName() {
        return name;
    }

    public int getTel() {
        return tel;
    }

    //when you call reservation.toString() --> prints the information
    public String toString() {
        return String.format("Reservation for: %s\nTelephone number: %d\nNumber of guests: %d\n", name, tel, pax);
    }
}
