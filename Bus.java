import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Bus extends Database {
    private int DriverId;
    private String busNumber;
    private int totalSeats;
    private String startingPoint;
    private String endingPoint;
    private LocalTime startingTime;
    private double fare;
    public List<BusReservation> reservations;
    private ReservationQueue waitingList;

    private static Scanner scanner = new Scanner(System.in);
    Cost_Info costInfo = new Cost_Info();
    CustomerPerDetails customer = new CustomerPerDetails();
    private Notifications notifications;

    public Bus() {
        this.reservations = new ArrayList<>();
        this.waitingList = new ReservationQueue();
        this.notifications = new NotificationMessage();
    }

    public int getDriverId() {
        return DriverId;
    }

    public void setDriverId(int driverId) {
        DriverId = driverId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public List<BusReservation> getReservations() {
        return reservations;
    }

    public ReservationQueue getWaitingList() {
        return waitingList;
    }

    public Bus(int _DriverId,String BusNumber, int totalSeats, String startingPoint, String endingPoint, LocalTime startingTime, double fare) {
        this.DriverId = _DriverId;
        this.busNumber = BusNumber;
        this.totalSeats = totalSeats;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingTime = startingTime;
        this.fare = fare;
        this.reservations = new ArrayList<>();
        this.waitingList = new ReservationQueue();
    }

    private static Bus bus = new Bus();
    public static void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nHome > Bus Management");
            System.out.println("==================");
            System.out.println("1. Rgister Bus");
            System.out.println("2. Update Bus");
            System.out.println("3. Delete Bus");
            System.out.println("4. View All Buses");
            System.out.println("5. Search Buses by Route and Time");
            System.out.println("6. Reserve Bus");
            System.out.println("7. Cancel Reservation");
            System.out.println("8. Check the Waiting Lists");
            System.out.println("9. Promote from Waiting List");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            try {
                int choice = getChoice();
                if (choice == -1) {
                    System.out.println("Invalid input. Please enter a number (1 to 10).");
                    showMenu();
                    return;
                }    
                switch (choice) {
                    case 1:
                        int _driver = Database.getIntInput(
                            "Enter Driver ID: ", 
                            "Invalid number format. Please enter a valid number of Id."
                        );
                        bus.insertBus(_driver);
                        break;
                    case 2:
                        System.out.println("\nUpdate Bus Details");
                        System.out.println("--------------------------");
                        System.out.print("Enter Bus Number to update: ");
                        String findBusNumber = scanner.nextLine();
                        bus.updateBus(findBusNumber);
                        break;
                    case 3:
                        bus.deleteBus();
                        break;
                    case 4:
                        viewAllBuses();
                        break;
                    case 5:
                        searchBusesByRouteAndTime();
                        break;
                    case 6:
                        bus.reserveBus();
                        showMenu();
                        break;
                    case 7:
                        bus.SteptoCencelReservation();
                        bus.cancelReservation();
                        showMenu();
                        break;
                    case 8:
                        bus.displayWaitingList();
                        showMenu();
                        break;
                    case 9:
                        bus.promoteFromWaitingList();
                        showMenu();
                        break;
                    case 10:
                        InterfaceAdmin.AdminShowMainMenu();
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // clear the invalid input from the scanner
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please enter time in HH:mm format.");
                scanner.nextLine(); // clear the invalid input from the scanner
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number.");
            }
        }
    }


    public void insertBus(int _driverId) {
        System.out.println("\nRegister New Bus Details");
        System.out.println("---------------------------");

        this.DriverId = _driverId;

        this.busNumber = getInput(
            "Enter Bus Number: ", 
            input -> !input.isEmpty(), 
            "Bus number cannot be empty. Please enter a valid bus number."
        );

        this.totalSeats = getIntInput(
            "Enter Total Seats: ", 
            "Invalid number format. Please enter a valid number of total seats."
        );

        this.startingPoint = getInput(
            "Enter Starting Point: ", 
            input -> !input.isEmpty(), 
            "Starting point cannot be empty. Please enter a valid starting point."
        );

        this.endingPoint = getInput(
            "Enter Ending Point: ", 
            input -> !input.isEmpty(), 
            "Ending point cannot be empty. Please enter a valid ending point."
        );

        this.startingTime = getTimeInput(
            "Enter Starting Time (HH:mm): ", 
            "Invalid time format. Please enter time in HH:mm format."
        );

        this.fare = getDoubleInput(
            "Enter Fare: ", 
            "Invalid number format. Please enter a valid fare."
        );

        Database.busses.add(this);
        System.out.println("Bus inserted successfully");
    }

    public void updateBus(String findBusNumber) {
        Bus bus = searchBusByNumber(findBusNumber);
        Bus newBus = new Bus();
        if (bus != null) {

            newBus.DriverId = bus.DriverId;

            newBus.busNumber = getInput(
                "Enter Bus Number: ", 
                input -> !input.isEmpty(), 
                "Bus number cannot be empty. Please enter a valid bus number."
            );
    
            newBus.totalSeats = getIntInput(
                "Enter Total Seats: ", 
                "Invalid number format. Please enter a valid number of total seats."
            );
    
            newBus.startingPoint = getInput(
                "Enter Starting Point: ", 
                input -> !input.isEmpty(), 
                "Starting point cannot be empty. Please enter a valid starting point."
            );
    
            newBus.endingPoint = getInput(
                "Enter Ending Point: ", 
                input -> !input.isEmpty(), 
                "Ending point cannot be empty. Please enter a valid ending point."
            );
    
            newBus.startingTime = getTimeInput(
                "Enter Starting Time (HH:mm): ", 
                "Invalid time format. Please enter time in HH:mm format."
            );
    
            newBus.fare = getDoubleInput(
                "Enter Fare: ", 
                "Invalid number format. Please enter a valid fare."
            );

            int index = Database.busses.indexOf(bus);
            Database.busses.set(index, newBus);
            System.out.println("Bus updated successfully");
        } else {
            System.out.println("Bus not found.");
        }
    }

    public void deleteBus() {
        System.out.println("\nDelete Bus Details");
        System.out.println("--------------------------");
        System.out.print("Enter Bus Number to delete: ");
        String findBusNumber = scanner.nextLine();
        Bus bus = searchBusByNumber(findBusNumber);

        if (bus != null) {
            Database.busses.remove(bus);
            System.err.println("Bus deleted successfully");
        } else {
            System.out.println("Bus not found for delete.");
        }
    }

    public void reserveBus() {
        System.out.println("\nMake reservation");
        System.out.println("------------------");
        
        String cusName = Database.getInput("Enter Customer Name: ",
        input -> !input.isEmpty(),
        "Customer Name is Important so insert Please.");
        String phoneNo = Database.getInput("Enter Customer Phone No: ",
        input -> !input.isEmpty(),
        "Customer Phone No is Important so insert Please.");
        CheckForReservation(cusName,phoneNo);
    }

    public void CheckForReservation(String customerName,String MobilNo){
        try{
            System.out.print("Enter Bus Number to reserve: ");
            String busNumberToReserve = scanner.nextLine();
            Bus busToReserve = searchBusByNumber(busNumberToReserve);
            if (busToReserve == null) {
                System.out.println("Bus not found for reservation.");
            } else {
                CustomerPerDetails cusToReserve = searchCusByNamePhone(customerName, MobilNo);
                
                if (cusToReserve != null) {
                    int BookingSeatCounts = Database.getIntInput("How many Seat You Want to Book: ", "Must want to insert the seat cpunt");
                    double TraDistance = busToReserve.getFare();
                    
                    float cost;    
                    System.out.println("==============");    
                    if (TraDistance <= 0) {
                        System.out.println("\nInvalid age");
                        return;
                    } else if (TraDistance <= 10) {
                        cost = 250;
                    } else if (TraDistance >= 10 && TraDistance <= 20){
                        cost = 380;
                    } else if (TraDistance >= 20 && TraDistance <= 45) {
                        cost = 600;
                    } else if (TraDistance >= 45 && TraDistance <= 60) {
                        cost = 800;
                    }else{
                        cost = 1000;
                    }
    
                    float Cost = cost*BookingSeatCounts;
                    String name = cusToReserve.getName();
                    String mobile = cusToReserve.getMobileNumber();
            
                    LocalDateTime now = LocalDateTime.now();
                    int totalSeats = busToReserve.getTotalSeats();
                    int AvailableSeats = totalSeats - reservations.size();
                    int RejectedSeatCount = BookingSeatCounts - AvailableSeats;
                    List<Integer> cusSeatNums = new ArrayList<>();
                    if (BookingSeatCounts <=  AvailableSeats) {
                        for (int i = 1; i <= BookingSeatCounts; i++) {
                            BusReservation reservation3 = new BusReservation(name, mobile, now, reservations.size()+1, cost,busNumberToReserve);
                            reservations.add(reservation3);
                            cusSeatNums.add(reservations.size());
                        }
                        System.out.println("\nYour Cost is: " + Cost);
                        costInfo.Insert_Data();
                        System.out.println("Seats: " + cusSeatNums + ", reserved successfully for " + customerName);
                    } else {
                        System.out.println("Sorry the Available Seats: "+ AvailableSeats+ " But you are booking: "+ BookingSeatCounts);
                        System.out.println("So can accept your " + AvailableSeats +" Your balance seat will goint to waiting List");
                        System.out.println("1. Do you want to continue");
                        System.out.println("2. Go to manin menu");
                        scanner.nextLine();
                        int option = getChoice();
                        if (option == -1) {
                            System.out.println("Invalid input. Please enter a number (1 or 2).");
                            showMenu();
                            return;
                        }                
    
                        switch (option) {
                            case 1:
                                for (int i = 1; i <= AvailableSeats; i++) {
                                    BusReservation reservation1 = new BusReservation(name, mobile, now, reservations.size()+1, cost,busNumberToReserve);
                                    reservations.add(reservation1);
                                    cusSeatNums.add(reservations.size());
                                }
                                System.out.println("Seats: " + cusSeatNums + ", reserved successfully for " + customerName);
                                for (int i = 1; i <= RejectedSeatCount; i++) {
                                    BusReservation reservation3 = new BusReservation(name, mobile, now, 0, cost,busNumberToReserve);
                                    bus.addToWaitingList(reservation3);
                                }
                                System.out.println("Bus is full. so " + RejectedSeatCount + " Seats in waiting list");
                                System.out.println("\nYour Cost is: " + Cost);
                                costInfo.Insert_Data();
                                break;
                            case 2:
                                return;
                            default:
                                break;
                        }
                    }
                } else {
                    System.out.println("\n=======================================");
                    System.out.println("The customer name is not found. Please register first to reserve a bus.");
                    System.out.println("1. Register Customer");
                    System.out.println("2. Main Menu");
                    int choice = scanner.nextInt();
                    
                    switch (choice) {
                        case 1:
                            customer.InsertCustomer(); // Call to insert customer
                            break;
                        case 2:
                            InterfaceAdmin.AdminShowMainMenu(); // Call to show main menu
                            break;
                        default:
                            System.out.println("Invalid Choice");
                            break;
                    }
                }
            }           
        } catch (Exception e) {
        System.out.println("An error occurred. Please enter a valid choice.");
        }
    }
    
    public static List<Bus> searchBusesByRouteAndTime() {
        System.out.println("\nSearch the Bus");
        System.out.println("----------------");

        System.out.print("Enter Starting Point: ");
        String startingPoint = scanner.nextLine();

        System.out.print("Enter Ending Point: ");
        String endingPoint = scanner.nextLine();

        System.out.print("Enter Starting Time (HH:mm): ");
        String startTimeStr = scanner.nextLine();

        LocalTime startingTime = LocalTime.parse(startTimeStr);
        List<Bus> foundBuses = new ArrayList<>();

        for (Bus bus : Database.busses) {
            if (bus.getStartingPoint().equalsIgnoreCase(startingPoint) &&
                    bus.getEndingPoint().equalsIgnoreCase(endingPoint) &&
                    bus.getStartingTime().equals(startingTime)) {
                foundBuses.add(bus);
            }
        }

        if (foundBuses.isEmpty()) {
            System.out.println("\nBuses not found for route " + startingPoint + " to " + endingPoint + " at " + startTimeStr);
        } else {
            System.out.println("\nBuses found for route " + startingPoint + " to " + endingPoint + " at " + startTimeStr + ":");
            for (Bus bus : foundBuses) {
                System.out.println(bus);
            }
        }
        return foundBuses;
    }

    public static void viewAllBuses() {
        System.out.println("\nAll Buses:");
        for (Bus bus : Database.busses) {
            System.out.println(bus);
        }
    }

    private void SteptoCencelReservation(){
        System.out.println("\nCancel the Reservation");
        System.out.println("--------------------------");
        System.out.print("Enter Customer Name: ");
        String findName = scanner.nextLine();
        String phoneNo = Database.getInput(
            "Enter Customer Mobile No: ", 
            input -> input.matches("\\d{10}"), 
            "Invalid mobile number format. Please enter a 10-digit number.");
        for (BusReservation reservation : reservations) {
            if (reservation.getCustomerName().equalsIgnoreCase(findName) && 
                reservation.getPhonNo().equals(phoneNo)) {
                System.out.println(reservation);
            }
        }
    }
    
    public void cancelReservation() {
        int reservationId = Database.getIntInput("Enter the reservation ID for Cancelation: ", "Must provide the ID.");

        boolean found = false;
        for (BusReservation reservation : reservations) {
            if (reservation.getBusReservationId()==(reservationId)) {
                reservations.remove(reservation);
                found = true;
                System.out.println("Reservation cancelled for " + reservationId);
                notifyWaitingList();
                break;
            }
        }
        if (!found) {
            System.out.println("Reservation not found for " + reservationId);
        }
    }

    public void searchReserveForNoti(String cusname, String phoneNo) {
        for (BusReservation reservation : reservations) {
            if (reservation.getCustomerName().equalsIgnoreCase(cusname) &&
                    reservation.getPhonNo().equals(phoneNo)) {
                notifications.requestSuccess(cusname, reservation.getSeatNumber(), reservation.getBusReservationId(), reservation.getRecervationTime());
            }
        }
    }

    public BusReservation searchRervetionByNamePhone(String name, String PhoneNo) {
        System.out.println("\nCheck the reservations");
        System.out.println("------------------");
        BusReservation _reservation = null;
        for (BusReservation reservation : reservations) {
            if (reservation.getCustomerName().equalsIgnoreCase(name) &&
                    reservation.getPhonNo().equals(PhoneNo)) {
                _reservation = reservation;
                System.out.println(_reservation);
            } else{
                System.out.println("No data Found");
            }
        }
        return _reservation;
    }


    private void notifyWaitingList() {
        if (!waitingList.isEmpty()) {
            QNode current = waitingList.getFront();

            while (current != null) {
                BusReservation waitingEntry = current.getResevation();
                int requestedSeat = waitingEntry.getSeatNumber();
                boolean seatAvailable = reservations.stream().noneMatch(reservation -> reservation.getSeatNumber() == requestedSeat);
            
                if (seatAvailable) {
                    reservations.add(waitingEntry);
                    //ReservationQueue.add(waitingEntry);
                    System.out.println("Notified " + waitingEntry.getCustomerName());
                    current = current.getNext();
                    waitingList.dequeue(); // Remove from waiting list
                } else {
                    current = current.getNext();
                }
            }   
        }
    }

    private void promoteFromWaitingList() {
        if (!waitingList.isEmpty()) {
            BusReservation nextReservation = waitingList.dequeue();
            reservations.add(nextReservation);
            System.out.println("Promoted from waiting List Customer Name: " + nextReservation.getCustomerName());
        }
    }

    @Override
    public String toString() {
        return 
                "Driver Id: " + DriverId + " | " +
                "Bus No: " + busNumber + " | " +
                "Total Seats: " + totalSeats + " | " +
                "startingPoint: " + startingPoint + " | " +
                "endingPoint: " + endingPoint + " | " +
                "startingTime: " + startingTime + " | " +
                "fare: " + fare + "."
                ;
    }

    public static void DummyData(){
        bus.reservations.add( new BusReservation("Vijay", "0767594154", LocalDateTime.now(), 1, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Santha Kumari", "0715485752", LocalDateTime.now(), 2, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Santha Kumari", "0715485752", LocalDateTime.now(), 3, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Lakshimi Theva", "0754848963", LocalDateTime.now(), 4, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Titus Fowler", "0715826425", LocalDateTime.now(), 5, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Indra", "0774568525", LocalDateTime.now(), 6, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Indra", "0774568525", LocalDateTime.now(), 7, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Indra", "0774568525", LocalDateTime.now(), 8, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Mohamed Syakirin", "0765896689", LocalDateTime.now(), 9, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Shalini Ratnasingam", "0773202058", LocalDateTime.now(), 10, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Shalini Ratnasingam", "0773202058", LocalDateTime.now(), 11, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("M. G. Mahathir", "0770404120", LocalDateTime.now(), 12, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Kumari", "0112588984", LocalDateTime.now(), 13, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("John Amran", "0750202458", LocalDateTime.now(), 14, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Titus Fowler", "0715826425", LocalDateTime.now(), 15, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Titus Fowler", "0715826425", LocalDateTime.now(), 16, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Samantha Berry", "0775548168", LocalDateTime.now(), 17, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Christian Mckinney", "0756895463", LocalDateTime.now(), 18, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Christian Mckinney", "0756895463", LocalDateTime.now(), 19, 1000, "ND-5553"));
        bus.reservations.add( new BusReservation("Christian Mckinney", "0756895463", LocalDateTime.now(), 20, 1000, "ND-5553"));

        
        BusReservation res1 = new BusReservation("Lakshimi Theva", "0754848963", LocalDateTime.now(), 0, 1000,"ND-5553");
        BusReservation res2 = new BusReservation("Samantha Berry", "0775548168", LocalDateTime.now(), 0, 1000,"ND-5553");
        BusReservation res3 = new BusReservation("John Amran", "0750202458", LocalDateTime.now(), 0, 1000,"ND-5553");
        BusReservation res4 = new BusReservation("Vijay", "0750202458", LocalDateTime.now(), 0, 1000,"ND-5553");
        BusReservation res5 = new BusReservation("asJohn Murugesond", "0718956586", LocalDateTime.now(), 0, 1000,"ND-5553");
        BusReservation res6 = new BusReservation("John Murugeson", "0718956586", LocalDateTime.now(), 0, 1000,"ND-5553");
        bus.addToWaitingList(res1);
        bus.addToWaitingList(res2);
        bus.addToWaitingList(res3);
        bus.addToWaitingList(res4);
        bus.addToWaitingList(res5);
        bus.addToWaitingList(res6);
    }

    public void addToWaitingList(BusReservation reservation) {
        waitingList.enqueue(reservation);
        //System.out.println("Added " + reservation.getCustomerName() + " to waiting list for seat " + reservation.getSeatNumber());
    }

    public void displayWaitingList() {
        waitingList.display();
    }
}