import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BusReservation {
    private static int idCounter = 1501;
    private int BusReservationId;
    private String customerName;
    private String PhonNo;
    private String BusNO;
    private LocalDateTime recervationTime;
    private int seatNumber;
    private float Cost;

    
    private Database database = new Database();
    public Database getDatabase() {
        return database;
    }
     
    public BusReservation(String customerName,String phoneNo, LocalDateTime recervationTime, int seatNumber, float cost, String busNo){
        this.BusReservationId = getNextId();
        this.customerName = customerName;
        this.PhonNo = phoneNo;
        this.recervationTime = recervationTime;
        this.seatNumber = seatNumber;
        this.Cost = cost;
        this.BusNO = busNo;
    }
    
    public int getBusReservationId() {
        return BusReservationId;
    }

    public void setBusReservationId(int busReservationId) {
        BusReservationId = busReservationId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhonNo() {
        return PhonNo;
    }

    public LocalDateTime getRecervationTime() {
        return recervationTime;
    }

    public void setRecervationTime(LocalDateTime recervationTime) {
        this.recervationTime = recervationTime;
    }
    
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public float getCost() {
        return Cost;
    }

    public void setCost(float cost) {
        Cost = cost;
    }

    public String getBusNO() {
        return BusNO;
    }

    public void setBusNO(String busNO) {
        BusNO = busNO;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = recervationTime.format(formatter);
        return
                "Reservation Id: " + BusReservationId + " | " +
                "Customer Name: " + customerName + " | " +
                "Customer Phone No: " + PhonNo + " | " +
                "Reservation Time: " + formattedTime + " | " +
                "Seat Number: " + seatNumber + " | " +
                "Cost: "+ Cost + " | " +
                "Bus Number: " + BusNO + ".";
    }

    private static synchronized int getNextId() {
        if (idCounter >= 9999) {
            idCounter = 1501; 
        }
        return ++idCounter;
    }
}
