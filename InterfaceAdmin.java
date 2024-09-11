public class InterfaceAdmin extends InterfaceCommon {
    public static void AdminShowMainMenu(){
        System.out.println("\nMain Menu");
        System.out.println("================");
        System.out.println("1. Bus Managment");
        System.out.println("2. Customer Managment");
        System.out.println("3. Driver Managment");
        System.out.println("4. Logout");
        System.out.println("Enter your choice");

        int choice = Database.getChoice();
        if (choice == -1) {
            System.out.println("Invalid input. Please enter a number (1 to 4).");
            AdminShowMainMenu();
            return;
        }    

        switch (choice) {
            case 1:
                Bus.showMenu();
                break;
            case 2:
                CustomerPerDetails.CustomershowMenu();
                break;
            case 3:
                DriverPerDetails.DrivershowMenu();
                break;
            case 4:
                System.out.println("Logout....");
                InterfaceCommon.displayLoginMenu();
                break;
            default:
            System.out.println("Invalid Choice. Please enter a number between 1 and 4");
            AdminShowMainMenu();
        }    
    }
}


