import java.util.Scanner;

public class CustomerPerDetails extends Database{
    private String Name, MobileNumber, Email, City, Address,Gender,username,password;
    private int Age;

    private static Scanner scanner = new Scanner(System.in);

    public CustomerPerDetails() {
    }

    public CustomerPerDetails(String name, String mobileNumber, String email, String city, String address, int age, String gender, String username,String password) {
        this.Name = name;
        this.Gender = gender;
        this.MobileNumber = mobileNumber;
        this.Email = email;
        this.City = city;
        this.Address = address;
        this.Age = age;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.MobileNumber = mobileNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static void CustomershowMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nHome > Customer");
            System.out.println("=================");
            System.out.println("1. Register Customer");
            System.out.println("2. Update Customer Details");
            System.out.println("3. Delete Customer");
            System.out.println("4. View All Customers");
            System.out.println("5. Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            CustomerPerDetails customer = new CustomerPerDetails();
            switch (choice) {
                case 1:
                    customer.InsertCustomer();
                    break;
                case 2:
                    System.out.println("\nUpdate Customer Details");
                    System.out.println("------------------------");
                    System.out.print("Find Customer By Name: ");
                    String findName = scanner.nextLine();
                    String phoneNo = Database.getInput(
                        "Enter Customer Mobile No: ", 
                        input -> input.matches("\\d{10}"), 
                        "Invalid mobile number format. Please enter a 10-digit number."
                    );
                    customer.UpdateCustomer(findName, phoneNo);
                    break;
                case 3:
                    customer.DeleteCustomer();
                    break;
                case 4:
                    ViewAllCustomers();
                    break;
                case 5:
                    InterfaceAdmin.AdminShowMainMenu();
                break;
                default:
                    System.out.println("Invalid Choice. Please enter a number between 1 and 5.");
            }
        }
    }


    public void InsertCustomer() {
        System.out.println("\nRegister Customer Details");
        System.out.println("--------------------------");
        this.Name = getInput(
            "Enter Customer Name: ", 
            input -> !input.isEmpty() && input.length() > 2, 
            "Customer name cannot be empty. Please enter a valid name."
        );

        this.MobileNumber = getInput(
            "Enter Customer Mobile: ", 
            input -> input.matches("\\d{10}"), 
            "Invalid mobile number format. Please enter a 10-digit number."
        );

        Email = getInput(
            "Enter Customer Email: ", 
            input -> !input.isEmpty()&& input.length() > 5, 
            "Email cannot be empty. Please enter a valid email."
        );

        City = getInput(
            "Enter Customer City: ", 
            input -> !input.isEmpty() && input.length() > 2, 
            "City cannot be empty. Please enter a valid city."
        );

        Address = getInput(
            "Enter Customer Address: ", 
            input -> !input.isEmpty() && input.length() > 5, 
            "Address cannot be empty. Please enter a valid address."
        );

        String gender = getInput(
            "Choose the Gender:\n1. Male\n2. Female\n", 
            input -> input.equals("1") || input.equals("2"), 
            "Invalid choice. Please enter 1 for Male or 2 for Female."
        );
        this.Gender = gender.equals("1") ? "Male" : "Female";

        this.Age = getIntInput(
            "Enter Customer Age: ", 
            "Age cannot be empty. Please enter a valid age."
        );
        System.out.println("-------------------------------------------------------");
        username = getInput(
            "Enter username: ", 
            input -> !input.isEmpty() && input.length() > 6, 
            "Username cannot be empty and must be longer than 6 characters."
        );

        this.password = getInput(
            "Enter password: ", 
            input -> !input.isEmpty() && input.length() > 6, 
            "Password cannot be empty and must be longer than 6 characters."
        );

        customers.add(this);
        System.out.println("Inserted Successfully");
    }

