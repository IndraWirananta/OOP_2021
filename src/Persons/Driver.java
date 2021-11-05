package Persons;

import Orders.Order;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;

public class Driver extends Person implements PersonInterface {

    private String affiliation;
    private Order delivery;

    public Driver(String name, String gender, String phone, String password, String affiliation) {
        super(name, gender, phone, password);
        this.affiliation = affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getAffiliation() {
        return this.affiliation;
    }

    public void setDelivery(Order delivery) {
        this.delivery = delivery;
        delivery.setStatus("en route");
    }

    public Order getDelivery() {
        return this.delivery;
    }

    private void deleteDelivery() {
        this.delivery = null;
    }

    public void completeOrder() {
        this.delivery.setStatus("delivered");
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
    public void info() {
        System.out.println("Name \t\t: " + getName());
        System.out.println("Gender \t\t: " + getGender());
        System.out.println("Phone \t\t: " + getPhone());
        System.out.println("Affiliation \t: " + getAffiliation());
    }

    @Override
    public double calculate() {
        return this.delivery.getTotalPrice() * DELIVERY_CHARGES;
    }

    public void menu(List<Customer> listCustomer) {
        Scanner myObj = new Scanner(System.in);
        String input;
        do {
            System.out.println("------------------------DRIVER MENU------------------------");
            System.out.println();
            System.out.println("Welcome User : " + getName());
            System.out.println();
            System.out.println("Take Delivery (t) | Delivery Complete (c) | Logout (l)");
            System.out.print("Input : ");
            input = myObj.nextLine();
            System.out.println();
            switch (input) {
                case "t":
                    if (Objects.isNull(this.delivery)) {
                        cls();
                        System.out.println("----------------------- Take Order -----------------------\n");
                        int index = 0;
                        int indexSearch = 0;
                        boolean orderTaken = false;
                        for (Customer c : listCustomer) {
                            for (Order o : c.getListOrder()) {
                                if (o.getStatus().equals("paid")) {
                                    System.out.println(String.format("Orders no %s", index + 1));
                                    o.info();
                                    System.out.println();
                                    index++;
                                }
                            }
                        }
                        if (index != 0) {
                            System.out.print("Pick order no : ");
                            input = myObj.nextLine();
                            for (Customer c : listCustomer) {
                                for (Order o : c.getListOrder()) {
                                    if (o.getStatus().equals("paid")) {
                                        if (indexSearch == (Integer.parseInt(input) - 1)) {
                                            setDelivery(o);
                                            orderTaken = true;
                                            break;
                                        } else {
                                            indexSearch++;
                                        }
                                    }
                                }
                            }
                            if (orderTaken) {
                                System.out.println("Order taken! Press any key to continue!");
                                input = myObj.nextLine();
                                cls();
                            } else {
                                System.out.println("Order not found! Press any key to continue!");
                                input = myObj.nextLine();
                                cls();
                                System.out.println();
                            }

                        } else {
                            System.out.println("There is no order which needs delivery right now!");
                            System.out.println();
                        }

                    } else {
                        cls();
                        System.out.println();
                        System.out.println("Complete your delivery first!");
                        System.out.println();
                    }

                    break;
                case "c":
                    cls();
                    if (Objects.isNull(this.delivery)) {
                        System.out.println("You have not taken any order yet!");
                        System.out.println();
                    } else {
                        System.out.println("Thank you for your hardwork!");
                        System.out.println();
                        completeOrder();
                        deleteDelivery();
                    }

                    break;
            }
        } while (!input.equals("l"));
    }
}
