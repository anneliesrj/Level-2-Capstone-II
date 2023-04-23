// create class for restaurant
public class Restaurant {
	private String nameRestaurant;
    private String locationRestaurant;
    private String contactNumberRestaurant;

    public Restaurant(String name, String location, String contactNumber) {
        this.nameRestaurant = name;
        this.locationRestaurant = location;
        this.contactNumberRestaurant = contactNumber;
    }

    public String getName() {
        return nameRestaurant;
    }

    public String getLocation() {
        return locationRestaurant;
    }

    public String getContactNumber() {
        return contactNumberRestaurant;
    }
}