    public void UpdateCustomer(String findName, String phoneNo) {
        CustomerPerDetails customer = Database.searchCusByNamePhone(findName,phoneNo);
        if (customer != null) {
            customer.Name = getInput(
                "Enter Customer Name: ", 
                input -> !input.isEmpty() && input.length() > 2, 
                "Customer name cannot be empty. Please enter a valid name."
            );
    
            customer.MobileNumber = getInput(
                "Enter Customer Mobile No: ", 
                input -> input.matches("\\d{10}"), 
                "Invalid mobile number format. Please enter a 10-digit number."
            );
    
            customer.Email = getInput(
                "Enter Customer Email: ", 
                input -> !input.isEmpty() && input.length() > 5, 
                "Email cannot be empty. Please enter a valid email."
            );
    
            customer.City = getInput(
                "Enter Customer City: ", 
                input -> !input.isEmpty() && input.length() > 2, 
                "City cannot be empty. Please enter a valid city."
            );
    
            customer.Address = getInput(
                "Enter Customer Address: ", 
                input -> !input.isEmpty() && input.length() > 5, 
                "Address cannot be empty. Please enter a valid address."
            );
    
            String gender = getInput(
                "Choose the Gender:\n1. Male\n2. Female\n", 
                input -> input.equals("1") || input.equals("2"), 
                "Invalid choice. Please enter 1 for Male or 2 for Female."
            );
            customer.Gender = gender.equals("1") ? "Male" : "Female";
    
            customer.Age = getIntInput(
                "Enter Customer Age: ", 
                "Age cannot be empty. Please enter a valid age."
            );
        
            System.out.println("Customer Updated Successfully");
        }
        else{
            System.out.println("Customer Not found");
        }
    }


    public void DeleteCustomer() {
        System.out.println("\nDelete Customer Details");
        String findName = getInput(
            "Enter Customer Name: ", 
            input -> !input.isEmpty() && input.length() > 2, 
            "Customer name cannot be empty. Please enter a valid name."
        );

        String PhoneNo = getInput(
            "Enter Customer Mobile: ", 
            input -> input.matches("\\d{10}"), 
            "Invalid mobile number format. Please enter a 10-digit number."
        );
        
        CustomerPerDetails customer = searchCusByNamePhone(findName,PhoneNo);

        if (customer != null) {
            customers.remove(customer);
            System.out.println("Customer Deleted Successfully");
        } else {
            System.out.println("Customer Not Found for Deletion.");
        }
    }

    public static void ViewAllCustomers() {
        System.out.println("\nAll Customers...");
        for (int i = 0; i < customers.size() ; i++) {
            System.out.println((i+1) + " -> "+ customers.get(i));
        }
        System.out.println("========================================================");
        Filter();
    }

    public static void Filter(){
        System.out.println("\n1. Filter");
        System.out.println("2. Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("\n===============");
                System.out.println("1. Sort Age by Bubble Sorting");
                System.out.println("2. Sort Age by Selection Sorting");
                System.out.println("3. Newest to Oldest");
                System.out.println("4. Reset");
                int Filter = scanner.nextInt();
                switch (Filter) {
                    case 1:
                        try {
                            System.out.println("\nSorted Customers:");
                            BubbleSort.bubbleSort(customers); // Ensure this method handles exceptions internally if needed
                            Filter(); // Ensure this method handles exceptions internally if needed
                        } catch (Exception e) {
                            System.out.println("An error occurred while sorting or filtering customers: " + e.getMessage());
                            e.printStackTrace(); // for debugging purposes
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("\nSorted Customers:");
                            SelectionSort.selectionSort(customers); // Ensure this method handles exceptions internally if needed
                            Filter(); // Ensure this method handles exceptions internally if needed
                        } catch (Exception e) {
                            System.out.println("An error occurred while sorting or filtering customers: " + e.getMessage());
                            e.printStackTrace(); // for debugging purposes
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("\nNewest to Oldest:");
                            Stack stack = new Stack();
                            for (CustomerPerDetails cutomerr : Database.customers) {
                                stack.stackPush(cutomerr);
                            }
                            stack.stackDisplay();
                            Filter(); // Ensure this method handles exceptions internally if needed
                        } catch (Exception e) {
                            System.out.println("An error occurred while displaying the stack or filtering: " + e.getMessage());
                            e.printStackTrace(); // for debugging purposes
                        }
                        break;  
                    case 4:
                        try {
                            ViewAllCustomers(); // Ensure this method handles exceptions internally if needed
                        } catch (Exception e) {
                            System.out.println("An error occurred while viewing all customers: " + e.getMessage());
                            e.printStackTrace(); // for debugging purposes
                        }
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
                CustomershowMenu();
                break;        
            default:
                System.out.println("Invalid Choice");
                break;
        }
    }


    @Override
    public String toString() {
        return 
                "Name: " + Name + " | " +
                "Gender: " + Gender + " | "+
                "Mobile Number: " + MobileNumber + " | " +
                "Email: " + Email + " | " +
                "City: " + City + " | " +
                "Address: " + Address + " | " +
                "Age: " + Age +
                "."
                ;
    }
}
