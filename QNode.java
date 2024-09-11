public class QNode {
    
    private BusReservation resevation;
    private QNode next;

    public QNode(BusReservation resevation){
        this.resevation = resevation;
        this.next = null;
    }

    public QNode getNext() {
        return next;
    }

    public void setNext(QNode next) {
        this.next = next;
    }

    public BusReservation getResevation() {
        return resevation;
    }

    public void setResevation(BusReservation resevation) {
        this.resevation = resevation;
    }
}





