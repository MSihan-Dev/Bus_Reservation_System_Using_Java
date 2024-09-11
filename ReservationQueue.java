public class ReservationQueue {
    private QNode front, rear;
    public ReservationQueue(){
        this.front = this.rear = null;
    }

    public void enqueue(BusReservation reservation) {
        QNode newNode = new QNode(reservation);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }
    public BusReservation dequeue(){
        if (isEmpty()) {
            System.out.println("Waiting list is Empty");
            return null;
        }
        BusReservation removevedReservation = front.getResevation();
        front = front.getNext();
        if(isEmpty()){
            rear = null;
        }
        System.out.println("Removed from waiting list: " + removevedReservation.getCustomerName());
        return removevedReservation;
    }

    public void display(){
        if (isEmpty()) {
            System.out.println("Waiting list is Empty");
            return;
        }
        System.out.print("Waiting list :  ");
        QNode current = front;
        while (current != null) {
            System.out.print("\nCustomer Name: "+current.getResevation().getCustomerName() +
                                 " - " + "For Bus No: " + current.getResevation().getBusNO() + " | ");
            current = current.getNext();
        }
        System.out.println();
    }

    public boolean isEmpty(){
        return front == null;
    }

    public QNode getFront(){
        return front;
    }
}




