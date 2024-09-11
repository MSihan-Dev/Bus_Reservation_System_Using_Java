import java.time.LocalDateTime;

public abstract class Notifications {

    public abstract void sendNotification(String cusname, int seats, int ID, LocalDateTime time);

    public void requestSuccess(String cusname, int seats, int ID, LocalDateTime time) {
        sendNotification(cusname, seats, ID, time);
    }
}

















