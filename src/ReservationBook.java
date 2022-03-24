import java.util.ArrayList;
import java.util.Scanner;

interface IntInputValidator {
    boolean validate(int n);
}
/**
 Build on the Reservation hierarchy.
 Give the user the option to add new reservations.
 The user must specify the type of the reservation and
 description, and then, if required, the day or date.
 This is a program to be used by a restaurant that uses
 a list to keep all their reservations. They may add, find, or delete
 a reservation.
 */
public class ReservationBook {
    ArrayList<Reservation> reservations = new ArrayList<>();

    private static int getIntInput(String msg, Scanner in, IntInputValidator validator) {
        boolean correct = false;
        int result = 0;
        System.out.print(msg);
        while (!correct) {
            try {
                result = Integer.parseInt(in.next().trim());
                if (validator.validate(result))
                    correct = true;
                else
                    System.out.print(msg);
            } catch (Exception error) {
                System.out.println("Input invalid, numbers only!");
                System.out.print(msg);
            }
        }
        return result;
    }

    private static String getStringInput(Scanner in) {
        System.out.print("Enter Description: ");
        return in.nextLine();
    }

    private static int getYear() {
        return getIntInput("Enter Year (YYYY): ", new Scanner(System.in), (int num) -> {
            if (num >= 1000) return true;
            System.out.println("Year must be 4 digits long");
            return false;
        });
    }

    private static int getMonth() {
        return getIntInput("Enter Month (M): ", new Scanner(System.in), (int num) -> {
            if (num >= 1 && num <= 12) return true;
            System.out.println("Month must be 1 to 12");
            return false;
        });
    }

    private static int getDay() {
        return getIntInput("Enter Day (D): ", new Scanner(System.in), (int num) -> {
            if (num >= 1 && num < 31) return true;
            System.out.println("Day must be 1 - 31");
            return false;
        });
    }

    private static int getHour(){
        return getIntInput("Enter the hour: ", new Scanner(System.in), (int num) -> {
            if (num >=1 && num<= 12) return true;
            System.out.println("Time must be 1 to 12");
            return false;
        });
    }

    private static int getMinute(){
        return getIntInput("Enter the minutes: ", new Scanner(System.in), (int num) -> {
            if (num >=0 && num<= 59) return true;
            System.out.println("Time must be between 00 and 59");
            return false;
        });
    }

    /**
     * Adds a new Reservation object based on user input.
     *
     * @param in the Scanner to read from.
     */
    public void addReservations(Scanner in) {
        System.out.println("------- Add New Reservation -------");
        boolean done = false;
        int year, month, day, hour, minute;
        String description;
        while (!done) {
            System.out.print("Reservation for: (L)unch, (D)inner or (B)ack: ");
            String choice = in.next();
            switch (choice.toUpperCase()) {
                /* Adding a Reservation */
                //Lunch
                case "L" -> {
                    System.out.print("Add a Date and Time for the reservation:  (YYYY/M/D) & (hh:mm) \n");

                    // get input
                    description = getStringInput(new Scanner(System.in));
                    year = getYear();
                    month = getMonth();
                    day = getDay();
                    hour = getHour();
                    minute = getMinute();

                    // add reservations to list
                    this.reservations.add(new Lunch(year, month, day, hour, minute, description) {
                    });

                    System.out.println("Success! Lunch Reservation for \"" + description + "\" created for " + year + "/" + month + "/" + day + " @ " + hour + ":" + minute);

                    // exit
                    done = true;
                }
                //Dinner
                case "D" -> {
                    System.out.print("Add a Date and Time for the reservation:  (YYYY/M/D) & (hh:mm) \n");

                    // get input
                    description = getStringInput(new Scanner(System.in));
                    year = getYear();
                    month = getMonth();
                    day = getDay();
                    hour = getHour();
                    minute = getMinute();

                    // add reservations to list
                    this.reservations.add(new Dinner(year, month, day, hour, minute, description) {
                    });

                    System.out.println("Success! Dinner Reservation for \"" + description + "\" created for " + year + "/" + month + "/" + day + " @ " + hour + ":" + minute);

                    // exit
                    done = true;
                }
                /* Back */
                case "B" ->
                    // exit
                    done = true;

            }
        }
    }

    /**
     * Method to print all reservations on a certain date with time.
     *
     * @param in the Scanner to read from.
     */
    public void findReservations(Scanner in) {
        System.out.println("--- Find Reservation (YYYY/M/D) & (tttt) ---");

        // get input
        int year = getYear(), month = getMonth(), day = getDay(), hour = getHour(), min = getMinute();
        // filter reservations
        ArrayList<Reservation> filtered = new ArrayList<>();

        for (Reservation rspv: reservations){
            if(rspv.occursOn(year, month, day, hour, min)){
                filtered.add(rspv);
        }
        // print reservations
        if (filtered.size() == 0)
            System.out.println("No reservations found!");
        else {
            System.out.println(filtered.size() + " reservation" + (filtered.size() == 1 ? "" : "sa") + " found:");
            for (int i = 0; i < filtered.size(); i++)
                System.out.println((i + 1) + ": " + filtered.get(i).description + " for " + filtered.get(i).hour + ":"
                        + filtered.get(i).minute + (i + 1 == filtered.size() ? "" : ","));
        }
        }
    }

    public void deleteReservations(Scanner in) {
        System.out.println("--- Delete Reservation (YYYY/M/D) & (tttt) ---");
        int year = getYear(), month = getMonth(), day = getDay(), hour = getHour(), minute = getMinute();
        int count = 0;
        for(int i = 0; i < reservations.size(); ++i) {
            if (reservations.get(i).occursOn(year, month, day, hour, minute)) {
                count += 1;
                System.out.println("The " + reservations.get(i) + " is canceled.");
                reservations.remove(reservations.get(i));
            }
        }
        if(count == 0){
            System.out.println("No reservations found!");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ReservationBook rb = new ReservationBook();

        System.out.println("Welcome to the Reservation Book");

        boolean done = false;
        while (!done) {
            System.out.println("------------ Main Menu ------------");
            System.out.print("Reservation: (F)ind, (A)dd, (D)elete or (Q)uit: ");
            String choice = in.next();
            switch (choice) {
                case "F", "f" -> rb.findReservations(new Scanner(System.in));
                case "D", "d" -> rb.deleteReservations(new Scanner(System.in));
                case "A", "a" -> rb.addReservations(new Scanner(System.in));
            }
            done = choice.equals("Q") || choice.equals("q");
        }
        System.out.println("Good bye.  Have a nice day!");
        in.close();
    }
}