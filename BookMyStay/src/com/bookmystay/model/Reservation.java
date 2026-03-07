package com.bookmystay.model;

 //Stores guest name, room type, and quantity.

public class Reservation {

    private String guestName;
    private String roomType;
    private int quantity;

    public Reservation(String guestName, String roomType, int quantity) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.quantity = quantity;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return guestName + "->" + roomType+" , " + quantity;
    }
}