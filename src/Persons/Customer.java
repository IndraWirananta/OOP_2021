package Persons;

import Items.Makanan;
import Items.Minuman;
import Items.Snack;
import Orders.Order;
import Orders.Payment;
import java.util.*;

public class Customer extends Person implements PersonInterface {

    private String address;
    private List<Order> listOrder = new ArrayList<Order>();

    public Customer(String name, String gender, String phone, String password, String address) {
        super(name, gender, phone, password);
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void addListOrder(Order order) {
        this.listOrder.add(order);
    }

    public List<Order> getListOrder() {
        return this.listOrder;
    }

    @Override
    public void info() {
        System.out.println("Name \t: " + getName());
        System.out.println("Gender \t: " + getGender());
        System.out.println("Phone \t: " + getPhone());
        System.out.println("Address \t: " + getAddress());
    }

    public void completeOrder(int index) {
        int i = 1;
        boolean isDone = false;
        for (Order x : listOrder) {
            if (x.getStatus().equals("active")) {
                if (i == index) {

                    System.out.println("Total Payment : Rp." + (Math.round(calculate() * x.getTotalPrice()) + x.getTotalPrice()));
                    Scanner myObj = new Scanner(System.in);
                    String input;

                    System.out.print("Insert Card number, [cancel] to cancel : ");
                    input = myObj.nextLine();
                    if (input.equals("cancel")) {
                        System.out.println("Payment cancelled! Press any key to continue!");
                        input = myObj.nextLine();
                        isDone = true;
                        break;
                    } else {
                        System.out.println("Payment completed! Press any key to continue!");
                        input = myObj.nextLine();
                        isDone = true;
                        Payment payment = new Payment(input);
                        x.setPayment(payment);
                        x.setStatus("paid");
                        break;
                    }
                } else {
                    i++;
                }
            }
        }
        if (!isDone) {
            cls();
            System.out.println("Order not found! Please select the correct order!");
            System.out.println();
        }

    }

    @Override
    public void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    @Override
    public double calculate() {
        return DELIVERY_CHARGES + TAXES;
    }

    public void menu(List< Makanan> lm, List< Minuman> ld, List< Snack> ls) {
        Scanner myObj = new Scanner(System.in);
        String input;
        do {
            System.out.println("------------------------CUSTOMER MENU------------------------\n");
            System.out.println("Welcome User : " + getName());
            System.out.println();
            System.out.println("View Order (v) | New Order(n) | Pay Order (p) | Logout (l)");
            System.out.print("Input : ");
            input = myObj.nextLine();
            switch (input) {
                case "v":
                    cls();
                    System.out.println("----------------------- View Order -----------------------\n");
                    int i;
                    for (i = 0; i < getListOrder().size(); i++) {
                        System.out.println("----------- Order No." + (i + 1) + "-----------");
                        getListOrder().get(i).infoAll();
                        System.out.println();
                    }
                    if (i == 0) {
                        cls();
                        System.out.println("You have no order!\n");

                        break;
                    }
                    System.out.println("Payment completed! Press any key to continue!");
                    input = myObj.nextLine();
                    break;
                case "n":
                    for (int x = 0; x < lm.size(); x++) {
                        System.out.println("--------Makanan no." + (x + 1) + "--------------");
                        lm.get(x).info();
                        System.out.println();
                    }
                    System.out.println();
                    for (int x = 0; x < ld.size(); x++) {
                        System.out.println("--------Minuman no." + (x + 1) + "--------------");
                        ld.get(x).info();
                        System.out.println();

                    }
                    System.out.println();
                    for (int x = 0; x < ls.size(); x++) {
                        System.out.println("--------Snack no." + (x + 1) + "--------------");
                        ls.get(x).info();
                        System.out.println();
                    }
                    System.out.println();
                    Order newOrder = new Order(this);
                    do {
                        System.out.print("FOOD [m] | DRINK [d] | SNACK [s] | done [done] : ");
                        input = myObj.nextLine();
                        System.out.println();
                        int number;

                        switch (input) {
                            case "m":
                                System.out.print("Insert Food Number : ");

                                number = Integer.parseInt(myObj.nextLine()) - 1;
                                System.out.println();

                                try {
                                    newOrder.addFood(lm.get(number));
                                } catch (IndexOutOfBoundsException exception) {
                                    System.out.println("ITEM NOT FOUND");
                                }
                                break;
                            case "d":
                                System.out.print("Insert Drink Number : ");
                                System.out.println();
                                number = Integer.parseInt(myObj.nextLine()) - 1;

                                try {
                                    newOrder.addDrink(ld.get(number));
                                } catch (IndexOutOfBoundsException exception) {
                                    System.out.println("ITEM NOT FOUND");
                                }
                                break;
                            case "s":
                                System.out.print("Insert Snack Number : ");
                                System.out.println();
                                number = Integer.parseInt(myObj.nextLine()) - 1;

                                try {
                                    newOrder.addSnack(ls.get(number));
                                } catch (IndexOutOfBoundsException exception) {
                                    System.out.println("ITEM NOT FOUND");
                                }
                                break;
                            default:
                                System.out.println("ITEM NOT FOUND");
                                System.out.println();
                                break;
                        }
                        System.out.println("---------Your Order----------\n");
                        newOrder.info();
                        System.out.println();
                    } while (!input.equals("done"));
                    if (!newOrder.isOrderEmpty()) {
                        addListOrder(newOrder);
                    } else {
                        System.out.println("Canceling order!");
                    }
                    break;
                case "p":
                    cls();
                    do {

                        System.out.println("-------------- Order Payment --------------");
                        System.out.println();
                        i = 0;
                        for (Order x : getListOrder()) {
                            if (x.getStatus().equals("active")) {
                                System.out.println("Order No." + (i + 1));
                                x.infoForCustomer();
                                i++;
                            }
                        }
                        if (i == 0) {
                            cls();
                            System.out.println("All order has been paid!\n");
                            break;
                        }
                        System.out.println();
                        System.out.print("Pay order no? [cancel] to cancel : ");
                        input = myObj.nextLine();

                        try {
                            int index = Integer.parseInt(input);
                            completeOrder(index);
                        } catch (NumberFormatException e) {
                            if (input.equals("cancel")) {
                                System.out.println("Canceling...");
                            } else {
                                System.out.println("Unknown input!");
                            }
                        }
                    } while (!input.equals("cancel"));
                    break;
            }
        } while (!input.equals("l"));
    }
}