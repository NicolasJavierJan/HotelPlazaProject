import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Classes:
    // TODO JASMIN
    // Room (change price, booking)
    // Bookings (start date, end date, room number, Guest)


    // TODO ANDREA
    // Guest : First/last name, telephone number, address




    // TODO NICO
    // Make a MENU:
    // Create ROOM
    // Create Guest
    // Create Staff
    // Create Booking
    // ENDPROGRAM

    public static void main(String[] args) {

        boolean keepAsking = true;

        while (keepAsking) {
            System.out.println("This is the Main Menu");
            System.out.println("Press 1 to create a Booking.");
            System.out.println("Administration options:");
            System.out.println("Press 2 for creating a new Guest");
            System.out.println("Press 3 for adding a new Room");
            System.out.println("Press 4 for adding new Staff");
            System.out.println("Press 9 for Exiting!");

            int choice = userChoice();

            switch (choice) {
                case 1:
                    System.out.println("CREATING BOOKINGGGGGG");
                    keepAsking = false;
                    break;
                case 2:
                    System.out.println("CREATING GUESSSSST");
                    keepAsking = false;
                    break;
                case 3:
                    System.out.println("ADDING ROOOOOMMMMMMM");
                    keepAsking = false;
                    break;
                case 4:
                    System.out.println("ADDING STAFFFFFFFFFFF");
                    keepAsking = false;
                    break;
                case 0:
                    System.out.println("Please try again.");
                    break;
                case 9:
                    System.out.println("Goodbye! ☺");
                    System.exit(0);
                default:
                    System.out.println("Choose a number from the list!");
            }
        }

    }

    public static int userChoice(){
        Scanner userChoice = new Scanner(System.in);
        int choice = 0;
        try {
            choice = userChoice.nextInt();
        } catch (InputMismatchException e){
            System.out.println("\n! Please write a number !\n");
        }
        return choice;
    }

    public static String userString(){
        Scanner userChoice = new Scanner(System.in);
        String answer = userChoice.nextLine();

        return answer;
    }

}
