package Restaurant;

/**
 * this is an entity class
 * an instance of this object represents a single Reservation
 */
public class Reservation {
    /**
     * the name of the person making this reservation
     */
    private String name;

    /**
     * the number of guests this reservation is being made for
     */
    private int pax;

    /**
     * the telephone number of the person making this reservation
     */
    private int tel;

    /**
     * constructor where the information from customer is assigned to attributes
     * @param name name of the person making this reservation
     * @param pax number of guests this reservation is being made for
     * @param tel telephone number of the person making this reservation
     */
    public Reservation(String name, int pax, int tel) {
        this.name = name;
        this.tel = tel;
        this.pax = pax;
    }

    //getters

    /**
     *
     * @return the number of people this reservation was made for
     */
    public int getPax() {
        return pax;
    }

    /**
     *
     * @return the name of the person who made the reservation
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the telephone number of the person who made the reservation
     */
    public int getTel() {
        return tel;
    }
    
    /**
     * converts the information in this reservation into a compiled string
     * @return a string that can be printed to display the reservation information
     */
    public String toString() {
        return String.format("Reservation for: %s\nTelephone number: %d\nNumber of guests: %d\n", name, tel, pax);
    }
}
