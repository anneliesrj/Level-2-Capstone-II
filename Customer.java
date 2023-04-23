//create class for customer
public class Customer {
    private int orderNumber;
    private String name;
    private String contactNumber;
    private String address;
    private String location;
    private String email;

    public Customer(int orderNumber, String name, String contactNumber, String address, String location, String email) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
        this.location = location;
        this.email = email;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }
}