package Restaurant;

public class Reservation {
    private String name;
    private int pax;
    private String tel =null;

    public Reservation(String name, int pax, String tel) {
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

    public String getTel() {
        return tel;
    }

    //when you call reservation.toString() --> prints the information
    public String toString() {
        return String.format("%d persons for %s, telephone: %s", pax, name, tel);
    }
}
