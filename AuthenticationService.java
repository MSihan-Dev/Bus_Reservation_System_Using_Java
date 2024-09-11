public class AuthenticationService {
    public CustomerPerDetails CustomerAuthenticate(String username, String password) {
        CustomerPerDetails user = null;
        for (CustomerPerDetails customer : Database.customers) {
            if (customer.getUsername().equals(username) && 
                customer.getPassword().equals(password)) {
                    user = customer;
                break;
            }
        }
        return user;
    }

    public DriverPerDetails DriverAuthenticate(String username, String password) {
        DriverPerDetails user = null;
        for (DriverPerDetails driver : Database.drivers) {
            if (driver.getUsername().equals(username) && 
                driver.getPassword().equals(password)) {
                    user = driver;
                break;
            }
        }
        return user;
    }
}
