package Exe2;

public class Contact {
    private String name;
    private int phoneNumber;

    public Contact() {
        this("", 0);
    }

    public Contact(String n, int p) {
        this.name = n;
        this.phoneNumber = p;
    }

    public Contact(Contact other)
    {
        this(other.getName(), other.getPhoneNumber());
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

    public void setPhoneNumber(int phoneNumber) throws Exception {
	    String regEx = "5[0-9]{8}";
	    if(Integer.toString(phoneNumber).matches(regEx))
	        this.phoneNumber = phoneNumber;
        else
        	throw new Exception("Invalid phone number");
	}
	
	public void setContact(String name, int phoneNumber) throws Exception{
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
    }

    public boolean equals(Contact other)
    {
        return this.name.equalsIgnoreCase(other.name) && this.phoneNumber == other.phoneNumber;
    }
    
}
