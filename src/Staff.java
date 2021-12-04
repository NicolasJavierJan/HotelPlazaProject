import java.io.Serializable;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Staff extends Person implements Serializable {

    private String title;
    private int salary;

    public Staff(String firstName, String lastName, int phoneNumber, String title, int salary) {
        super(firstName, lastName, phoneNumber);
        this.title = title;
        this.salary = salary;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public static void menu(){
        System.out.println("\n----- Staff Options -----" +
                    "\n· 1. Create new staff member" +
                    "\n· 2. Edit staff member" +
                    "\n· 9. Main menu");

        boolean keepAsking = true;
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
                    System.out.println("\n ! Choose a number from the list ! ");
            }
        }
    }

    public static void createPerson(){
        System.out.println("\nFill the following fields to create a new staff member:");

        System.out.println("\n· Enter first name: ");
        String staffFirstName = Main.userString();

        System.out.println("\n· Enter last name: ");
        String staffLastName = Main.userString();

        System.out.println("\n· Enter phone number (+45):");
        int staffPhoneNumber = Main.userChoice();

        System.out.println("\n· Enter title: ");
        String staffTitle = Main.userString();

        System.out.println("\n· Enter salary per hour: ");
        int staffSalary = Main.userChoice();

        Staff newStaffMember = new Staff (staffFirstName, staffLastName, staffPhoneNumber, staffTitle, staffSalary);

        System.out.println("\nYou have registered a new staff member with the following information: " +
                "\n- First Name: " + newStaffMember.firstName +
                "\n- Last Name: " + newStaffMember.lastName +
                "\n- Title: " + newStaffMember.getTitle() +
                "\n- Phone Number: +45 " + Main.phonePrint(newStaffMember.phoneNumber) +
                "\n- Salary: " + newStaffMember.getSalary() + ".00 DKK per hour\n");

        System.out.println("\nIs the information correct?" +
                "\n· Yes" +
                "\n· No");

        boolean keepAsking = true;
        while (keepAsking) {

            String infoValidation = Main.userString();
            switch (infoValidation.toLowerCase(Locale.ROOT)){

                case "yes":
                    keepAsking= false;
                    Main.staffMembers.add(newStaffMember);
                    System.out.println("\nStaff member registered correctly!");
                    menu();
                    break;

                case "no":
                    keepAsking = false;
                    boolean keepAsking1 = true;
                    while (keepAsking1) {

                        System.out.println("\nChoose the field you want to change:" +
                                "\n· 1. First name" +
                                "\n· 2. Last name" +
                                "\n· 3. Phone number" +
                                "\n· 4. Title" +
                                "\n· 5. Salary" +
                                "\n· 6. All fields" +
                                "\n· 7. Delete staff member" +
                                "\n· 8. Apply changes");

                        int choice = Main.userChoice();
                        switch (choice) {

                            case 1:
                                System.out.println("\n· Enter new first name: ");
                                String newFirstName = Main.userString();
                                newStaffMember.firstName = newFirstName;
                                System.out.println("\nField updated!");
                                break;

                            case 2:
                                System.out.println("\n· Enter new last name: ");
                                String newLastName = Main.userString();
                                newStaffMember.lastName = newLastName;
                                System.out.println("\nField updated!");
                                break;

                            case 3:
                                System.out.println("\n· Enter new phone number without white spaces: (+45) ");
                                int newPhoneNumber = Main.userChoice();
                                newStaffMember.phoneNumber = newPhoneNumber;
                                System.out.println("\nField updated!");
                                break;

                            case 4:
                                System.out.println("\n· Enter new title: ");
                                String newTitle = Main.userString();
                                newStaffMember.setTitle(newTitle);
                                System.out.println("\nField updated!");
                                break;

                            case 5:
                                System.out.println("\n· Enter new salary per hour: (DKK)");
                                int newSalary = Main.userChoice();
                                newStaffMember.setSalary(newSalary);
                                System.out.println("\nField updated!");
                                break;

                            case 6:
                                createPerson();
                                break;

                            case 7:
                                System.out.println("\nDo you want to delete the selected staff member: '" + newStaffMember.firstName + "' ?" +
                                        "\n· Yes" +
                                        "\n· No");

                                boolean keepAsking2 = true;
                                while(keepAsking2) {

                                    String answer = Main.userString();
                                    switch (answer.toLowerCase(Locale.ROOT)){

                                        case "yes":
                                            keepAsking1=false;
                                            keepAsking2 = false;
                                            Main.staffMembers.remove(newStaffMember);
                                            System.out.println("Staff member successfully deleted!");
                                            modifyPerson();
                                            break;

                                        case "no":
                                            keepAsking2 = false;
                                            break;

                                        default:
                                            System.out.println("\n ! Please answer with yes or no !");
                                    }
                                }
                                break;

                            case 8:
                                keepAsking = false;
                                Main.staffMembers.add(newStaffMember);
                                System.out.println("\nYou have registered a new staff member with the following information: " +
                                        "\n- First Name: " + newStaffMember.firstName +
                                        "\n- Last Name: " + newStaffMember.lastName +
                                        "\n- Title: " + newStaffMember.getTitle() +
                                        "\n- Phone Number: " + Main.phonePrint(newStaffMember.phoneNumber) +
                                        "\n- Salary: " + newStaffMember.getSalary() + ".00 DKK per hour\n");
                                menu();
                                break;

                            default:
                                System.out.println("\n ! Choose a number from the list ! ");
                        }
                    }
                default:
                    System.out.println("\n ! Please answer with 'Yes' or 'No' !");
            }
        }
    }




    public static void modifyPerson(){

        if (Main.staffMembers.isEmpty()){
            System.out.println("\n ! The list seems empty. Create staff members and come back later !");
            menu();
        }

        System.out.println("\nList of staff members: ");

        for (Staff staff : Main.staffMembers){
            System.out.println("- " + staff.firstName + " ");
        }

        System.out.println("\n· Search for staff member first name: ");

        String staffMember = Main.userString();
        boolean isFound = false;

        // Change type of the Arraylist to Staff.
        // Take out the typecasting.
        for (Staff staff : Main.staffMembers) {
            if (staffMember.equalsIgnoreCase(staff.firstName)){

            isFound = true;
                    Staff found = staff;

                    boolean keepAsking = true;
                    while (keepAsking) {

                        System.out.println("\nChoose the field you want to change:" +
                                "\n· 1. First name" +
                                "\n· 2. Last name" +
                                "\n· 3. Phone number" +
                                "\n· 4. Title" +
                                "\n· 5. Salary" +
                                "\n· 6. All fields" +
                                "\n· 7. Delete staff member" +
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
                                System.out.println("\n· Enter new phone number: (+45) ");
                                int newPhoneNumber = Main.userChoice();
                                found.phoneNumber = newPhoneNumber;
                                System.out.println("\nField updated!");
                                break;

                            case 4:
                                System.out.println("\n· Enter new title: ");
                                String newTitle = Main.userString();
                                found.setTitle(newTitle);
                                System.out.println("\nField updated!");
                                break;

                            case 5:
                                System.out.println("\n· Enter new salary per hour: (DKK)");
                                int newSalary = Main.userChoice();
                                found.setSalary(newSalary);
                                System.out.println("\nField updated!");
                                break;

                            case 6:
                                Main.staffMembers.remove(found);
                                createPerson();
                                break;

                            case 7:
                                System.out.println("\nDo you want to delete the selected staff member: '" + found.firstName + "' ?" +
                                        "\n· Yes" +
                                        "\n· No");

                                boolean keepAsking1 = true;
                                while(keepAsking1) {

                                    String answer = Main.userString();
                                    switch (answer.toLowerCase(Locale.ROOT)){

                                        case "yes":
                                            keepAsking=false;
                                            keepAsking1=false;
                                            Main.staffMembers.remove(found);
                                            System.out.println("\nStaff member successfully deleted!");
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
                                Main.staffMembers.remove(staff);
                                Main.staffMembers.add(found);
                                System.out.println("\nYou have updated a guest information with the following: " +
                                        "\n- First Name: " + found.firstName +
                                        "\n- Last Name: " + found.lastName +
                                        "\n- Title: " + found.getTitle() +
                                        "\n- Phone Number: " + Main.phonePrint(found.phoneNumber) +
                                        "\n- Salary: " + found.getSalary() + ".00 DKK per hour\n");
                                menu();
                                break;

                            default:
                                System.out.println("\n ! Choose a number from the list !");
                        }
                    }
                }
            }
        if (!isFound){
            System.out.println("\n !  Staff member not found !");
            System.out.println("\nDo you want to search for another staff member?" +
                    "\n· Yes" +
                    "\n· No");

            boolean keepAsking3 = true;
            while(keepAsking3) {

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
                        System.out.println("\n ! Please answer with 'Yes' or 'No'! ");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "First name= " + firstName +
                "\n- Last name= " + lastName +
                "\n- Phone number= " + phoneNumber +
                "\n- title=" + getTitle() +
                "\n- salary=" + getSalary() + "DKK";
    }

}
