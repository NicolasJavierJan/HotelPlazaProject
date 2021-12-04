import java.io.Serializable;

public abstract class Person  implements Serializable {

    protected String firstName;
    protected String lastName;
    protected int phoneNumber;

    public Person(String firstName, String lastName, int phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber = phoneNumber;
    }
}
