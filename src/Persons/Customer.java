package Persons;

/*
todo

menu -> edit order -> delete sama edit food jadiin satu aja
menu -> new order -> implementasi del, nnti ini dipake jg di edit order
 */
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

    public void addOrder(Order order) {
        this.listOrder.add(order);
    }

    public void removeOrder(int index) {
        this.listOrder.remove(index);
    }

    public List<Order> getListOrder() {
        return this.listOrder;
    }

    @Override
    public void info() {
        System.out.println("Name \t: " + getName());
        System.out.println("Gender \t: " + getGender());
        System.out.println("Phone \t: " + getPhone());
        System.out.println("Address : " + getAddress());
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
            System.out.println("Welcome User : " + getName());
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
            cls();
            System.out.println("------------------------CUSTOMER MENU------------------------\n");
            System.out.println();
            System.out.println("View Order   (v) | New Order      (n) | Pay Order   (p) |");
            System.out.println("Edit Order   (e) | Delete Order   (x) | Logout      (l) |");
            System.out.print("Input : ");
            input = myObj.nextLine();
            switch (input) {
                case "e":
                    cls();
                    do {
                        cls();
                        System.out.println("-------------- Edit Order --------------");
                        System.out.println();
                        int i = 0;
                        for (Order x : getListOrder()) {
                            if (x.getStatus().equals("active")) {
                                System.out.println("Order No." + (i + 1));
                                x.infoForCustomer();
                                i++;
                            }
                        }
                        if (i == 0) {
                            cls();
                            System.out.println("No active order found! Press any key to continue!");
                            input = myObj.nextLine();
                            break;
                        }
                        System.out.println();
                        System.out.print("Edit order number | [cancel] to cancel : ");
                        input = myObj.nextLine();
                        System.out.println();
                        try {
                            System.out.println("-------------- Your Order --------------");
                            System.out.println();
                            int index = Integer.parseInt(input) - 1;
                            this.listOrder.get(index).info();

                            do {
                                System.out.println();
                                System.out.println("-------------- Available Item --------------");
                                System.out.println();
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
                                System.out.print("FOOD [m] | DRINK [d] | SNACK [s] | DELETE [del] | VIEW ORDER [v] | done [done] : ");
                                input = myObj.nextLine();
                                System.out.println();
                                int number;

                                switch (input) {
                                    case "v":
                                        System.out.println("---------Your Order----------\n");
                                        this.listOrder.get(index).info();
                                        System.out.println();
                                        break;
                                    case "del":
                                        cls();
                                        System.out.println("---------Your Order----------\n");
                                        this.listOrder.get(index).info();
                                        System.out.println();
                                        System.out.print("DELETE : FOOD [dm]| DRINK [dd] | SNACK [ds]  : ");
                                        input = myObj.nextLine();
                                        System.out.println();

                                        switch (input) {
                                            case "dm":
                                                System.out.print("DELETE FOOD NUMBER? ");
                                                number = Integer.parseInt(myObj.nextLine()) - 1;
                                                try {
                                                    this.listOrder.get(index).deleteFood(number);
                                                } catch (IndexOutOfBoundsException exception) {
                                                    System.out.println("ITEM NOT FOUND");
                                                }
                                                break;
                                            case "dd":
                                                System.out.print("DELETE DRINK NUMBER? ");
                                                number = Integer.parseInt(myObj.nextLine()) - 1;
                                                try {
                                                    this.listOrder.get(index).deleteDrink(number);
                                                } catch (IndexOutOfBoundsException exception) {
                                                    System.out.println("ITEM NOT FOUND");
                                                }
                                                break;
                                            case "ds":
                                                System.out.print("DELETE SNACK NUMBER? ");
                                                number = Integer.parseInt(myObj.nextLine()) - 1;
                                                try {
                                                    this.listOrder.get(index).deleteSnack(number);
                                                } catch (IndexOutOfBoundsException exception) {
                                                    System.out.println("ITEM NOT FOUND");
                                                }
                                                break;
                                            default:
                                                System.out.println("Option not found!");
                                                break;
                                        }
                                        break;
                                    case "m":
                                        System.out.print("Insert Food Number : ");

                                        number = Integer.parseInt(myObj.nextLine()) - 1;
                                        System.out.println();

                                        try {
                                            this.listOrder.get(index).addFood(lm.get(number));
                                        } catch (IndexOutOfBoundsException exception) {
                                            System.out.println("ITEM NOT FOUND");
                                        }
                                        break;
                                    case "d":
                                        System.out.print("Insert Drink Number : ");
                                        number = Integer.parseInt(myObj.nextLine()) - 1;

                                        try {
                                            this.listOrder.get(index).addDrink(ld.get(number));
                                        } catch (IndexOutOfBoundsException exception) {
                                            System.out.println("ITEM NOT FOUND");
                                        }
                                        break;
                                    case "s":
                                        System.out.print("Insert Snack Number : ");

                                        number = Integer.parseInt(myObj.nextLine()) - 1;

                                        try {
                                            this.listOrder.get(index).addSnack(ls.get(number));
                                        } catch (IndexOutOfBoundsException exception) {
                                            System.out.println("ITEM NOT FOUND");
                                        }
                                        break;
                                    default:
                                        System.out.println("ITEM NOT FOUND");
                                        System.out.println();
                                        break;
                                }
                                cls();
//                                System.out.println("---------Your Order----------\n");
//                                this.listOrder.get(index).info();
//                                System.out.println();
                            } while (!input.equals("done"));
                            if (!this.listOrder.get(index).isOrderEmpty()) {
                                addOrder(this.listOrder.get(index));
                            } else {
                                System.out.println("Canceling order!");
                                removeOrder(index);
                            }
                            break;

                        } catch (Exception e) {
                            if (input.equals("cancel")) {
                                System.out.println("Canceling...");
                            } else {
                                System.out.println("Unknown input!");
                            }
                        }
                    } while (!input.equals("cancel"));
                    break;
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
                    }
                    System.out.println("Press any key to continue!");
                    input = myObj.nextLine();
                    break;
                case "n":

                    Order newOrder = new Order(this);
                    do {
                        cls();
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

                        System.out.print("FOOD [m] | DRINK [d] | SNACK [s] | DELETE [del] | VIEW ORDER [v] | done [done] : ");
                        input = myObj.nextLine();
                        System.out.println();
                        int number;

                        switch (input) {
                            case "v":
                                System.out.println("---------Your Order----------\n");
                                newOrder.info();
                                System.out.println();
                                break;
                            case "del":
                                if (!newOrder.isOrderEmpty()) {

                                    System.out.println("---------Your Order----------\n");
                                    newOrder.info();
                                    System.out.println();
                                    System.out.print("DELETE : FOOD [dm]| DRINK [dd] | SNACK [ds]  : ");
                                    input = myObj.nextLine();
                                    System.out.println();

                                    switch (input) {
                                        case "dm":
                                            System.out.print("DELETE FOOD NUMBER? ");
                                            number = Integer.parseInt(myObj.nextLine()) - 1;
                                            try {
                                                newOrder.deleteFood(number);
                                            } catch (IndexOutOfBoundsException exception) {
                                                System.out.println("ITEM NOT FOUND\n");
                                            }
                                            break;
                                        case "dd":
                                            System.out.print("DELETE DRINK NUMBER? ");
                                            number = Integer.parseInt(myObj.nextLine()) - 1;
                                            try {
                                                newOrder.deleteDrink(number);
                                            } catch (IndexOutOfBoundsException exception) {
                                                System.out.println("ITEM NOT FOUND\n");
                                            }
                                            break;
                                        case "ds":
                                            System.out.print("DELETE SNACK NUMBER? ");
                                            number = Integer.parseInt(myObj.nextLine()) - 1;
                                            try {
                                                newOrder.deleteSnack(number);
                                            } catch (IndexOutOfBoundsException exception) {
                                                System.out.println("ITEM NOT FOUND\n");
                                            }
                                            break;
                                        default:
                                            System.out.println("Option not found!\n");
                                            break;
                                    }
                                } else {
                                    System.out.println("You have no item in your order!\n");

                                }
                                break;
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
                                number = Integer.parseInt(myObj.nextLine()) - 1;

                                try {
                                    newOrder.addDrink(ld.get(number));
                                } catch (IndexOutOfBoundsException exception) {
                                    System.out.println("ITEM NOT FOUND");
                                }
                                break;
                            case "s":
                                System.out.print("Insert Snack Number : ");

                                number = Integer.parseInt(myObj.nextLine()) - 1;

                                try {
                                    newOrder.addSnack(ls.get(number));
                                } catch (IndexOutOfBoundsException exception) {
                                    System.out.println("ITEM NOT FOUND");
                                }
                                break;
                            case "done":
                                break;
                            default:
                                System.out.println("INVALID OPTION!");
                                System.out.println();
                                break;
                        }
//                        if (!newOrder.isOrderEmpty()) {
//                            System.out.println("---------Your Order----------\n");
//                            newOrder.info();
//                        }
//                        System.out.println();
                    } while (!input.equals("done"));
                    if (!newOrder.isOrderEmpty()) {
                        addOrder(newOrder);
                        System.out.println("Order added! Press any key to continue!");
                        input = myObj.nextLine();
                        cls();
                    } else {
                        System.out.println("Order cancelled! Press any key to continue!");
                        input = myObj.nextLine();
                        cls();
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
                                System.out.println();
                                i++;
                            }
                        }
                        if (i == 0) {
                            cls();
                            System.out.println("All order has been paid! Press any key to continue!");
                            input = myObj.nextLine();
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
                case "x":
                    cls();
                    System.out.println("----------------------- Delete Order -----------------------\n");
                    boolean error = false;
                    int deleteIndex = 0;
                    for (i = 0; i < getListOrder().size(); i++) {
                        System.out.println("----------- Order No." + (i + 1) + "-----------");
                        getListOrder().get(i).infoAll();
                        System.out.println();
                    }
                    if (i == 0) {
                        cls();
                        System.out.println("You have no order!\n");
                        System.out.println("Press any key to continue!");
                        input = myObj.nextLine();
                        break;
                    } else {
                        do {
                            try {
                                System.out.print("Delete order number? ([cancel] to cancel) : ");
                                input = myObj.nextLine();
                                if (input.equals("cancel")) {
                                    System.out.println("Cancelled! Press any key to continue! \n");
                                    input = myObj.nextLine();
                                    cls();
                                } else {
                                    deleteIndex = Integer.parseInt(input) - 1;
                                    switch (this.listOrder.get(deleteIndex).getStatus()) {
                                        case "active":
                                            System.out.println("Order Cancelled!");
                                            removeOrder(deleteIndex);
                                            break;
                                        case "paid":
                                            System.out.println("Order Cancelled! You have been refunded!");
                                            removeOrder(deleteIndex);
                                            break;
                                        case "en route":
                                            System.out.println("Cannot cancel en route order!");
                                            break;
                                        case "delivered":
                                            System.out.println("Deleting order from history!");
                                            removeOrder(deleteIndex);
                                            break;
                                    }
                                    System.out.print("Press any key to continue!");
                                    input = myObj.nextLine();
                                    cls();
                                }

                                error = false;
                            } catch (Exception e) {
                                System.out.println("Invalid input!");
                                error = true;
                            }
                        } while (error);

                    }

                    break;
            }
        } while (!input.equals("l"));
    }
}
