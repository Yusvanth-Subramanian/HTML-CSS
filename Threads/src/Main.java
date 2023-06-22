
public class Main {
    public static void main(String[] args) {
        Flight flight = new Flight();
        FlightBooking flightBooking = new FlightBooking();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                flightBooking.bookTicket(flight,1);
            }
        },"Thread 1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                flightBooking.bookTicket(flight,2);
            }
        },"Thread 2");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                flightBooking.bookTicket(flight,2);
            }
        },"Thread 3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}