package tugasbesar;

/*
DONE :
Order : setdate(date), getdate: date, deleteFood(index), deleteDrink(index), deleteSnack(index)
Driver : cancelDelivery()
 */
import Items.Makanan;
import Items.Minuman;
import Items.Snack;
import Orders.Order;
import Orders.Payment;
import Persons.Customer;
import Persons.Person;
import Persons.Driver;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class DriverTubes {

//USED TO CLEAR CONSOLE
    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public static boolean loginAdmin(String name, String password) {
        final String admin = "admin";
        final String pass = "admin";

        return (name.equals(admin) && password.equals(pass));
    }
//USED TO SAVE TO TMP FILE

    public static void save(List db, String saveFile) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(saveFile);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(db);
        }
    }
//USED TO LOAD FROM TMP FILE

    public static List load(String saveFile) throws FileNotFoundException, IOException, ClassNotFoundException {
        List db = new ArrayList();
        try {
            FileInputStream fis = new FileInputStream(saveFile);
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                db = (List) ois.readObject();
            }
        } catch (Exception ex) {
            //            System.out.println("Exception: " + ex);
        }
        return db;
    }

//CHECK IF CUSTOMER USERNAME IS TAKEN
    public static boolean checkAlreadyTakenCustomer(String check, List< Customer> customer) {
        for (Customer x : customer) {
            if (x.getName().equals(check)) {
                return true;
            }
        }
        return false;
    }

