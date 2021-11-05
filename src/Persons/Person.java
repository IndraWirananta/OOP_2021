package Persons;

import java.io.Serializable;

abstract public class Person implements Serializable {

    private String name;
    private String gender;
    private String phone;
    private String password;

    public Person(String name, String gender, String phone, String password) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getGender() {
        return this.gender;
    }

    public String getPhone() {
        return this.phone;
    }

    public boolean auth(String pass) {
        return this.password.equals(pass);
    }

    abstract public void info();

}
