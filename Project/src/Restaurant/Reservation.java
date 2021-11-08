package Restaurant;

public class Reservation {
    private String name;
    private int pax;
    private String tel;

    Reservation(String name, int pax, String tel) {
        this.name = name;
        this.tel = tel;
        this.pax = pax;
    }

    //when you call reservation.toString() --> prints the information
    public String toString() {
        return String.format("%d persons at name: %s, telephone: %s", pax, name, tel);
    }
}
