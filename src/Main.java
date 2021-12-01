import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static ArrayList<Staff> staffMembers = new ArrayList<>();
    public static ArrayList<Guest> guests = new ArrayList<>();
    public static ArrayList<Booking> bookings = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();

    // TODO NICO
    // Create Booking
    // Edit Booking

    public static void main(String[] args) {
        menu();

    }

    public static void menu(){

            System.out.println("\n----- Main Menu -----\n" +
                    "· 1. Create booking" +
                    "\n· 2. Guest options" +
                    "\n· 3. Staff options" +
                    "\n· 4. Room options" +
                    "\n· 9. Exit");

            boolean keepAsking = true;
            while (keepAsking) {
            int choice = userChoice();

            switch (choice) {

                case 1:

                    System.out.println("----- Create Booking -----");
                    Booking.menu();
                    keepAsking = false;
                    break;

                case 2:
                    Guest.menu();
                    keepAsking = false;
                        break;
                case 3:
                    Staff.menu();
                    keepAsking = false;
                    break;

                case 4:
                    Room.menu();
                    keepAsking = false;
                    break;

                case 9:
                    System.out.println("Goodbye! ☺");
                    System.exit(0);

                default:
                    System.out.println("\n! Choose a number from the list !");
            }
        }
    }

    public static int userChoice(){
        int choice = 0;
        while (choice == 0) {
            try {
                Scanner userChoice = new Scanner(System.in);
                choice = userChoice.nextInt();
                if (choice == 0){
                    System.out.println("\n! Number can't be 0. Please try again. !");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n! Please write a number !");
            }
        }
        return choice;
    }

    public static String userString(){
        Scanner userChoice = new Scanner(System.in);
        String answer = userChoice.nextLine();

        return answer;
    }

    public static boolean userBoolean(){
        boolean keepAsking = true;
        System.out.println("Please enter 'yes' or 'no'");
        while (keepAsking) {
            Scanner userBoolean = new Scanner(System.in);
            String answer = userBoolean.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                keepAsking = false;
                return true;
            } else if (answer.equalsIgnoreCase("no")) {
                keepAsking = false;
                return false;
            } else {
                System.out.println("Please write 'yes' or 'no'");
            }
        }
        return false;
    }

    // Prints a sequence of number with a white space every two numbers
    public static String phonePrint(int phoneNumber){

        String phoneNumberString = String.valueOf(phoneNumber);
        String phonePrint = "";
        int chCount= 0;

        for (int i = 0; i<phoneNumberString.length(); i++){
            phonePrint = phonePrint + phoneNumberString.charAt(i);
            chCount++;

            if (chCount == 2){
                phonePrint = phonePrint + " ";
                chCount = 0;
            }
        }
        return phonePrint;
    }

}
