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
}
