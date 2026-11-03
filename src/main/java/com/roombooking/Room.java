package com.roombooking;

public class Room {
    private final int roomNumber;
    private final String type;
    private final int capacity;
    private final double pricePerNight;

    public Room(int roomNumber, String type, int capacity, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public int getCapacity() { return capacity; }
    public double getPricePerNight() { return pricePerNight; }
}
