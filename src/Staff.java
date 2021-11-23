import java.util.ArrayList;
import java.util.Scanner;


public class Staff extends Person {

    public static void main(String[] args) {
        Staff staffAdmin = new Staff("admin","-",1,"-",1000);
        staffAdmin.getStaffMembers().add(staffAdmin);

        staffAdmin.createPerson();
        staffAdmin.modifyPerson();

    }

    private String title;
    private int salary;

    // Change position later
    private ArrayList<Staff> staffMembers = new ArrayList<>();

    public Staff(String firstName, String lastName, int phoneNumber, String title, int salary) {

        super(firstName, lastName, phoneNumber);
        this.title = title;
        this.salary = salary;
    }

    public void createPerson(){

        System.out.println("\nFill the following fields to 'create' a new staff member: ( Or something like this)");

        System.out.println("\n· Enter first name: ");
        String staffFirstName = Main.userString();

        System.out.println("\n· Enter last name: ");
        String staffLastName = Main.userString();

        System.out.println("\n· Enter phone number: (+45)");
        int staffPhoneNumber = Main.userChoice();

        System.out.println("\n· Enter title: ");
        String staffTitle = Main.userString();


        System.out.println("\n· Enter salary per hour: ");
        int staffSalary = Main.userChoice();

        Staff newStaffMember = new Staff (staffFirstName, staffLastName, staffPhoneNumber, staffTitle, staffSalary);

        getStaffMembers().add(newStaffMember);

        System.out.println("\nYou have registered a new staff member with the following information: " +
                "\n- First Name: " + newStaffMember.firstName +
                "\n- Last Name: " + newStaffMember.lastName +
                "\n- Title: " + newStaffMember.getTitle() +
                "\n- Phone Number: " + newStaffMember.phoneNumber +
                "\n- Salary: " + newStaffMember.getSalary() + " DKK");
    }

    public void modifyPerson(){

        System.out.println("\nList of staff members: ");

        for (Staff staff : staffMembers){
            System.out.println("- " + staff.firstName + " ");
        }

        System.out.println("\nSearch for staff member first name: ");

        Scanner s1 = new Scanner(System.in);
        String staffMember = s1.nextLine();
        boolean isFound = false;

        for (Staff staff : getStaffMembers()){

            if (staff.firstName.equalsIgnoreCase(staffMember)){

                isFound = true;
                // The setName doesn't work:
                System.out.println("\n· Enter staff member new first name: ");
                String newFirstName = Main.userString();
                staff.firstName = newFirstName;

                System.out.println("\n· Enter staff member new last name: ");
                String newLastName = Main.userString();
                staff.lastName = newLastName;

                System.out.println("\n· Enter staff member new phone number: (+45) ");
                int newPhoneNumber = Main.userChoice();
                staff.phoneNumber = newPhoneNumber;

                System.out.println("\n· Enter staff member new title: ");
                String newTitle = Main.userString();
                staff.setTitle(newTitle);

                System.out.println("\n· Enter staff member new salary per hour: (DKK)");
                int newSalary = Main.userChoice();
                staff.setSalary(newSalary);

                System.out.println("\nYou have modified " + staffMember +  "'s information to: " +
                        "\n- First Name: " + staff.firstName +
                        "\n- Last Name: " + staff.lastName +
                        "\n- Title: " + staff.getTitle() +
                        "\n- Phone Number: " + staff.phoneNumber+
                        "\n- Salary: " + staff.getSalary() + " DKK per hour");
            }
                //LOOP CLOSED
            }
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

    @Override
    public String toString() {
        return "\n{" +
                "\n- First name= " + firstName +
                "\n- Last name= " + lastName +
                "\n- Phone number= " + phoneNumber +
                "\n- title='" + getTitle() +
                "\n- salary=" + getSalary() + "DKK" +
                "\n}";
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getSalary() {
        return salary;
    }

    public ArrayList<Staff> getStaffMembers() {
        return staffMembers;
    }
}
