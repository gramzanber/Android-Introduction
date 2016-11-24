package edu.uco.ttachibana.p5TyrelT;

public class Contact implements Comparable<Contact>
{
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;

    public Contact(String first, String last, String phoneNumber, String mail)
    {
        this.lastName = last;
        this.firstName = first;
        this.phoneNumber = phoneNumber;
        this.email = mail;
    }

    public int compareTo(Contact c)
    {
        return lastName.compareTo(c.lastName) != 0 ? lastName.compareTo(c.lastName) : firstName.compareTo(c.firstName);
    }

    public String getLastName() {
        return this.lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getPhone() {
        return this.phoneNumber;
    }
    public String getEmail() {
        return this.email;
    }
    @Override public String toString() {
        return this.lastName;
    }
}
