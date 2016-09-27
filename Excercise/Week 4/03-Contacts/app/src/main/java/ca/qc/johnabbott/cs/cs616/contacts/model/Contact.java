package ca.qc.johnabbott.cs.cs616.contacts.model;

import java.util.Date;

/**
 * Contact class for ListView lecture and maybe more.
 *
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class Contact {

    // store next ID to be given to the next Contact created
    private static long currentId = 0;

    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date lastCalled;
    private int callCount;

    public Contact() {
        id = currentId++;
    }

    public Contact(String firstName, String lastName, String phoneNumber) {
        this(); // call the default constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Contact(int callCount, String firstName, String lastName, String phoneNumber, Date lastCalled) {
        this(); // call the default constructor
        this.callCount = callCount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.lastCalled = lastCalled;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Contact setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Contact setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getLastCalled() {
        return lastCalled;
    }

    public Contact setLastCalled(Date lastCalled) {
        this.lastCalled = lastCalled;
        return this;
    }

    public int getCallCount() {
        return callCount;
    }

    public Contact setCallCount(int callCount) {
        this.callCount = callCount;
        return this;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastCalled=" + lastCalled +
                ", callCount=" + callCount +
                '}';
    }
}
