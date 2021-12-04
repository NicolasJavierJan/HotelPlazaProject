import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static ArrayList<Staff> staffMembers = new ArrayList<>();
    public static ArrayList<Guest> guests = new ArrayList<>();
    public static ArrayList<Booking> bookings = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    public static void menu(){

        // De-Serializing Code

        try {
            FileInputStream staffIn = new FileInputStream("src/Staff.ser");
            FileInputStream guestsIn = new FileInputStream("src/Guests.ser");
            FileInputStream bookingsIn = new FileInputStream("src/Bookings.ser");
            FileInputStream roomsIn = new FileInputStream("src/Rooms.ser");

            ObjectInputStream oisStaff = new ObjectInputStream(staffIn);
            ObjectInputStream oisGuests = new ObjectInputStream(guestsIn);
            ObjectInputStream oisBookings = new ObjectInputStream(bookingsIn);
            ObjectInputStream oisRooms = new ObjectInputStream(roomsIn);

            Main.staffMembers = (ArrayList) oisStaff.readObject();
            Main.guests = (ArrayList) oisGuests.readObject();
            Main.bookings = (ArrayList) oisBookings.readObject();
            Main.rooms = (ArrayList) oisRooms.readObject();

            oisStaff.close();
            oisGuests.close();
            oisBookings.close();
            oisRooms.close();
            staffIn.close();
            guestsIn.close();
            bookingsIn.close();
            roomsIn.close();

        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException c){
            c.printStackTrace();
        }

        // For checking that everything works correctly.
        /*
        System.out.println(Main.staffMembers);
        System.out.println(Main.guests);
        System.out.println(Main.bookings);
        System.out.println(Main.rooms);
        */

            System.out.println("\n----- Main Menu -----\n" +
                    "· 1. Create booking" +
                    "\n· 2. Guest options" +
                    "\n· 3. Staff options" +
                    "\n· 4. Room options" +
                    "\n· 9. Save Changes and Exit!");

            boolean keepAsking = true;
            while (keepAsking) {
            int choice = userChoice();

            switch (choice) {

                case 1:
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
                    // Serializing code.
                    try {
                        FileOutputStream fosStaff = new FileOutputStream("src/Staff.ser");
                        ObjectOutputStream oosStaff = new ObjectOutputStream(fosStaff);

                        oosStaff.writeObject(Main.staffMembers);
                        oosStaff.close();
                        fosStaff.close();

                        FileOutputStream fosGuests = new FileOutputStream("src/Guests.ser");
                        ObjectOutputStream oosGuests = new ObjectOutputStream(fosGuests);

                        oosGuests.writeObject(Main.guests);
                        oosGuests.close();
                        fosGuests.close();

                        FileOutputStream fosBookings = new FileOutputStream("src/Bookings.ser");
                        ObjectOutputStream oosBookings = new ObjectOutputStream(fosBookings);

                        oosBookings.writeObject(Main.bookings);
                        oosBookings.close();
                        fosBookings.close();

                        FileOutputStream fosRooms = new FileOutputStream("src/Rooms.ser");
                        ObjectOutputStream oosRooms = new ObjectOutputStream(fosRooms);

                        oosRooms.writeObject(Main.rooms);
                        oosRooms.close();
                        fosRooms.close();

                    } catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                    System.out.println("Goodbye! ☺");
                    System.exit(0);

                default:
                    System.out.println("\n ! Choose a number from the list !");
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
                    System.out.println("\n ! Number can't be 0. Please try again. !");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n ! Please write a number !");
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
        System.out.println("· Yes" +
                "\n· No");
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
                System.out.println("\n ! Please write 'Yes' or 'No' !");
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
