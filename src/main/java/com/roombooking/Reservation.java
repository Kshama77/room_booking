package com.roombooking;

import java.time.LocalDate;

public class Reservation {
    private final int id;
    private final String guestName;
    private final int roomNumber;
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    public Reservation(int id, String guestName, int roomNumber,
                       LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() { return id; }
    public String getGuestName() { return guestName; }
    public int getRoomNumber() { return roomNumber; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }

    // A guest may check in on the same day another guest checks out.
    public boolean overlaps(LocalDate start, LocalDate end) {
        return start.isBefore(checkOut) && end.isAfter(checkIn);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Guest: " + guestName + " | Room: " + roomNumber
                + " | " + checkIn + " to " + checkOut;
    }
}
