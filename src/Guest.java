import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class Guest extends Person implements Serializable {

    private String address;

    public Guest (String firstName, String lastName, int phoneNumber, String address){
        super(firstName, lastName, phoneNumber);
        this.address = address;
    }

    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress() {
        return address;
    }

    public static void menu() {

        System.out.println("\n----- Guest Options -----" +
                    "\n· 1. Create guest" +
                    "\n· 2. Edit guest" +
                    "\n· 9. Main menu");

        boolean keepAsking= true;
        while (keepAsking) {

            int answerCase2 = Main.userChoice();
            switch (answerCase2) {

                case 1:
                    keepAsking = false;
                    createPerson();
                    break;

                case 2:
                    keepAsking = false;
                    modifyPerson();
                    break;

                case 9:
                    keepAsking = false;
                    Main.menu();
                    break;

                default:
                    System.out.println("\n ! Choose a number from the list !");
            }
        }
    }

    public static void createPerson(){

        System.out.println("\nFill the following fields to create a new guest:");

        System.out.println("\n· Enter first name: ");
        String guestFirstName = Main.userString();

        System.out.println("\n· Enter last name: ");
        String guestLastName = Main.userString();

        System.out.println("\n· Enter phone number without white spaces: (+45)");
        int guestPhoneNumber = Main.userChoice();

        System.out.println("\n· Enter address: ");
        String guestAddress = Main.userString();

        Guest newGuest = new Guest (guestFirstName, guestLastName, guestPhoneNumber, guestAddress);

        System.out.println("\nYou have registered a new guest with the following information: " +
                "\n- First Name: " + newGuest.firstName +
                "\n- Last Name: " + newGuest.lastName +
                "\n- Phone Number: +45 " + Main.phonePrint(newGuest.phoneNumber) +
                "\n- Address: " + newGuest.getAddress());

        System.out.println("\nIs the information correct?" +
                "\n· Yes" +
                "\n· No");

        boolean keepAsking=true;
        while (keepAsking) {

            String infoValidation = Main.userString();
            switch (infoValidation.toLowerCase(Locale.ROOT)) {

                case "yes":
                    keepAsking=false;
                    Main.guests.add(newGuest);
                    System.out.println("\nGuest registered correctly!");
                    menu();
                    break;

                case "no":
                    keepAsking=false;
                    boolean keepAsking1 = true;
                    while (keepAsking1) {

                    System.out.println("\nChoose the field you want to change:" +
                            "\n· 1. First name" +
                            "\n· 2. Last name" +
                            "\n· 3. Phone number" +
                            "\n· 4. Address" +
                            "\n· 5. All fields" +
                            "\n· 6. Delete guest" +
                            "\n· 7. Apply changes");

                        int choice = Main.userChoice();
                        switch (choice) {

                            case 1:
                                System.out.println("\n· Enter new first name: ");
                                String newFirstName = Main.userString();
                                newGuest.firstName = newFirstName;
                                System.out.println("\nField updated!");
                                break;

                            case 2:
                                System.out.println("\n· Enter new last name: ");
                                String newLastName = Main.userString();
                                newGuest.lastName = newLastName;
                                System.out.println("\nField updated!");
                                break;

                            case 3:
                                System.out.println("\n· Enter new phone number without white spaces: +45 ");
                                int newPhoneNumber = Main.userChoice();
                                newGuest.phoneNumber = newPhoneNumber;
                                System.out.println("\nField updated!");
                                break;

                            case 4:
                                System.out.println("\n· Enter new address: ");
                                String newAddress = Main.userString();
                                newGuest.setAddress(newAddress);
                                break;

                            case 5:
                                keepAsking1 = false;
                                createPerson();
                                break;

                            case 6:
                                System.out.println("\nDo you want to delete the selected guest: '" + newGuest.firstName + "' ?" +
                                        "\n· Yes" +
                                        "\n· No");

                                boolean keepAsking2 = true;
                                while (keepAsking2) {

                                    String answer = Main.userString();
                                    switch (answer.toLowerCase(Locale.ROOT)) {

                                        case "yes":
                                            keepAsking1=false;
                                            keepAsking2 = false;
                                            Main.guests.remove(newGuest);
                                            System.out.println("\nGuest successfully deleted!");
                                            modifyPerson();
                                            break;

                                        case "no":
                                            keepAsking2=false;
                                            keepAsking1=true;
                                            break;

                                        default:
                                            keepAsking1=false;
                                            System.out.println("\n ! Please answer with 'Yes' or 'No' !");
                                    }
                                }
                                break;

                            case 7:
                                keepAsking1 = false;
                                Main.guests.add(newGuest);
                                System.out.println("\nYou have registered a new guest with the following information: " +
                                        "\n- First Name: " + newGuest.firstName +
                                        "\n- Last Name: " + newGuest.lastName +
                                        "\n- Phone Number: +45 " + Main.phonePrint(newGuest.phoneNumber) +
                                        "\n- Address: " + newGuest.getAddress());
                                menu();
                                break;

                            default:
                                System.out.println("\n ! Choose a number from the list !");
                        }
                    }
                break;

                default:
                    System.out.println("\n ! Please answer with 'Yes' or 'No' !");
            }
        }
    }

    public static void modifyPerson(){

        if (Main.guests.isEmpty()){
            System.out.println("\n ! The list seems empty. Create new guests and come back later !");
            menu();
        }

        System.out.println("\nList of guests: ");

        for (Guest guest : Main.guests){
            System.out.println("- " + guest.firstName + " ");
        }

        System.out.println("\n· Search for guest first name: ");
        String guestName = Main.userString();

        boolean isFound = false;
        // Change type of the Arraylist to Guest.
        // Take out the typecasting.
        for (Guest guest: Main.guests) {
            if (guestName.equalsIgnoreCase(guest.firstName)) {

                isFound = true;

                    Guest found = guest;

                    boolean keepAsking = true;
                    while(keepAsking) {

                    System.out.println("\nChoose the field you want to change:" +
                            "\n· 1. First name" +
                            "\n· 2. Last name" +
                            "\n· 3. Phone number" +
                            "\n· 4. Address" +
                            "\n· 5. All fields" +
                            "\n· 6. Delete guest" +
                            "\n· 8. Apply changes");

                        int choice = Main.userChoice();
                        switch (choice) {

                            case 1:
                                System.out.println("\n· Enter new first name: ");
                                String newFirstName = Main.userString();
                                found.firstName = newFirstName;
                                System.out.println("\nField updated!");
                                break;

                            case 2:
                                System.out.println("\n· Enter new last name: ");
                                String newLastName = Main.userString();
                                found.lastName = newLastName;
                                System.out.println("\nField updated!");
                                break;

                            case 3:
                                System.out.println("\n· Enter new phone number without white spaces: (+45) ");
                                int newPhoneNumber = Main.userChoice();
                                found.phoneNumber = newPhoneNumber;
                                System.out.println("\nField updated!");
                                break;

                            case 4:
                                System.out.println("\n· Enter new address: ");
                                String newAddress = Main.userString();
                                found.setAddress(newAddress);
                                System.out.println("\nField updated!");
                                break;

                            case 5:
                                keepAsking = false;
                                Main.guests.remove(found);
                                createPerson();
                                break;

                            case 6:
                                System.out.println("\nDo you want to delete the selected guest: '" + found.firstName + "' ?" +
                                        "\n· Yes" +
                                        "\n· No");

                                boolean keepAsking1 = true;
                                while (keepAsking1) {

                                    String answer = Main.userString();
                                    switch (answer.toLowerCase(Locale.ROOT)) {

                                        case "yes":
                                            keepAsking=false;
                                            keepAsking1=false;
                                            Main.guests.remove(found);
                                            System.out.println("\nGuest successfully deleted!");
                                            modifyPerson();
                                            break;

                                        case "no":
                                            keepAsking1=false;
                                            break;

                                        default:
                                            System.out.println("\n ! Please answer with 'Yes' or 'No'! ");
                                    }
                                }
                                break;

                            case 8:
                                keepAsking = false;
                                Main.guests.remove(guest);
                                Main.guests.add(found);
                                System.out.println("\nYou have updated " + guest.firstName + "'s information with the following: " +
                                        "\n- First Name: " + found.firstName +
                                        "\n- Last Name: " + found.lastName +
                                        "\n- Phone Number: +45 " + Main.phonePrint(found.phoneNumber) +
                                        "\n- Address: " + found.getAddress());
                                menu();
                                break;

                            default:
                                System.out.println("\n ! Choose a number from the list !");
                        }
                    }
                }
            }
        if (!isFound){

            System.out.println("\n !  Guest not found !");
            System.out.println("\nDo you want to search for another guest?" +
                    "\n· Yes" +
                    "\n· No");

            boolean keepAsking3 = true;
            while (keepAsking3) {

                String answer = Main.userString();
                switch (answer.toLowerCase(Locale.ROOT)) {

                    case "yes":
                        keepAsking3=false;
                        modifyPerson();
                        break;

                    case "no":
                        keepAsking3=false;
                        Main.menu();
                        break;

                    default:
                        System.out.println("\n ! Please answer with 'Yes' or 'No' !");
                }
            }
        }
    }

    @Override
    public String toString(){
        return "First name= " + firstName +
                "\n- Last name= " + lastName +
                "\n- Phone number= " + phoneNumber +
                "\n- Address= " + getAddress();
    }
}
