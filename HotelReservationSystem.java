import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    static class Room {
        int roomNumber;
        String category;
        boolean isAvailable;
        double pricePerNight;

        public Room(int roomNumber, String category, boolean isAvailable, double pricePerNight) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.isAvailable = isAvailable;
            this.pricePerNight = pricePerNight;
        }

        @Override
        public String toString() {
            return "Room Number: " + roomNumber +
                   ", Category: " + category +
                   ", Price: $" + pricePerNight +
                   ", Available: " + (isAvailable ? "Yes" : "No");
        }
    }

    static class Booking {
        int roomNumber;
        String customerName;
        int nights;
        double totalAmount;

        public Booking(int roomNumber, String customerName, int nights, double totalAmount) {
            this.roomNumber = roomNumber;
            this.customerName = customerName;
            this.nights = nights;
            this.totalAmount = totalAmount;
        }

        @Override
        public String toString() {
            return "Booking Details:\n" +
                   "Customer Name: " + customerName +
                   "\nRoom Number: " + roomNumber +
                   "\nNights: " + nights +
                   "\nTotal Amount: $" + totalAmount;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        // Sample room data
        rooms.add(new Room(101, "Single", true, 100.0));
        rooms.add(new Room(102, "Double", true, 150.0));
        rooms.add(new Room(103, "Suite", true, 250.0));
        rooms.add(new Room(104, "Double", false, 150.0));

        boolean exit = false;

        System.out.println("Welcome to the Hotel Reservation System!");

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // View Available Rooms
                    System.out.println("\nAvailable Rooms:");
                    for (Room room : rooms) {
                        if (room.isAvailable) {
                            System.out.println(room);
                        }
                    }
                    break;

                case 2: // Make a Reservation
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Room selectedRoom = null;
                    for (Room room : rooms) {
                        if (room.roomNumber == roomNumber && room.isAvailable) {
                            selectedRoom = room;
                            break;
                        }
                    }

                    if (selectedRoom != null) {
                        System.out.print("Enter your name: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Enter number of nights: ");
                        int nights = scanner.nextInt();
                        double totalAmount = nights * selectedRoom.pricePerNight;

                        // Mark room as booked
                        selectedRoom.isAvailable = false;

                        // Add booking
                        bookings.add(new Booking(roomNumber, customerName, nights, totalAmount));

                        System.out.printf("Room booked successfully! Total amount: $%.2f%n", totalAmount);
                    } else {
                        System.out.println("Room not available or does not exist.");
                    }
                    break;

                case 3: // View Booking Details
                    if (bookings.isEmpty()) {
                        System.out.println("No bookings found.");
                    } else {
                        System.out.println("\nBookings:");
                        for (Booking booking : bookings) {
                            System.out.println(booking + "\n");
                        }
                    }
                    break;

                case 4: // Exit
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
