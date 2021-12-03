import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Booking {
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfNights;
    private Room room;
    private Guest guest;

    public Booking(LocalDate start, LocalDate end, int numberOfNights, Room room, Guest guest) {
        this.numberOfNights = numberOfNights;
        this.startDate = start;
        this.endDate = end;
        this.room = room;
        this.guest = guest;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
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
            Booking.createBooking();
        } else if (answer == 2) {
            Booking.editBooking();
        } else if (answer == 9) {
            Main.menu();
        }
    }

    public static void createBooking(){
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
        if (room == null){
            Main.menu();
        }
        Guest guest = guestChecking();
        if (guest == null){
            System.out.println("Something went wrong. Please try again");
            Booking.menu();
        }
        Booking newBooking = new Booking(start, end, numberOfNights, room, guest);
        room.getBookings().add(newBooking);
        Main.bookings.add(newBooking);
        System.out.println(newBooking);
        Main.menu();
    }

    public static void editBooking(){
        System.out.println("Enter the Guest last name");
        String lastName = Main.userString();
        System.out.println("Enter the Guest first name");
        String firstName = Main.userString();
        ArrayList<Booking> bookings = new ArrayList<>();
        for (Booking booking : Main.bookings){
            if (booking.guest.firstName.equalsIgnoreCase(firstName) && booking.guest.lastName.equalsIgnoreCase(lastName)){
                bookings.add(booking);
            }
        }
        if (!bookings.isEmpty()){
            int counter = 1;
            for (Booking booking : bookings){
                System.out.println("Booking number " + counter);
                booking.printInfo();
                counter++;
            }
            System.out.println("Please enter the number of the booking to add days to:");
            int bookingToAddDaysTo = Main.userChoice();
            System.out.println("Please enter the days to add to the booking:");
            int daysToAdd = Main.userChoice();
            Booking extended = bookings.get(bookingToAddDaysTo - 1);
            boolean canExtend = checkOverlap(extended.startDate, extended.endDate.plus(Period.ofDays(daysToAdd)), extended.room);
            if (!canExtend){
                extended.endDate = extended.endDate.plus(Period.ofDays(daysToAdd));
                extended.printInfo();
            } else {
                System.out.println("This reservation cannot be extended.");
                Booking.menu();
            }

        } else {
            System.out.println("There's no booking under that name.");
            Booking.menu();
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
        if (Main.rooms.isEmpty()){
            System.out.println("There are no rooms in the system. Please add rooms and come back");
            return null;
        }
        while (keepAsking){
            System.out.println("What type of room?");
            System.out.println("Press 1 for Single Bed");
            System.out.println("Press 2 for Double Bed");
            System.out.println("Press 3 for Suite");
            System.out.println("Press 9 to Exit");
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
            } else if (answer == 3) {
                for (Room room : Main.rooms) {
                    if (room.getRoomName().equalsIgnoreCase("Suite")) {
                        overlap = checkOverlap(start, end, room);
                        if (!overlap) {
                            return room;
                        }
                    }
                }
                if (overlap) {
                    System.out.println("There's no available Suite Rooms for that date.");
                }
                } else if (answer == 9){
                    System.out.println("Going back to Menu");
                    Booking.menu();
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

    public static Guest guestChecking(){
        if (Main.guests.isEmpty()){
            System.out.println("There are no guests in the System. Please add Guests");
            Main.menu();
        }
        System.out.println("Please choose guest lastname from the List");
        Set<String> lastNames = new HashSet<>();
        for (Guest guest : Main.guests){
            lastNames.add(guest.lastName);
        }
        System.out.println(lastNames);
        String lastNameAnswer = Main.userString();
        System.out.println("Please choose guest firstname from the List");
        Set<String> firstNames = new HashSet<>();
        for (Guest guest: Main.guests){
            if (guest.lastName.equalsIgnoreCase(lastNameAnswer)){
                firstNames.add(guest.firstName);
            }
        }
        System.out.println(firstNames);
        String firstNameAnswer = Main.userString();
        for (Guest guest : Main.guests){
            if (guest.lastName.equalsIgnoreCase(lastNameAnswer) && guest.firstName.equalsIgnoreCase(firstNameAnswer)){
                return guest;
            }
        }
        return null;
    }

    public void printInfo(){
        System.out.println(this.guest);
        System.out.println(this.room);
        System.out.println(this.startDate + " to " + this.endDate);
    }

    @Override
    public String toString(){
        return "+-------------------------------------+\n" +
                "|                                     |\n" +
                "|             HOTEL PLAZA             |\n" +
                "|                                     |\n" +
                "|  " + this.room.getRoomName() + " Price per night: " + this.room.getPricePerNight() + "  |\n" +
                "|  TOTAL                     " + this.room.getPricePerNight() * this.numberOfNights + "    |\n" +
                "|                                     |\n" +
                "|                                     |\n" +
                "|                                     |\n" +
                "|             THANK YOU!              |\n" +
                "+-------------------------------------+";
    }
}
