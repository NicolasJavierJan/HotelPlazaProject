public class Booking {
    // Bookings (start date, end date, room number, Guest)
    private int startDate;
    private int endDate;
    private int numberOfNights;
    private Room room;

    public Booking(int startDate, int endDate, int numberOfNights) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfNights = numberOfNights;
    }


    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
