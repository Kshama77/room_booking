# RoomBooking - Java Console Application

A beginner-friendly room reservation application built with core Java. It lets users view rooms, create reservations, review bookings, search by guest name, and cancel reservations.

## Features

- View the sample rooms, capacity, and nightly price
- Book a room for a date range
- Prevent overlapping reservations for the same room
- Calculate the total booking price
- View all reservations
- Search reservations by guest name
- Cancel a reservation by ID
- Validate names, numbers, and dates

## Technologies

- Java 17 or later
- `ArrayList` and `List`
- Classes and objects
- Loops, conditionals, and methods
- `Scanner` for console input
- `LocalDate` for date handling

## Project Structure

```text
RoomBooking/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── roombooking/
│                   ├── Main.java
│                   ├── Room.java
│                   └── Reservation.java
└── README.md
```

## How to Run

### Option 1: IntelliJ IDEA or Eclipse

1. Download and extract the ZIP file.
2. Open the `RoomBooking` folder as a Java project.
3. Open `src/main/java/com/roombooking/Main.java`.
4. Run the `Main` class.

### Option 2: Terminal

From the `RoomBooking` project folder, compile:

```bash
javac -d out src/main/java/com/roombooking/*.java
```

Run:

```bash
java -cp out com.roombooking.Main
```

## Sample Rooms

| Room Number | Type     | Capacity | Price per Night |
|-------------|----------|----------|-----------------|
| 101         | Standard | 2        | $80.00          |
| 102         | Deluxe   | 3        | $120.00         |
| 201         | Suite    | 4        | $180.00         |
| 202         | Standard | 2        | $85.00          |

## Important Note

This is a learning project. Reservations are stored in memory using an `ArrayList`, so they are cleared when the application exits. It does not use a database or a graphical interface.

## Future Improvements

- Add rooms from the menu
- Save reservations to a file or database
- Add guest contact information
- Build a Java Swing or web interface
