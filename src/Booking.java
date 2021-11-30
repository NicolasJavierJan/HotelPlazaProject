public class Booking {
    // Bookings (start date, end date, room number, Guest)
    private int startDateDay;
    private String startDateMonth;
    private int endDateDay;
    private String endDateMonth;
    private int numberOfNights;
    private Room room;

    public Booking(int startDateDay, String startDateMonth, int endDateDay, String endDateMonth, int numberOfNights) {
        this.startDateDay = startDateDay;
        this.startDateMonth = startDateMonth;
        this.endDateDay = endDateDay;
        this.endDateMonth = endDateMonth;
        this.numberOfNights = numberOfNights;
    }


    public int getStartDate() {
        return startDateDay;
    }

    public void setStartDate(int startDate) {
        this.startDateDay = startDate;
    }

    public int gtEndDate() {
        return endDateDay;
    }

    public void setEndDate(int endDate) {
        this.endDateDay = endDate;
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

    public int getStartDateDay() {
        return startDateDay;
    }

    public void setStartDateDay(int startDateDay) {
        this.startDateDay = startDateDay;
    }

    public String getStartDateMonth() {
        return startDateMonth;
    }

    public void setStartDateMonth(String startDateMonth) {
        this.startDateMonth = startDateMonth;
    }

    public int getEndDateDay() {
        return endDateDay;
    }

    public void setEndDateDay(int endDateDay) {
        this.endDateDay = endDateDay;
    }

    public String getEndDateMonth() {
        return endDateMonth;
    }

    public void setEndDateMonth(String endDateMonth) {
        this.endDateMonth = endDateMonth;
    }
}
