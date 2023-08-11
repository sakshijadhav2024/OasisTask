import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String loginId;
    private String password;

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private String pnrNumber;
    private String bankDetails;
    private int trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String source;
    private String destination;

    public Reservation(String pnrNumber, String bankDetails, int trainNumber, String trainName, String classType,
                       String dateOfJourney, String source, String destination) {
        this.pnrNumber = pnrNumber;
        this.bankDetails = bankDetails;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.source = source;
        this.destination = destination;
    }

    public String getPnrNumber() {
        return pnrNumber;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    // Add other getters and setters as needed
}

public class ReservationSystem {
    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int pnrCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a default user for demonstration purposes
        users.add(new User("user", "password123"));

        System.out.println("Welcome to the Online Reservation System!");

        // Login Module
        User loggedInUser = login(scanner);

        // Reservation Module
        if (loggedInUser != null) {
            makeReservation(scanner);
        }

        // Cancellation Module
        if (loggedInUser != null) {
            cancelReservation(scanner);
        }

        scanner.close();
    }

    private static User login(Scanner scanner) {
        System.out.print("Enter your login ID: ");
        String loginId = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getLoginId().equals(loginId) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }

        System.out.println("Invalid login credentials.");
        return null;
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("Welcome to the Reservation Form!");
        System.out.print("Enter bank details: ");
        String bankDetails = scanner.nextLine();

        System.out.print("Enter train number: ");
        int trainNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Enter source: ");
        String source = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        String pnrNumber = generatePNR();
        Reservation reservation = new Reservation(pnrNumber, bankDetails, trainNumber, "TrainName",
                classType, dateOfJourney, source, destination);
        reservations.add(reservation);

        System.out.println("Reservation Successful! Your PNR number is: " + pnrNumber);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR number to cancel the reservation: ");
        String pnrNumber = scanner.nextLine();

        for (Reservation reservation : reservations) {
            if (pnrNumber.equals(reservation.getPnrNumber())) {
                System.out.println("PNR Number: " + reservation.getPnrNumber());
                System.out.println("Bank Details: " + reservation.getBankDetails());
                // Display other reservation information

                System.out.print("Press OK to confirm cancellation: ");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("OK")) {
                    reservations.remove(reservation);
                    System.out.println("Reservation cancelled successfully!");
                    return;
                } else {
                    System.out.println("Cancellation not confirmed.");
                    return;
                }
            }
        }

        System.out.println("Invalid PNR number or reservation not found.");
    }

    private static String generatePNR() {
        String pnrPrefix = "PNR";
        return pnrPrefix + pnrCounter++;
    }
}
