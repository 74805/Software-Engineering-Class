package Exe1;

public class Contact {
    private String name;
    private int phoneNumber;

    public Contact(String n, int p) {
        this.name = n;
        this.phoneNumber = p;
    }

    @Override
    public String toString() {
        return name + " - " + phoneNumber + "\n";
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setContact(String name, int phoneNumber) {
        this.name=name;
        this.phoneNumber=phoneNumber;
    }
}
