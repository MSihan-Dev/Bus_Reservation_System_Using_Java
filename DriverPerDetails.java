


public class DriverPerDetails extends Database{
    private static int idCounter = 4401;
    private String Name, MobileNumber, Email, City, Address,Gender,username,password;
    private int Age, DriverId;

    public DriverPerDetails() {
    }


    public DriverPerDetails(String name, String mobileNumber, String email, String city, String address, int age, String gender, String username,String password) {
        this. DriverId = getNextId();
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


    public int getDriverId() {
        return DriverId;
    }

    public void setDriverId(int driverId) {
        DriverId = driverId;
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

    Database database = new Database();
    public static void DrivershowMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nHome > Customer");
            System.out.println("=================");
            System.out.println("1. Register Driver");
            System.out.println("2. Update Driver Details");
            System.out.println("3. Delete Driver");
            System.out.println("4. View All Drivers");
            System.out.println("5. Main Menu");
            System.out.print("Enter your choice: ");

            int choice = getChoice();
            if (choice == -1) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                DrivershowMenu();
                return;
            }    

            DriverPerDetails driver = new DriverPerDetails();
            InterfaceAdmin interfaceAdmin = new InterfaceAdmin();

            switch (choice) {
                case 1:
                    driver.InsertDriver();
                    break;
                case 2:
                    System.out.println("\nUpdate Driver Details");
                    System.out.println("------------------------");
                    System.out.print("Find Customer By Name: ");
                    int DriverId = Database.getIntInput(
                        "Enter Driver Id: ", 
                        "Invalid Id number format."
                    );
                    driver.UpdateDriver(DriverId);
                    break;
                case 3:
                    driver.DeleteDriver();
                    DrivershowMenu();
                    break;
                case 4:
                    ViewAllDrivers();
                    break;
                case 5:
                    interfaceAdmin.AdminShowMainMenu();
                break;
                default:
                    System.out.println("Invalid Choice. Please enter a number between 1 and 5.");
            }
        }
    }


    public void InsertDriver() {
        System.out.println("\nRegister Driver Details");
        System.out.println("--------------------------");

        this.DriverId = getNextId();

        this.Name = getInput(
            "Enter Driver Name: ", 
            input -> !input.isEmpty() && input.length() > 2, 
            "Driver name cannot be empty. Please enter a valid name."
        );

        this.MobileNumber = getInput(
            "Enter Driver Mobile: ", 
            input -> input.matches("\\d{10}"), 
            "Invalid mobile number format. Please enter a 10-digit number."
        );

        Email = getInput(
            "Enter Driver Email: ", 
            input -> !input.isEmpty()  && input.length() > 5, 
            "Email cannot be empty. Please enter a valid email."
        );

        City = getInput(
            "Enter Driver City: ", 
            input -> !input.isEmpty() && input.length() > 2, 
            "City cannot be empty. Please enter a valid city."
        );

        Address = getInput(
            "Enter Driver Address: ", 
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
            "Enter Driver Age: ", 
            "Age cannot be empty. Please enter a valid age."
        );
        System.out.println("-------------------------------------------------------");
        username = getInput(
            "Enter username: ", 
            input -> !input.isEmpty() && input.length() > 5, 
            "Username cannot be empty and must be longer than 6 characters."
        );

        this.password = getInput(
            "Enter password: ", 
            input -> !input.isEmpty() && input.length() > 5, 
            "Password cannot be empty and must be longer than 6 characters."
        );

        drivers.add(this);
        System.out.println("Inserted Successfully");
    }

    private DriverPerDetails searchDriverbyId(int driverid){
        DriverPerDetails _driver = null;
        for (DriverPerDetails driver : drivers) {
            if (driverid == driver.DriverId) {
                _driver = driver;
                break;
            }
        }
        return _driver;
    }

    public void UpdateDriver(int driverIdNo) {
        DriverPerDetails driverDetail = searchDriverbyId(driverIdNo);
        if (driverDetail != null) {
            driverDetail.Name = getInput(
                "Enter Driver Name: ", 
                input -> !input.isEmpty() && input.length() > 2, 
                "Driver name cannot be empty. Please enter a valid name."
            );
    
            driverDetail.MobileNumber = getInput(
                "Enter Driver Mobile: ", 
                input -> input.matches("\\d{10}"), 
                "Invalid mobile number format. Please enter a 10-digit number."
            );
    
            driverDetail.Email = getInput(
                "Enter Driver Email: ", 
                input -> !input.isEmpty() && input.length() > 5, 
                "Email cannot be empty. Please enter a valid email."
            );
    
            driverDetail.City = getInput(
                "Enter Driver City: ", 
                input -> !input.isEmpty() && input.length() > 2, 
                "City cannot be empty. Please enter a valid city."
            );
    
            driverDetail.Address = getInput(
                "Enter Driver Address: ", 
                input -> !input.isEmpty() && input.length() > 5, 
                "Address cannot be empty. Please enter a valid address."
            );
    
            String gender = getInput(
                "Choose the Gender:\n1. Male\n2. Female\n", 
                input -> input.equals("1") || input.equals("2"), 
                "Invalid choice. Please enter 1 for Male or 2 for Female."
            );
            driverDetail.Gender = gender.equals("1") ? "Male" : "Female";
    
            driverDetail.Age = getIntInput(
                "Enter Driver Age: ", 
                "Age cannot be empty. Please enter a valid age."
            );
        
            System.out.println("Driver Updated Successfully");
        }
        else{
            System.out.println("No Data Found");
        }
    }

    public void DeleteDriver() {
        System.out.println("\nDelete Driver Details");

        int driverid = getIntInput("Ender Driver Id: ", "Must want the Id No");
        DriverPerDetails driver = searchDriverbyId(driverid);

        if (driver != null) {
            drivers.remove(driver);
            System.out.println("Driver Deleted Successfully");
        } else {
            System.out.println("Driver Not Found for Deletion.");
        }
    }

    public static void ViewAllDrivers() {
        System.out.println("\nAll Drivers...");
        for (int i = 0; i < drivers.size() ; i++) {
            System.out.println((i+1) + " -> "+ drivers.get(i));
        }
    }

    @Override
    public String toString() {
        return 
                "ID: " + DriverId +" | " +
                "Name: " + Name + " | " +
                "Gender: " + Gender + " | "+
                "Mobile Number: '" + MobileNumber + " | " +
                "Email: " + Email + " | " +
                "City: " + City + " | " +
                "Address: " + Address + " | " +
                "Age: " + Age + "."
                ;
    }

    private static synchronized int getNextId() {
        if (idCounter >= 9999) {
            idCounter = 1501; 
        }
        return ++idCounter;
    }
}
