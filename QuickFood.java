/* Create a food delivery system for a company called "Food Quick"
The system keeps track of the orders and distribute accordingly. */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class QuickFood {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        Customer customer = null;
        Restaurant restaurant = null;

        try {
            // Prompt the customer for details
            System.out.print("Enter ordernumber: ");
            int orderNumber = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String contactNumber = scanner.nextLine();
            System.out.print("Enter address: ");
            String address = scanner.nextLine();
            System.out.print("Enter location: ");
            String location = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            // Create a new Customer object using the user input
            customer = new Customer(orderNumber, name, contactNumber, address, location, email);
            
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
        }

        try {
            // Prompt the user for restaurant details
            System.out.print("Enter restaurant name: ");
            String nameRestaurant = scanner.nextLine();
            System.out.print("Enter restaurant location: ");
            String locationRestaurant = scanner.nextLine();
            System.out.print("Enter restaurant phone number: ");
            String contactNumberRestaurant = scanner.nextLine();

            // Create a new Restaurant object using the user input
            restaurant = new Restaurant(nameRestaurant, locationRestaurant, contactNumberRestaurant);
            
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
        }

        
        
     // Read the drivers-info.txt file and check for the driver's location
        List<Driver> drivers = new ArrayList<>();
        
        try {
            String driversFilePath = "drivers-info.txt";
            Scanner driverScanner = new Scanner(new File(driversFilePath));
            while (driverScanner.hasNextLine()) {
                String line = driverScanner.nextLine();
                String[] parts = line.split(", "); // split by comma followed by 1 space
                String driverName = parts[0];
                String driverLocation = parts[1];
                int driverLoad = Integer.parseInt(parts[2]); 
                drivers.add(new Driver(driverName, driverLocation, driverLoad));
            }
            driverScanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return; // exit the method if the file is not found
        }

        // Find a driver for the customer's location and with lowest load in that location
        Driver selectedDriver = null;
        for (Driver d : drivers) {
            if (d.getLocation().toLowerCase().equals(customer.getLocation().toLowerCase())) {
                if (selectedDriver == null || d.getLoad() < selectedDriver.getLoad()) {
                    selectedDriver = d;
                }
            }
        }


        
        // Prompt the user for the order details
        System.out.print("Enter item 1: ");
        String item1 = scanner.nextLine();
        System.out.print("Enter item 1 price: ");
        double item1Price = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Enter item 2: ");
        String item2 = scanner.nextLine();
        System.out.print("Enter item 2 price: ");
        double item2Price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter item 3: ");
        String item3 = scanner.nextLine();
        System.out.print("Enter item 3 price: ");
        double item3Price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter special instructions: ");
        String specialInstructions = scanner.nextLine();

        // Calculate the total amount
        double totalAmount = item1Price + item2Price + item3Price;
        
        
        // Create an invoice for the customer
        String invoiceText = "";
        if (selectedDriver == null) {
            invoiceText = "Sorry! Our drivers are too far away from you to be able to deliver to your location.";
        } else {
            invoiceText = "Order number: " + customer.getOrderNumber() +
                "\nCustomer: " + customer.getName() +
                "\nEmail: " + customer.getEmail() +
                "\nPhone number: " + customer.getContactNumber() +
                "\nLocation: " + customer.getLocation() +
                "\n\nYou have ordered the following from " + restaurant.getName() + ":" +
                "\n\n1 x " + item1 + " (R" + item1Price + ")" +
                "\n1 x " + item2 + " (R" + item2Price + ")" +
                "\n1 x " + item3 + " (R" + item3Price + ")" +
                "\n\nSpecial instructions: " + specialInstructions + 
                "\n\nTotal: R" + totalAmount + 
                "\n\n" + selectedDriver.getName() + " is nearest to the restaurant and will be delivering your order to you at:" +
                "\n\n" + customer.getAddress() + 
                "\n"+ customer.getLocation() +
                "\n\nIf you need to contact the restaurant, their number is " + restaurant.getContactNumber() + ".";
        }

        //Write the invoice text to a file
        String invoicesDirectoryPath = "invoices"; // Relative path to the directory
        File invoicesDirectory = new File(invoicesDirectoryPath);

        if (!invoicesDirectory.exists()) {
            invoicesDirectory.mkdir(); // Create the directory if it doesn't exist
        }

        String outputFile = invoicesDirectoryPath + "/invoice_" + customer.getOrderNumber() + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println(invoiceText);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}



