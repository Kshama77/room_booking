package com.roombooking;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Reservation> reservations = new ArrayList<>();
    private static int nextReservationId = 1;

    public static void main(String[] args) {
        addSampleRooms();

        System.out.println("================================");
        System.out.println("       ROOM BOOKING APP");
        System.out.println("================================");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> showRooms();
                case 2 -> bookRoom();
                case 3 -> showReservations();
                case 4 -> cancelReservation();
                case 5 -> searchReservations();
                case 0 -> running = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Thank you for using Room Booking App!");
        scanner.close();
    }

    private static void addSampleRooms() {
        rooms.add(new Room(101, "Standard", 2, 80.00));
        rooms.add(new Room(102, "Deluxe", 3, 120.00));
        rooms.add(new Room(201, "Suite", 4, 180.00));
        rooms.add(new Room(202, "Standard", 2, 85.00));
    }

    private static void showMenu() {
        System.out.println("\n------------- MENU -------------");
        System.out.println("1. View available rooms");
        System.out.println("2. Book a room");
        System.out.println("3. View all reservations");
        System.out.println("4. Cancel a reservation");
        System.out.println("5. Search reservations by guest name");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
    }

    private static void showRooms() {
        System.out.println("\nROOMS");
        System.out.printf("%-10s %-12s %-12s %-12s%n", "Room No.", "Type", "Capacity", "Price/Night");
        for (Room room : rooms) {
            System.out.printf("%-10d %-12s %-12d $%-11.2f%n",
                    room.getRoomNumber(), room.getType(), room.getCapacity(), room.getPricePerNight());
        }
    }

    private static void bookRoom() {
        showRooms();
        int roomNumber = readInt("\nEnter room number: ");
        Room room = findRoom(roomNumber);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.print("Guest name: ");
        String guestName = scanner.nextLine().trim();
        if (guestName.isEmpty()) {
            System.out.println("Guest name cannot be empty.");
            return;
        }

        LocalDate checkIn = readDate("Check-in date (YYYY-MM-DD): ");
        LocalDate checkOut = readDate("Check-out date (YYYY-MM-DD): ");

        if (checkIn == null || checkOut == null) {
            System.out.println("Invalid date. Please use YYYY-MM-DD.");
            return;
        }
        if (!checkOut.isAfter(checkIn)) {
            System.out.println("Check-out date must be after check-in date.");
            return;
        }
        if (checkIn.isBefore(LocalDate.now())) {
            System.out.println("Check-in date cannot be in the past.");
            return;
        }
        if (hasConflict(roomNumber, checkIn, checkOut)) {
            System.out.println("Sorry, this room is already booked for those dates.");
            return;
        }

        Reservation reservation = new Reservation(
                nextReservationId++, guestName, roomNumber, checkIn, checkOut);
        reservations.add(reservation);

        long nights = checkOut.toEpochDay() - checkIn.toEpochDay();
        double total = nights * room.getPricePerNight();
        System.out.println("\nBooking successful!");
        System.out.println("Reservation ID: " + reservation.getId());
        System.out.printf("Total price for %d night(s): $%.2f%n", nights, total);
    }

    private static boolean hasConflict(int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoomNumber() == roomNumber
                    && reservation.overlaps(checkIn, checkOut)) {
                return true;
            }
        }
        return false;
    }

    private static void showReservations() {
        if (reservations.isEmpty()) {
            System.out.println("\nNo reservations found.");
            return;
        }

        System.out.println("\nRESERVATIONS");
        System.out.printf("%-6s %-20s %-10s %-14s %-14s%n",
                "ID", "Guest", "Room", "Check-in", "Check-out");
        for (Reservation reservation : reservations) {
            System.out.printf("%-6d %-20s %-10d %-14s %-14s%n",
                    reservation.getId(), reservation.getGuestName(),
                    reservation.getRoomNumber(), reservation.getCheckIn(),
                    reservation.getCheckOut());
        }
    }

    private static void cancelReservation() {
        int id = readInt("Enter reservation ID to cancel: ");
        Reservation found = findReservation(id);

        if (found == null) {
            System.out.println("Reservation not found.");
            return;
        }

        reservations.remove(found);
        System.out.println("Reservation " + id + " has been cancelled.");
    }

    private static void searchReservations() {
        System.out.print("Enter guest name to search: ");
        String name = scanner.nextLine().trim().toLowerCase();
        boolean found = false;

        for (Reservation reservation : reservations) {
            if (reservation.getGuestName().toLowerCase().contains(name)) {
                System.out.println(reservation);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching reservations found.");
        }
    }

    private static Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) return room;
        }
        return null;
    }

    private static Reservation findReservation(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) return reservation;
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        System.out.print(prompt);
        try {
            return LocalDate.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
