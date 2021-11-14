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
        delivery.setDriver(this);
    }

    public Order getDelivery() {
        return this.delivery;
    }

    public void deleteDelivery() {
        this.delivery = null;
    }

    public void completeOrder() {
        this.delivery.setStatus("delivered");
    }

    public void cancelDelivery() {
        this.delivery.setStatus("paid");
    }

    @Override
    public void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("---------------------------------------------------");
            System.out.println("Active Driver : " + getName());
            System.out.println("---------------------------------------------------\n");
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
            cls();
            System.out.println("------------------------DRIVER MENU------------------------");
            System.out.println();
            System.out.println("Take Delivery (t) | Delivery Complete (c) | Cancel Delivery (x) |  Logout (l)");
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
                                    o.infoAll();
                                    System.out.println();
                                    index++;
                                }
                            }
                        }
                        if (index != 0) {

                            boolean error = false;
                            int indexPick = -1;
                            do {
                                try {
                                    System.out.print("Pick order no : ");
                                    input = myObj.nextLine();
                                    indexPick = Integer.parseInt(input) - 1;
                                    error = false;

                                } catch (Exception e) {
                                    error = true;
                                }
                            } while (error);

                            for (Customer c : listCustomer) {
                                for (Order o : c.getListOrder()) {
                                    if (o.getStatus().equals("paid")) {
                                        if (indexSearch == (indexPick)) {
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
                            System.out.print("There is no order! Press any key to continue!");

                            input = myObj.nextLine();
                        }

                    } else {
                        cls();

                        System.out.print("Complete your delivery first! Press any key to continue!");
                        input = myObj.nextLine();
                    }

                    break;
                case "c":
                    cls();
                    if (Objects.isNull(this.delivery)) {
                        System.out.print("You have not taken any order yet! Press any key to continue!");
                        input = myObj.nextLine();

                    } else {
                        System.out.println("Thank you for your hardwork!");
                        System.out.println("You Receive : Rp." + calculate());
                        System.out.println("Press any key to continue!");

                        input = myObj.nextLine();
                        completeOrder();
                        deleteDelivery();
                    }

                    break;
                case "x":
                    cls();
                    if (Objects.isNull(this.delivery)) {
                        System.out.println("You have not taken any order yet! Press any key to continue!");
                        input = myObj.nextLine();

                    } else {
                        System.out.println("Delivery Cancelled! Press any key to continue!");
                        input = myObj.nextLine();
                        cancelDelivery();
                        deleteDelivery();
                    }

                    break;
            }
        } while (!input.equals("l"));
    }
}
