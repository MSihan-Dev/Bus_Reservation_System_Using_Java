import java.util.InputMismatchException;
public class InterfaceCommon {
    public static CustomerPerDetails loggedInCustomer;
    public static DriverPerDetails loggedInDriver;
   
    public static void Customerlogin() {
        String username;
        String password;
        try {
            System.out.println("\nSign In");
            System.out.println("------------");
            username = Database.getInput(
                "Enter username: ", 
                input -> !input.isEmpty(), 
                "Username cannot be empty."
            );
            password = Database.getInput(
                "Enter password: ", 
                input -> !input.isEmpty(), 
                "Password cannot be empty."
            );
            try {
                AuthenticationService Auth = new AuthenticationService();
                CustomerPerDetails customer = Auth.CustomerAuthenticate(username, password);
                if (customer != null) {
                    String storedUsername = customer.getUsername();
                    String storedPassword = customer.getPassword();
                    
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        loggedInCustomer = customer;
                        System.out.println("----------------------------");
                        System.out.println("Login successful!");
                        InterfaceCustomer.CustomerdisplayMainMenu();
                        return;
                    }
                } else if ("Admin".equals(username) && "adm".equals(password)) {
                    loggedInCustomer = null;
                    InterfaceAdmin.AdminShowMainMenu();
                    return;
                }
                System.out.println("----------------------------");
                System.out.println("Invalid credentials. Please try again.");
                displayLoginMenu();
            } catch (Exception e) {
                System.out.println("An error occurred during the login process. Please try again.");
                displayLoginMenu();
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the login process. Please try again.");
            displayLoginMenu();
        }
    }

    public static void Driverlogin() {
        String username;
        String password;
        try {
            System.out.println("\nSign In");
            System.out.println("------------");

            username = Database.getInput(
                "Enter username: ", 
                input -> !input.isEmpty(), 
                "Username cannot be empty."
            );
            password = Database.getInput(
                "Enter password: ", 
                input -> !input.isEmpty(), 
                "Password cannot be empty."
            );
            try {
                AuthenticationService Auth = new AuthenticationService();
                DriverPerDetails driver = Auth.DriverAuthenticate(username, password);
                
                if (driver != null) {
                    String storedUsername = driver.getUsername();
                    String storedPassword = driver.getPassword();
                    
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        loggedInDriver = driver;
                        System.out.println("----------------------------");
                        System.out.println("Login successful!");
                        InterfaceDriver.DisplayDriverMenu();
                        return;
                    }
                } else if ("Admin".equals(username) && "adm".equals(password)) {
                    loggedInDriver = null;
                    InterfaceAdmin.AdminShowMainMenu();
                    return;
                }
                System.out.println("----------------------------");
                System.out.println("Invalid credentials. Please try again.");
                displayLoginMenu();
            } catch (Exception e) {
                System.out.println("An error occurred during the login process. Please try again.");
                displayLoginMenu();
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the login process. Please try again.");
            displayLoginMenu();
        }
    }

    private static void register() {
        System.out.println("1. As a Customer");
        System.out.println("2. As a Driver");
        try {
            int choice = Database.getChoice();
            if (choice == -1) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                register();
                return;
            }

            switch (choice) {
                case 1:
                    CustomerPerDetails customer = new CustomerPerDetails();
                    customer.InsertCustomer();
                    System.out.println("Registration successful! Please log in.");
                    Customerlogin();
                    break;
                case 2:
                    DriverPerDetails driver = new DriverPerDetails();
                    driver.InsertDriver();
                    System.out.println("Registration successful! Please log in.");
                    Driverlogin();
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number (1 or 2).");
            displayLoginMenu();
        }
    }

    public static void displayLoginMenu() {
        System.out.println("\n\n-----WELCOME-----");
        System.out.println("1. Login (Already a member)");
        System.out.println("2. Register (I'm not a member)");
        try {
            int choice = Database.getChoice();
            if (choice == -1) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                displayLoginMenu();
                return;
            }    
            switch (choice) {
                case 1:
                        System.out.println("\n1. If You are Customer Please Select 1");
                        System.out.println("2. If you are Driver Please select 2");
                        try {
                    
                            int login = Database.getChoice();
                            if (login == -1) {
                                System.out.println("Invalid input. Please enter a number (1 or 2).");
                                displayLoginMenu();
                                return;
                            }    
                
                            switch (login) {
                                case 1:
                                    Customerlogin();
                                    break;
                                case 2:
                                    Driverlogin();
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please select 1 or 2.");
                                    displayLoginMenu();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number (1 or 2).");
                            displayLoginMenu();
                        }
                        break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    displayLoginMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number (1 or 2).");
            displayLoginMenu();
        }
    }

    public static void main(String[] args) {
        Database.DbFk();
        Bus.DummyData();
        displayLoginMenu();
    }
}
