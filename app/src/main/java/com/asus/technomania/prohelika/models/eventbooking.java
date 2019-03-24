package com.asus.technomania.prohelika.models;

public class eventbooking {
    public eventbooking() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getNumberOfpeople() {
        return numberOfpeople;
    }

    public void setNumberOfpeople(String numberOfpeople) {
        this.numberOfpeople = numberOfpeople;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    String name,phone,eventid,numberOfpeople,transactionID;

    public eventbooking(String name, String phone, String eventid, String numberOfpeople, String transactionID) {
        this.name = name;
        this.phone = phone;
        this.eventid = eventid;
        this.numberOfpeople = numberOfpeople;
        this.transactionID = transactionID;
    }
}
