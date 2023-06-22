public class FlightBooking {

    public synchronized void bookTicket(Flight flight,int seatNumber){
        if(flight.getNoOfTickets()>0&&!flight.getIsSeatAlloted()[seatNumber]){
            System.out.println(Thread.currentThread().getName()+" : Ticket Booked Successfully!!!");
            flight.setNoOfTickets(flight.getNoOfTickets()-1);
            flight.getIsSeatAlloted()[seatNumber]=true;
        }
        else if(flight.getNoOfTickets()<=0)
            System.out.println("For "+Thread.currentThread().getName()+" No seats are available for booking.");
        else
            System.out.println("For "+Thread.currentThread().getName()+" Try booking different seat.");
    }
}
