import java.util.ArrayList;
import java.util.Scanner;


public class Staff extends Person {

    private String title;
    private int salary;

    public Staff(String firstName, String lastName, int phoneNumber, String title, int salary) {
        super(firstName, lastName, phoneNumber);
        this.title = title;
        this.salary = salary;
    }
/*

*/
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
}
