import java.time.LocalDate;
import java.time.Period;

public class Booking {
    // Add GUEST
    // TODO
    // Bookings (start date, end date, room number, Guest)
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfNights;
    private Room room;

    public Booking(LocalDate start, LocalDate end, int numberOfNights, Room room) {
        this.numberOfNights = numberOfNights;
        this.startDate = start;
        this.endDate = end;
        this.room = room;
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

    public static void menu() {
        System.out.println("Press 1 to create a new Booking");
        System.out.println("Press 2 to extend a Booking");
        System.out.println("Press 9 to Exit");

        int answer = Main.userChoice();

        if (answer == 1) {
            System.out.println("Enter the Starting day");
            int startDate = Main.userChoice();
            System.out.println("Enter the Starting month");
            int startMonth = validateStartMonth(Main.userChoice());
            startDate = validateStartDate(startDate, startMonth);
            System.out.println("Enter the Starting year");
            int startYear = Main.userChoice();
            System.out.println("Enter the number of nights for the stay");
            int numberOfNights = Main.userChoice();
            LocalDate start = validateDate(startDate, startMonth, startYear);
            LocalDate end = start.plus(Period.ofDays(numberOfNights));
            Room room = checkAvailability(start, end);
            Booking newBooking = new Booking(start, end, numberOfNights, room);
            room.getBookings().add(newBooking);
            System.out.println(newBooking);
            Main.menu();

        } else if (answer == 2) {

        } else if (answer == 9) {

        }
    }

    public static int validateStartDate(int startDate, int startMonth) {
        if (startDate < 1) {
            return 1;
        }
        if (startMonth == 1 || startMonth == 3 || startMonth == 5 || startMonth == 7 || startMonth == 8 || startMonth == 10 || startMonth == 12) {
            if (startDate > 31) {
                return 31;
            } else {
                return startDate;
            }
        } else if(startMonth ==4||startMonth ==6||startMonth ==9||startMonth ==11){
            if (startDate > 30) {
                return 30;
            } else {
                return startDate;
            }
    } else if(startMonth ==2) {
            if (startDate > 28) {
                return 28;
            } else {
                return startDate;
            }
        }
        return 0;
    }

    public static int validateStartMonth(int startMonth){
        if (startMonth < 0){
            return 1;
        } else if (startMonth > 12){
            return 12;
        }
        return startMonth;
    }

    public static LocalDate validateDate(int startDay, int startMonth, int startYear){
        String startDayString = "";
        String startMonthString = "";
        // Typecasting for Day
        if (startDay < 10){
            startDayString = "0" + Integer.toString(startDay);
        } else {
            startDayString = Integer.toString(startDay);
        }
        // Typecasting for Month
        if (startMonth < 10) {
            startMonthString = "0" + Integer.toString(startMonth);
        } else {
            startMonthString = Integer.toString(startMonth);
        }
        return LocalDate.parse(startYear + "-" + startMonthString + "-" + startDayString);
    }

    public static Room checkAvailability(LocalDate start, LocalDate end){
        boolean keepAsking = true;
        while (keepAsking){
            System.out.println("What type of room?");
            System.out.println("Press 1 for Single Bed");
            System.out.println("Press 2 for Double Bed");
            System.out.println("Press 3 for Suite");
            int answer = Main.userChoice();
            boolean overlap = true;
            if (answer == 1){
                for (Room room : Main.rooms){
                    if (room.getRoomName().equalsIgnoreCase("Single bed")){
                        overlap = checkOverlap(start, end, room);
                        if (!overlap){
                            return room;
                        }
                    }
                }
                if (overlap){
                    System.out.println("There's no available Single Bed Rooms for that date.");
                }
            } else if (answer == 2){
                for (Room room : Main.rooms){
                    if (room.getRoomName().equalsIgnoreCase("Double bed")){
                        overlap = checkOverlap(start, end, room);
                        if (!overlap){
                            return room;
                        }
                    }
                }
                if (overlap){
                    System.out.println("There's no available Double Bed Rooms for that date.");
                }
            } else if (answer == 3){
                for (Room room : Main.rooms){
                    if (room.getRoomName().equalsIgnoreCase("Suite")){
                        overlap = checkOverlap(start, end, room);
                        if (!overlap){
                            return room;
                        }
                    }
                }
                if (overlap){
                    System.out.println("There's no available Suite Rooms for that date.");
                }
                } else {
                System.out.println("Please press a valid option");
            }
        }
        return null;
    }

    public static boolean checkOverlap(LocalDate startDate, LocalDate endDate, Room room){
        for (Booking booking : room.getBookings()){
            if (startDate.isAfter(booking.startDate) && startDate.isBefore(booking.endDate)){
                return true;
            } else if (endDate.isAfter(booking.startDate) && endDate.isBefore(booking.endDate)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return this.startDate + " to " + this.endDate;
    }
}
