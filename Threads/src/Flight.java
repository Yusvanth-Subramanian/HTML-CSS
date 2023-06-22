public class Flight {
    private int noOfTickets;

    private boolean isSeatAlloted[];


    public Flight(){
        noOfTickets=2;
        isSeatAlloted=new boolean[3];
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public boolean[] getIsSeatAlloted() {
        return isSeatAlloted;
    }

    public void setIsSeatAlloted(boolean[] isSeatAlloted) {
        this.isSeatAlloted = isSeatAlloted;
    }
}
