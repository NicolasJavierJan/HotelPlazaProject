import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static ArrayList<Person> staffMembers = new ArrayList<>();

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
        menu();
    }

    public static void menu(){

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
                    System.out.println("Press 1 to Create a new Staff member");
                    System.out.println("Press 2 to Edit a Staff member");
                    System.out.println("Another number (except 0) will take you back to the Main Menu");
                    int answer = userChoice();
                    if (answer == 1){
                        createStaff();
                    } else if (answer == 2){
                        modifyStaff();
                    } else {
                        break;
                    }
                    keepAsking = false;
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
        int choice = 0;
        while (choice == 0) {
            try {
                Scanner userChoice = new Scanner(System.in);
                choice = userChoice.nextInt();
                if (choice == 0){
                    System.out.println("Number can't be 0. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n! Please write a number !\n");
            }
        }
        return choice;
    }

    public static String userString(){
        Scanner userChoice = new Scanner(System.in);
        String answer = userChoice.nextLine();

        return answer;
    }

    public static void createStaff(){

        System.out.println("\nFill the following fields to 'create' a new staff member:");
        System.out.println("\n· Enter first name: ");
        String staffFirstName = Main.userString();

        System.out.println("\n· Enter last name: ");
        String staffLastName = Main.userString();

        System.out.println("\n· Enter phone number (+45):");
        int staffPhoneNumber = Main.userChoice();

        System.out.println("\n· Enter title: ");
        String staffTitle = Main.userString();


        System.out.println("\n· Enter salary per month: ");
        int staffSalary = Main.userChoice();

        Staff newStaffMember = new Staff (staffFirstName, staffLastName, staffPhoneNumber, staffTitle, staffSalary);

        staffMembers.add(newStaffMember);

        System.out.println("\nYou have registered a new staff member with the following information: " +
                "\n- First Name: " + newStaffMember.firstName +
                "\n- Last Name: " + newStaffMember.lastName +
                "\n- Title: " + newStaffMember.getTitle() +
                "\n- Phone Number: " + newStaffMember.phoneNumber +
                "\n- Salary: " + newStaffMember.getSalary() + " DKK");

        menu();
    }

    public static void modifyStaff(){

        System.out.println("\nList of staff members: ");

        for (Person staff : staffMembers){
            System.out.println("- " + staff.firstName + " ");
        }

        System.out.println("\nSearch for staff member first name: ");

        String staffMember = Main.userString();
        boolean isFound = false;

        for (Person staff : staffMembers) {
            isFound = true;
            if (staff instanceof Staff) {
                Staff found = (Staff) staff;

                System.out.println("\n· Enter staff member new first name: ");
                String newFirstName = Main.userString();
                found.firstName = newFirstName;

                System.out.println("\n· Enter staff member new last name: ");
                String newLastName = Main.userString();
                found.lastName = newLastName;

                System.out.println("\n· Enter staff member new phone number: (+45) ");
                int newPhoneNumber = Main.userChoice();
                found.phoneNumber = newPhoneNumber;

                System.out.println("\n· Enter staff member new title: ");
                String newTitle = Main.userString();
                found.setTitle(newTitle);

                System.out.println("\n· Enter staff member new salary per hour: (DKK)");
                int newSalary = Main.userChoice();
                found.setSalary(newSalary);

                System.out.println("\nYou have modified " + staffMember + "'s information to: " +
                        "\n- First Name: " + found.firstName +
                        "\n- Last Name: " + found.lastName +
                        "\n- Title: " + found.getTitle() +
                        "\n- Phone Number: " + staff.phoneNumber +
                        "\n- Salary: " + found.getSalary() + " DKK per hour");

                menu();
            }
        }
        if (!isFound){
            System.out.println("\n !  Staff member not found ! \n");

            boolean elseLoop = true;
            while(elseLoop) {

                System.out.println("\nDo you want to search for another staff member?");
                String answer = userString();
                if (answer.equalsIgnoreCase("yes")) {
                    modifyStaff();
                    elseLoop = false;
                } else if (answer.equalsIgnoreCase("no")) {
                    elseLoop = false;
                    // Redirect somewhere
                    menu();
                } else {
                    System.out.println("\n ! Please answer with yes or no !\n");
                }
            }
        }
    }
}
