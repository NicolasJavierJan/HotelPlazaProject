import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Change to type Staff
    public static ArrayList<Person> staffMembers = new ArrayList<>();
    public static ArrayList<Person> guests = new ArrayList<>();

    // Classes:
    // TODO JASMIN
    // Room (change price, booking)
    // Bookings (start date, end date, room number, Guest)

    // TODO ANDREA
    // Guest : First/last name, telephone number, address

    // TODO NICO
    // Create ROOM
    // Create Guest
    // Create Booking

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
                    System.out.println("ADDING ROOOOOMMMMMMM");
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
