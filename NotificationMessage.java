import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class NotificationMessage extends Notifications {

    @Override
    public void sendNotification(String cusname, int seats, int ID, LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(formatter);
        System.out.println("-----------------------------------");
        System.out.println("Your Seat Reservation Success");
        System.out.println("-----------------------------------");
        System.out.println("Hi! " + cusname);
        System.out.println("You Booked " + seats + " seats");
        System.out.println("You Traveling Time: " + formattedTime);
        System.out.println("You Reservation Id: " + ID);
        System.out.println("-----------------------------------");
    }
}


