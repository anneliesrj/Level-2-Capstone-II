// create class for driver
public class Driver {
    private String nameDriver;
    private String locationDriver;
    private int loadDriver;

    public Driver(String name, String location, int load) {
        this.nameDriver = name;
        this.locationDriver = location;
        this.loadDriver = load;
    }

    public String getName() {
        return nameDriver;
    }

    public String getLocation() {
        return locationDriver;
    }
    
    public int getLoad() {
    	return loadDriver;
    }
}
