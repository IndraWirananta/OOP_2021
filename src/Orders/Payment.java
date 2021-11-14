package Orders;

import java.io.Serializable;
import java.util.*;

public class Payment implements Serializable {

    private Date paymentDate;
    private String cardNumber;

    public Payment(String cardNumber) {
        this.paymentDate = new Date();
        this.cardNumber = cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public Date getPaymentDate() {
        return this.paymentDate;
    }
}
