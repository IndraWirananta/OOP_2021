package Orders;

import Items.Makanan;
import Items.Minuman;
import Items.Snack;
import Persons.Customer;
import Persons.Driver;
import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {

    private String status = "active";
    private List<Makanan> listMakanan = new ArrayList<Makanan>();
    private List<Minuman> listMinuman = new ArrayList<Minuman>();
    private List<Snack> listSnack = new ArrayList<Snack>();
    private Payment payment;
    private Date date;
    private Customer customer;
    private Driver driver;

//  CONSTRUCTOR
    public Order(Customer customer) {
        this.date = new Date();
        this.customer = customer;
    }

//  SETTER
    public void addFood(Makanan food) {
        this.listMakanan.add(food);
    }

    public void addDrink(Minuman drink) {
        this.listMinuman.add(drink);
    }

    public void addSnack(Snack snack) {
        this.listSnack.add(snack);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

//  GETTER
    public List<Makanan> getFood() {
        return this.listMakanan;
    }

    public List<Minuman> getDrink() {
        return this.listMinuman;
    }

    public List<Snack> getSnack() {
        return this.listSnack;
    }

    public int getTotalPrice() {
        int total = 0;
        for (Makanan x : listMakanan) {
            total += x.getHarga();
        }
        for (Minuman y : listMinuman) {
            total += y.getHarga();
        }
        for (Snack z : listSnack) {
            total += z.getHarga();
        }
        return total;
    }

    public String getStatus() {
        return this.status;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public boolean isOrderEmpty() {
        return this.listMakanan.isEmpty() && this.listMinuman.isEmpty() && this.listSnack.isEmpty();
    }
//  OTHER

    public void info() {
        if (!this.listMakanan.isEmpty()) {
            System.out.println("Makanan \t:\n");
            for (Makanan x : this.listMakanan) {
                x.info();
                System.out.println();
            }
            System.out.println();
        }

        if (!this.listMinuman.isEmpty()) {
            System.out.println("Minuman \t:\n");
            for (Minuman x : this.listMinuman) {
                x.info();
                System.out.println();
            }
            System.out.println();
        }

        if (!this.listSnack.isEmpty()) {
            System.out.println("Snack \t:\n");
            for (Snack x : this.listSnack) {
                x.info();
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }

    public void infoAll() {
        if (!this.listMakanan.isEmpty()) {
            System.out.println("Makanan :\n");
            for (Makanan x : this.listMakanan) {
                x.info();
                System.out.println();
            }
            System.out.println();
        }

        if (!this.listMinuman.isEmpty()) {
            System.out.println("Minuman \t:\n");
            for (Minuman x : this.listMinuman) {
                x.info();
                System.out.println();

            }
            System.out.println();
        }

        if (!this.listSnack.isEmpty()) {
            System.out.println("Snack \t:\n");
            for (Snack x : this.listSnack) {
                x.info();
                System.out.println();
            }
            System.out.println();
        }

        System.out.println("Status \t\t: " + this.status);
        System.out.println("Customer \t: " + this.customer.getName());
        try {
            System.out.println("Driver \t\t: " + this.driver.getName());
        } catch (NullPointerException e) {
            System.out.println("Driver \t\t: Not Delivered Yet");
        }

        System.out.println("Price w/o taxes : " + getTotalPrice());

    }

    public void infoForCustomer() {
        if (!this.listMakanan.isEmpty()) {
            System.out.println("Makanan \t:\n");
            for (Makanan x : this.listMakanan) {
                x.info();
                System.out.println();
            }
            System.out.println();
        }

        if (!this.listMinuman.isEmpty()) {
            System.out.println("Minuman \t:\n");
            for (Minuman x : this.listMinuman) {
                x.info();
                System.out.println();
            }
            System.out.println();
        }

        if (!this.listSnack.isEmpty()) {
            System.out.println("Snack \t:\n");
            for (Snack x : this.listSnack) {
                x.info();
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Price w/o taxes : " + getTotalPrice());

    }
}
