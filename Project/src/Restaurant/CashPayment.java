package Restaurant;


/**
 * An instance of this class represents a cash payment for orders of table(s)
 * Subclass of Payment; specific for cash payments
 */
class CashPayment extends Payment {
    /**
     * Cash paid for this payment
     */
    private double cashPaid;

    /**
     * Constructor that uses CashPayment's superclass constructor (Payment) to instantiate its variables
     */
    public CashPayment() {
        super();
        this.cashPaid = 0;
    }

    /**
     * Gets cash paid for this cash payment
     * @return Returns cash paid for this cash payment as a double
     */
    public double getCashPaid() {
        return cashPaid;
    }

    /**
     * Gets change returned for this payment
     * @return Returns cash returned for this payment as a double
     */
    public double getChange() {
        return this.cashPaid-(this.subtotal+this.tax-this.discountApplied);
    }

    /**
     * Sets cash paid for this cash payment
     * @param cashPaid Returns cash paid for this cash payment as a double
     */
    public void setCashPaid(double cashPaid) {
        this.cashPaid = cashPaid;
    }

}


