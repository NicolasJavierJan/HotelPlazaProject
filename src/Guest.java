
import java.util.ArrayList;
import java.util.Scanner;

public class Guest extends Person{


    public static void main(String[] args) {
        Guest guestAdmin = new Guest("Guest Admin","-",2,"-");



        guestAdmin.createPerson();
        guestAdmin.modifyPerson();

    }

    private String address;
    private ArrayList<Guest> guestList = new ArrayList<>();

    public Guest (String firstName, String lastName, int phoneNumber, String address){
        super(firstName, lastName, phoneNumber);
        this.address = address;
    }

    public void createPerson(){

        System.out.println("\n· Enter guest's first name: ");
        String guestFirstName = Main.userString();

        System.out.println("\n· Enter guest's last name: ");
        String guestLastName = Main.userString();

        System.out.println("\n· Enter guest's phone number:");
        int guestPhoneNumber = Main.userChoice();


        System.out.println("\n· Enter guest's address: ");
        String guestAddress = Main.userString();


        Guest newGuest = new Guest (guestFirstName, guestLastName, guestPhoneNumber, guestAddress);


        getGuestList().add(newGuest);

        System.out.println("\nYou have registered a new staff member with the following information: " +
                "\n- Guest first Name: " + newGuest.firstName +
                "\n- Guest last Name: " + newGuest.lastName +
                "\n- Guest phone Number: " + newGuest.phoneNumber +
                "\n- Guest address: " + newGuest.getAddress());
    }

    public void modifyPerson(){

        System.out.println("\nList of staff members: ");

        for (Guest guest : guestList){
            System.out.println("- " + guest.firstName + " ");
        }

        System.out.println("\n· Search for guest first name: ");

        Scanner s1 = new Scanner(System.in);
        String staffMember = s1.nextLine();
        boolean isFound = false;

        for (Guest guest : getGuestList()){

            if (guest.firstName.equalsIgnoreCase(staffMember)){

                isFound = true;
                // The setName doesn't work:
                System.out.println("\n· Enter staff member new first name: ");
                String newFirstName = Main.userString();
                guest.firstName = newFirstName;

                System.out.println("\n· Enter staff member new last name: ");
                String newLastName = Main.userString();
                guest.lastName = newLastName;

                System.out.println("\n· Enter staff member new phone number: (+45) ");
                int newPhoneNumber = Main.userChoice();
                guest.phoneNumber = newPhoneNumber;

                System.out.println("\n· Enter guest new address: ");
                String newAddress = Main.userString();
                guest.setAddress(newAddress);

                System.out.println("\nYou have modified " + staffMember +  "'s information to: " +
                        "\n- First Name: " + guest.firstName +
                        "\n- Last Name: " + guest.lastName +
                        "\n- Phone Number: " + guest.phoneNumber+
                        "\n- Address: " + guest.getAddress());
            }
        } //LOOP CLOSED
        if (!isFound){
            System.out.println("\n !  Staff member not found ! \n");

            boolean elseLoop = true;
            while(elseLoop) {

                System.out.println("\nDo you want to search for another staff member?");
                Scanner s6 = new Scanner(System.in);
                String answer = s6.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    modifyPerson();
                    elseLoop = false;
                } else if (answer.equalsIgnoreCase("no")) {
                    elseLoop = false;
                    // redirect to somewhere
                } else {
                    System.out.println("\n ! Please answer with yes or no !\n");
                }
            }
        }
    }


    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress() {
        return address;
    }

    public ArrayList<Guest> getGuestList() {
        return guestList;
    }
}
