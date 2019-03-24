package com.asus.technomania.prohelika.models;

public class user {
    public user() {
    }

    public user(String name, String emai, String phone) {
        this.name = name;
        this.emai = emai;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String name,emai,phone;
}