//CHECK IF DRIVER USERNAME IS TAKEN
    public static boolean checkAlreadyTakenDriver(String check, List< Driver> driver) {
        for (Driver x : driver) {
            if (x.getName().equals(check)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkGenderInput(String check) {
        if (check.equals("l") || check.equals("p")) {
            return true;
        } else {
            return false;
        }
    }

    public static void menuAdmin(List< Makanan> makananDb, List< Minuman> minumanDb, List< Snack> snackDb, List<Customer> customerDb, List<Driver> driverDb) {

        Scanner myObj = new Scanner(System.in);
        String input;
        do {
            cls();
            System.out.println("---------------------------------------[ MENU ADMIN ]--------------------------------------------");
            System.out.println("[gc] getInfoCustomer\t| [gd] getInfoDriver\t| [gm] getInfoMakanan\t| [exit] Exit");
            System.out.println("[af] add food \t\t| [ad] add drink\t| [as] add snack \t");
            System.out.println("[df] delete food \t| [dd] delete drink\t| [ds] delete snack \t");
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.print("Input : ");
            input = myObj.nextLine();

            switch (input) {
                case "gc":
// ------------------------------------------------------------------GET CUSTOMER INFO-----------------------------------------------
                    int indexC = 1;
                    System.out.println("-------------------Customer Info-----------------");
                    System.out.println();
                    for (Customer x : customerDb) {
                        System.out.println("Customer no " + indexC);
                        x.info();
                        indexC++;
                        System.out.println();
                    }
                    System.out.print("Press any key to continue!");
                    input = myObj.nextLine();
                    break;
                case "gd":
// ------------------------------------------------------------------GET DRIVER INFO-----------------------------------------------
                    int indexD = 1;
                    System.out.println("-------------------Driver Info-----------------");
                    System.out.println();
                    for (Driver x : driverDb) {
                        System.out.println("Driver no " + indexD);
                        x.info();
                        indexD++;
                        System.out.println();
                    }
                    System.out.print("Press any key to continue!");
                    input = myObj.nextLine();
                    break;
                case "gm":
// ------------------------------------------------------------------GET ITEM INFO-----------------------------------------------
                    System.out.println("-------------------Food Info-----------------");
                    System.out.println();
                    for (int x = 0; x < makananDb.size(); x++) {
                        System.out.println("Makanan no." + (x + 1) + "\t--------------");
                        makananDb.get(x).info();
                        System.out.println();
                    }
                    System.out.println();
                    for (int x = 0; x < minumanDb.size(); x++) {
                        System.out.println("Minuman no." + (x + 1) + "\t--------------");
                        minumanDb.get(x).info();
                        System.out.println();

                    }
                    System.out.println();
                    for (int x = 0; x < snackDb.size(); x++) {
                        System.out.println("Snack no." + (x + 1) + "\t--------------");
                        snackDb.get(x).info();
                        System.out.println();
                    }
                    System.out.println();
                    System.out.print("Press any key to continue!");
                    input = myObj.nextLine();
                    break;
                case "af":
// ------------------------------------------------------------------ ADD FOOD -----------------------------------------------
//                    String nama, int harga, String kategori, int porsi, int durasiMasak
                    String foodName;
                    int foodPrice;
                    String foodCategory;
                    int foodPortion;
                    int foodDuration;

                    System.out.println("-------------------Insert Food Info-----------------\n");
                    System.out.print("Insert food name \t: ");
                    foodName = myObj.nextLine();
                    System.out.print("Insert food name \t: ");
                    foodPrice = myObj.nextLine();
                    System.out.print("Insert food name \t: ");
                    foodCategory = myObj.nextLine();
                    System.out.print("Insert food name \t: ");
                    foodPortion = myObj.nextLine();
                    System.out.print("Insert food name \t: ");
                    foodDuration = myObj.nextLine();
                    Makanan newMakanan = new Makanan(foodName, foodPrice, foodCategory, foodPortion, foodDuration);
                    break;

            }

        } while (!input.equals("exit"));

    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

//      INIT ARRAYLIST --------------------------------------------------------------------------------------------
        List< Customer> customerDb = new ArrayList< Customer>();
        List< Driver> driverDb = new ArrayList< Driver>();
        List< Makanan> makananDb = new ArrayList< Makanan>();
        List< Minuman> minumanDb = new ArrayList< Minuman>();
        List< Snack> snackDb = new ArrayList< Snack>();
        Customer activeCustomer;
        Driver activeDriver;

        customerDb = load("dataCustomer.tmp");
        driverDb = load("dataDriver.tmp");

//      INIT ISI ARRAYLIST-----------------------------------------------------------------------------------------
//      String nama, int harga, String kategori, int porsi, int durasiMasak
        Makanan m1 = new Makanan("Mie Indomie", 10000, "Mie", 2, 5);
        Makanan m2 = new Makanan("Mie Supermie", 9000, "Mie", 2, 5);
        Makanan m3 = new Makanan("Mie Sedap", 5000, "Mie", 1, 5);
        makananDb.add(m1);
        makananDb.add(m2);
        makananDb.add(m3);
//      String nama, int harga, String kategori, int volume, boolean isDingin
        Minuman mm1 = new Minuman("Boba", 10000, "Tea", 300, true);
        Minuman mm2 = new Minuman("Teh hangat", 5000, "Tea", 450, false);
        Minuman mm3 = new Minuman("Jus jeruk", 7000, "Mie", 450, true);
        minumanDb.add(mm1);
        minumanDb.add(mm2);
        minumanDb.add(mm3);
//      String nama, int harga, String kategori,int berat
        Snack s1 = new Snack("Krupuk bawang", 3000, "Krupuk", 100);
        Snack s2 = new Snack("Krupuk udang", 3000, "Krupuk", 150);
        Snack s3 = new Snack("Kacang rebus", 2000, "Kacang", 150);
        snackDb.add(s1);
        snackDb.add(s2);
        snackDb.add(s3);

        Scanner myObj = new Scanner(System.in);
        System.out.println("------------------------------------------[ MENU ]-----------------------------------------------");
        System.out.println("[D ] New Driver \t| [C ] New Customer\t| [lc] Login Customer\t| [ld] Login Driver");
        System.out.println("[A ] Login Admin \t| [exit] Exit");
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.print("Input : ");
        String input = myObj.nextLine();
        cls();
        while (!input.equals("exit")) {
            switch (input) {
                case "D":
// ------------------------------------------------------------------DRIVER CREATE---------------------------------------------------
                    System.out.println("------------------------Create Driver Account------------------------\n");
                    System.out.print("Insert Name : ");
                    String nameDriver = myObj.nextLine();

                    while (checkAlreadyTakenDriver(nameDriver, driverDb)) {
                        System.out.print("Name already taken! Insert Name : ");
                        nameDriver = myObj.nextLine();
                    }

                    System.out.print("Insert Gender (p/l) : ");
                    String genderDriver = myObj.nextLine();
                    while (!checkGenderInput(genderDriver)) {
                        System.out.print("Insert Gender (p/l) : ");
                        genderDriver = myObj.nextLine();
                        checkGenderInput(genderDriver);
                    }
                    System.out.print("Insert Phone number : ");
                    String phoneDriver = myObj.nextLine();
                    System.out.print("Insert Affiliation : ");
                    String affiDriver = myObj.nextLine();
                    System.out.print("Insert Password : ");
                    String passDriver = myObj.nextLine();
                    activeDriver = new Driver(nameDriver, genderDriver, phoneDriver, passDriver, affiDriver);
                    driverDb.add(activeDriver);
                    save(driverDb, "dataDriver.tmp");
                    try {
                        activeDriver.menu(customerDb);
                    } catch (NullPointerException e) {
                        System.out.println("There is no order or customer currently. Logging out..");
                    }
                    save(driverDb, "dataDriver.tmp");

                    break;
                case "C":
// ------------------------------------------------------------------CUSTOMER CREATE---------------------------------------------------
                    System.out.println("------------------------Create Customer Account------------------------\n");
                    System.out.print("Insert Name : ");
                    String nameCustomer = myObj.nextLine();

                    while (checkAlreadyTakenCustomer(nameCustomer, customerDb)) {
                        System.out.print("Name already taken! Insert Name : ");
                        nameCustomer = myObj.nextLine();
                    }
                    System.out.print("Insert Gender (p/l) : ");
                    String genderCustomer = myObj.nextLine();
                    while (!checkGenderInput(genderCustomer)) {
                        System.out.print("Insert Gender (p/l) : ");
                        genderCustomer = myObj.nextLine();
                        checkGenderInput(genderCustomer);
                    }

                    System.out.print("Insert Phone number : ");
                    String phoneCustomer = myObj.nextLine();
                    System.out.print("Insert Address : ");
                    String addrCustomer = myObj.nextLine();
                    System.out.print("Insert Password : ");
                    String passCustomer = myObj.nextLine();
                    activeCustomer = new Customer(nameCustomer, genderCustomer, phoneCustomer, passCustomer, addrCustomer);
                    customerDb.add(activeCustomer);
                    save(customerDb, "dataCustomer.tmp");
                    activeCustomer.menu(makananDb, minumanDb, snackDb);
                    save(customerDb, "dataCustomer.tmp");

                    break;
                case "lc":
// ------------------------------------------------------------------CUSTOMER LOGIN-------------------------------------------------
                    cls();
                    boolean cLogin = false;
                    System.out.println("------------------------CUSTOMER LOGIN------------------------");
                    do {
                        System.out.print("Insert Username : ");
                        nameCustomer = myObj.nextLine();
                        System.out.print("Insert Password : ");
                        passCustomer = myObj.nextLine();
                        for (Customer x : customerDb) {
                            if (nameCustomer.equals(x.getName()) && x.auth(passCustomer)) {
                                activeCustomer = x;
                                cLogin = true;
                                activeCustomer.menu(makananDb, minumanDb, snackDb);
                                save(customerDb, "dataCustomer.tmp");
                            }
                        }
                        if (!cLogin) {
                            System.out.print("Wrong password or username! exit? [y/n] : ");
                            passCustomer = myObj.nextLine();
                            if (passCustomer.equals("y")) {
                                cLogin = true;
                            }
                        }
                    } while (!cLogin);

                    break;
                case "A":
// ------------------------------------------------------------------ADMIN LOGIN-------------------------------------------------
                    cls();
                    boolean aLogin = false;
                    System.out.println("------------------------ADMIN LOGIN------------------------");
                    do {
                        System.out.print("Insert Username : ");
                        String nameAdmin = myObj.nextLine();
                        System.out.print("Insert Password : ");
                        String passAdmin = myObj.nextLine();
                        aLogin = loginAdmin(nameAdmin, passAdmin);
                        if (aLogin) {
                            menuAdmin(makananDb, minumanDb, snackDb, customerDb, driverDb);
                        } else {
                            System.out.print("Wrong password or username! exit? [y/n] : ");
                            passCustomer = myObj.nextLine();
                            if (passCustomer.equals("y")) {
                                cLogin = true;
                            }
                        }
                    } while (!aLogin);

                    break;
                case "ld":
// ------------------------------------------------------------------DRIVER LOGIN----------------------------------------------------
                    cls();
                    boolean dLogin = false;
                    System.out.println("------------------------DRIVER LOGIN------------------------");
                    do {
                        System.out.print("Insert Username : ");
                        nameDriver = myObj.nextLine();
                        System.out.print("Insert Password : ");
                        passDriver = myObj.nextLine();
                        for (Driver x : driverDb) {
                            if (nameDriver.equals(x.getName()) && x.auth(passDriver)) {
                                activeDriver = x;
                                dLogin = true;
                                try {
                                    activeDriver.menu(customerDb);
                                } catch (NullPointerException e) {
                                    System.out.println("There is no order or customer currently. Logging out..");
                                }
                                save(driverDb, "dataDriver.tmp");
                            }
                        }
                        if (!dLogin) {
                            System.out.print("Wrong password or username! exit? [y/n] : ");
                            passDriver = myObj.nextLine();
                            if (passDriver.equals("y")) {
                                dLogin = true;
                            }
                        }
                    } while (!dLogin);
                    break;

            }
            cls();
            System.out.println("------------------------------------------[ MENU ]-----------------------------------------------");
            System.out.println("[D ] New Driver \t| [C ] New Customer\t| [lc] Login Customer\t| [ld] Login Driver");
            System.out.println("[A ] Login Admin \t| [exit] Exit");
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.print("Input : ");
            input = myObj.nextLine();
            cls();
        }

    }

}
