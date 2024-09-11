public class InterfaceDriver extends InterfaceCommon{
    static int Driverid = loggedInDriver.getDriverId();

    public static void DisplayDriverMenu(){
        System.out.println("\nMain Menu");
        System.out.println("================");
        System.out.println("1. Check Bus Details");
        System.out.println("2. Check Reserved customers");
        System.out.println("3. Cancel Customer Reservation");
        System.out.println("4. Check Profile");
        System.out.println("5. Logout");
        int choice = Database.getChoice();
        if (choice == -1) {
            System.out.println("Invalid input. Please enter a number (1 to 5).");
            DisplayDriverMenu();
            return;
        }    

        switch (choice) {
            case 1:
                checkBus();
                DisplayDriverMenu();
                break;
            case 2:
                checkReservedCustomers();
                DisplayDriverMenu();
                break;
            case 3:
                bus.cancelReservation();
                DisplayDriverMenu();
                break;
            case 4:
                checkPrf();
                break;
            case 5:
                System.out.println("Logout....");
                System.out.println("==========================================");
                InterfaceCommon.main(null);
                break;
            default:
                System.out.println("Invalid Choice. Please enter a number between 1 and 5");
        }
    }

    public static Bus searchBusById() {
        Bus foundBus = null;
        for (Bus busDetails : Database.busses) {
            if (busDetails.getDriverId() == loggedInDriver.getDriverId()) {
                foundBus = busDetails;
                break;
            }
        }
        return foundBus;
    }
    
    static Bus bus = new Bus(); 

    private static void checkBus(){
        Bus confirmBus = searchBusById();
        if (confirmBus != null) {
            System.out.println("     Driver ID: " + confirmBus.getDriverId());
            System.out.println("        Bus No: " + confirmBus.getBusNumber());
            System.out.println("   Total seats: " + confirmBus.getTotalSeats());
            System.out.println(" Starting Time: " + confirmBus.getStartingTime());
            System.out.println("Starting Point: " + confirmBus.getStartingPoint());
            System.out.println("  Ending Point: " + confirmBus.getEndingPoint());
            System.out.println("          Fare: " + confirmBus.getFare());
            System.out.println("\n1. Do you want to update?");
            System.out.println("2. Go Back");
            int choice = Database.getChoice();
            if (choice == -1) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                checkBus();
                return;
            }    

            switch (choice) {
                case 1:
                    System.out.println("\nUpdate Bus Details");
                    System.out.println("--------------------------");  
                    upadateBusByCustomer();
                    DisplayDriverMenu();
                    break;
                case 2:
                    DisplayDriverMenu();
                    break;
                default:
                    break;
            }
        }
        else{
            System.out.println("Bus Not Found");
            System.out.println(loggedInDriver.getDriverId());
            System.out.println("1. Press 1 to Add Bus Details");
            int like = Database.getChoice();
            if (like == -1) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                checkBus();
                return;
            }    
            switch (like) {
                case 1:
                    bus.insertBus(loggedInDriver.getDriverId());
                    checkBus();
                    break;
            
                default:
                    System.out.println("1. Press 1 to Add Bus Details");
                    break;
            }
        }
    }
    

    private static void checkReservedCustomers() {
        Bus confirmBus = searchBusById();
        System.out.println(confirmBus.getDriverId());
        if (confirmBus != null) {
            String busNo = confirmBus.getBusNumber();
            System.out.println(busNo);
            if (busNo != null) {
                boolean reservationFound = false;
                for (BusReservation reservation : bus.reservations) {
                    System.out.println(reservation.getBusNO());
                    if (reservation.getBusNO().equals(busNo)) {
                        System.out.println(reservation);
                        reservationFound = true;
                    }
                }
                if (!reservationFound) {
                    System.out.println("No reservations found.");
                }
            } else {
                System.out.println("Register Your Bus.");
            }
        }
    }
    
    private static void checkPrf(){
        System.out.println("   Id Number: " + loggedInDriver.getDriverId());
        System.out.println("        Name: " + loggedInDriver.getName());
        System.out.println("Phone Number: " + loggedInDriver.getMobileNumber());
        System.out.println("       Email: " + loggedInDriver.getEmail());
        System.out.println("         Age: " + loggedInDriver.getAge());
        System.out.println("        City: " + loggedInDriver.getCity());
        System.out.println("     Address: " + loggedInDriver.getAddress());
        System.out.println("      Gendar: " + loggedInDriver.getGender());
        System.out.println("\n1. Do you want to update?");
        System.out.println("2. Go Back");
        int choice = Database.getChoice();
        if (choice == -1) {
            System.out.println("Invalid input. Please enter a number (1 or 2).");
            checkPrf();
            return;
        }    

        switch (choice) {
            case 1:
                DriverPerDetails driverPerDetails = new DriverPerDetails();
                driverPerDetails.UpdateDriver(Driverid);
                DisplayDriverMenu();
                break;
            case 2:
                DisplayDriverMenu();
                break;
            default:
                break;
        }
    }

    private static void upadateBusByCustomer(){
        Bus confirmBus = searchBusById();
        if (confirmBus != null) {
            bus.updateBus(confirmBus.getBusNumber());
        }
    }
}
