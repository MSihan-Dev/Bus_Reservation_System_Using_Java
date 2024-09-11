public class InterfaceCustomer extends InterfaceCommon {
    private static Bus bus = new Bus();
    static String cusname = loggedInCustomer.getName();
    static String phoneNo = loggedInCustomer.getMobileNumber();

    protected static void CustomerdisplayMainMenu() {
        try {
            if (loggedInCustomer == null) {
                System.out.println("User not logged in.");
                displayLoginMenu();
                return;
            }
            System.out.println("\n----------------------------");
            System.out.println("Welcome " + cusname);
            System.out.println("----------------------------");
            System.out.println("Main Menu");
            System.out.println("================");
            System.out.println("1. Search Buses");
            System.out.println("2. Check Reserve Seat");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Request New Seat");
            System.out.println("5. check the Notifications");
            System.out.println("6. Check Profile");
            System.out.println("7. Logout");
            int choice = Database.getChoice();
            if (choice == -1) {
                System.out.println("Invalid input. Please enter a number (1 to 7).");
                CustomerdisplayMainMenu();
                return;
            }    

            switch (choice) {
                case 1:
                    Bus.searchBusesByRouteAndTime();
                    CustomerdisplayMainMenu();
                    break;
                case 2:
                    searchRervetionByNamePhone();
                    CustomerdisplayMainMenu();
                    break;
                case 3:
                    System.out.println("Cancel Reservation:");
                    bus.cancelReservation();
                    CustomerdisplayMainMenu();
                    break;
                case 4:
                    reserveBus();
                    CustomerdisplayMainMenu();
                    break;
                case 5:
                    bus.searchReserveForNoti(cusname,phoneNo);
                    CustomerdisplayMainMenu();
                    break;
                case 6:
                    checkPrf();
                    break;
                case 7:
                    System.out.println("Logout....");
                    System.out.println("==========================================");
                    InterfaceCommon.main(null);
                    break;
                default:
                    System.out.println("Invalid Choice. Please enter a number between 1 and 7");
            }
        } catch (Exception e) {
            System.out.println("Invalid Choice. Please enter a number between 1 and 7");
            CustomerdisplayMainMenu();
        }
    }


    private static void reserveBus() {
        System.out.println("\nMake reservation");
        System.out.println("------------------");
        bus.CheckForReservation(cusname,phoneNo); 
        CustomerdisplayMainMenu();
    }

    private static BusReservation searchRervetionByNamePhone() {
        System.out.println("\nCheck the reservations");
        System.out.println("------------------");
        BusReservation _reservation = null;
        for (BusReservation reservation : bus.reservations) {
            if (reservation.getCustomerName().equalsIgnoreCase(loggedInCustomer.getName()) &&
                reservation.getPhonNo().equals(loggedInCustomer.getMobileNumber())) {
                _reservation = reservation;
                System.out.println(_reservation);
            } 
        }
        return _reservation;
    }
    
    private static void checkPrf(){
        System.out.println("    Name: " + loggedInCustomer.getName());
        System.out.println("Phone No: " + loggedInCustomer.getMobileNumber());
        System.out.println("   Email: " + loggedInCustomer.getEmail());
        System.out.println("     Age: " + loggedInCustomer.getAge());
        System.out.println("    City: " + loggedInCustomer.getCity());
        System.out.println(" Address: " + loggedInCustomer.getAddress());
        System.out.println("\n1. Do you want to update?");
        System.out.println("2. Go Back");
        int chice = Database.getChoice();
        if (chice == -1) {
            System.out.println("Invalid input. Please enter a number (1 or 2).");
            checkPrf();
            return;
        }    

        switch (chice) {
            case 1:
                CustomerPerDetails customerPerDetails = new CustomerPerDetails();
                customerPerDetails.UpdateCustomer(loggedInCustomer.getName(), loggedInCustomer.getMobileNumber());
                CustomerdisplayMainMenu();
                break;
            case 2:
                CustomerdisplayMainMenu();
                break;
            default:
                break;
        }
    }
}
