import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Booking implements Serializable {
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

        System.out.println("\n----- Create Booking -----" +
                "\n· 1. Create booking" +
                "\n· 2. Extend booking" +
                "\n· 3. Display bookings list" +
                "\n· 9. Go back");

        int answer = Main.userChoice();

        if (answer == 1) {
            Booking.createBooking();
        } else if (answer == 2) {
            Booking.editBooking();
        }else if (answer == 3){
            Main.displayBookingList();
        } else if (answer == 9) {
            Main.menu();
        }
    }

    public static void createBooking(){

        int startDay = 0;
        int startMonth = 0;
        int startYear = 0;
        int numberOfNights = 0;

        boolean enterDayLoop = true;
        while(enterDayLoop) {

            System.out.println("\n· Enter starting day:");
            startDay = Main.userChoice();
            if (startDay >= 1 && startDay <= 31) {
                enterDayLoop = false;
            } else {
                System.out.println("\n ! The day has to be between 1 and 31, try again !");
            }
        }

        boolean enterMonthLoop = true;
        while (enterMonthLoop) {

            //System.out.println("· Enter starting month");
            //int startMonth = validateStartMonth(Main.userChoice());
            //startMonth = Main.userChoice();

            if (startDay == 30){

                System.out.println("\nChoose one of the following months:" +
                        "\n· 4. April" + "\n· 6. June" + "\n· 9. September" + "\n· 11. November");

                startMonth = Main.userChoice();
                if (startMonth == 4 || startMonth == 6 || startMonth == 9 || startMonth == 11){
                    enterMonthLoop = false;
                } else {
                    System.out.println("\n ! Invalid answer, try again !");
                }
            } else if (startDay == 31){
                System.out.println("\nChoose one of the following months:" +
                        "\n· 1. January" + "\n· 3. March" + "\n· 5. May" + "\n· 7. July" +
                        "\n· 8. August" + "\n· 10. October" + "\n· 12. December");

                startMonth = Main.userChoice();

                if (startMonth == 1 || startMonth == 3 || startMonth == 5  || startMonth == 7 || startMonth == 8 || startMonth == 10 || startMonth == 12 ){
                    enterMonthLoop = false;
                } else {
                    System.out.println("\n ! Invalid answer, try again !");
                }

            } else {
                System.out.println("\nChoose one of the following months:" +
                        "\n· 1. January" + "\n· 2. February" + "\n· 3. March" + "\n· 4. April" + "\n· 5. May" + "\n· 6. June" +
                        "\n· 7. July" + "\n· 8. August" + "\n· 9. September " + "\n· 10. October" + "\n· 11. November" + "\n· 12. December");

                startMonth = Main.userChoice();

                if (startMonth == 1 || startMonth == 2 || startMonth == 3  || startMonth == 4 || startMonth == 5 || startMonth == 6 ||
                        startMonth == 7 || startMonth == 8 || startMonth == 9 || startMonth == 10 || startMonth == 11 || startMonth == 12){
                    enterMonthLoop = false;
                } else {
                    System.out.println("\n ! Invalid answer, try again !");
                }
            }
        }

        // startDate = validateStartDate(startDate, startMonth);

        boolean startYearLoop = true;
        while (startYearLoop) {
            System.out.println("\n· Enter year:");
            startYear = Main.userChoice();
            if (startYear <= 2030 && startYear > 2021) {
                startYearLoop = false;
            } else {
                System.out.println("\n ! Invalid answer. Impossible to enter a year previous to 2022 !");
            }
        }

        System.out.println("\n· Enter number of nights:");
        numberOfNights = Main.userChoice();
        if (numberOfNights < 1){
            System.out.println("\n ! Invalid answer. Please try again ! ");
        }

        LocalDate start = validateDate(startDay, startMonth, startYear);
        LocalDate end = start.plus(Period.ofDays(numberOfNights));
        Room room = checkAvailability(start, end);

        if (room == null){
            Main.menu();
        }
        Guest guest = guestChecking();
        if (guest == null){
            System.out.println("\n ! Something went wrong. Please try again ! ");
            Booking.menu();
        }

        Booking newBooking = new Booking(start, end, numberOfNights, room, guest);
        room.getBookings().add(newBooking);
        Main.bookings.add(newBooking);
        System.out.println("+---------------------------------------------+\n" +
                "|                                             |\n" +
                "|               HOTEL PLAZA                   |\n" +
                "|                                             |\n" +
                "|    " + room.getRoomName() + " Price per night: " + (room.getPricePerNight() * numberOfNights) + "dkk       |\n" +
                "|    TOTAL:                     " + room.getPricePerNight() * numberOfNights + "dkk        |\n" +
                "|                                             |\n" +
                "|                                             |\n" +
                "|                                             |\n" +
                "|               THANK YOU!                    |\n" +
                "+---------------------------------------------+");

        Booking.menu();
    }

    public static void editBooking(){

        System.out.println("\n· Choose Guest last name from the list:");
        for (Guest guest : Main.guests){
            System.out.println("[" + guest.lastName + "]");
        }
        String lastName = Main.userString();

        System.out.println("\n· Choose guest first name from the list:");
        for (Guest guest : Main.guests){
            System.out.println("[" + guest.firstName + "]");
        }
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
                System.out.println("\n-- Booking number " + counter + " --");
                booking.printInfo();
                counter++;
            }
            System.out.println("\n· Enter the booking number:");
            int bookingToAddDaysTo = Main.userChoice();
            System.out.println("\n· Enter number of days to add to the booking:");
            int daysToAdd = Main.userChoice();
            Booking extended = bookings.get(bookingToAddDaysTo - 1);
            boolean canExtend = checkOverlap(extended.startDate, extended.endDate.plus(Period.ofDays(daysToAdd)), extended.room);
            if (!canExtend){
                extended.endDate = extended.endDate.plus(Period.ofDays(daysToAdd));
                extended.printInfo();
            } else {
                System.out.println("\n ! This reservation cannot be extended !");
                Booking.menu();
            }

        } else {
            System.out.println("\n ! There's no booking under that name !");
            Booking.menu();
        }
        Booking.menu();
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
            System.out.println("\n ! There are no rooms in the system. Please add rooms and come back !");
            return null;
        }
        while (keepAsking){

            System.out.println("\nWhat type of room?" +
                    "\n· 1. Single bed" +
                    "\n· 2. Double bed" +
                    "\n· 3. Suite" +
                    "\n· 9. Exit");

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
                    System.out.println("\n ! There's no available Single Bed Rooms for that date !");
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
                    System.out.println("\n !There's no available Double Bed Rooms for that date !");
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
                    System.out.println("\n ! There's no available Suite Rooms for that date ! ");
                }
                } else if (answer == 9){
                    Booking.menu();
                } else {
                System.out.println("\n ! Please press a valid option !");
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
            System.out.println("\n ! There are no guests in the System. Please add Guests ! ");
            Main.menu();
        }
        System.out.println("\n· Choose guest lastname from the List:");
        Set<String> lastNames = new HashSet<>();
        for (Guest guest : Main.guests){
            lastNames.add(guest.lastName);
        }
        System.out.println(lastNames);
        String lastNameAnswer = Main.userString();
        System.out.println("\n· Choose guest firstname from the List:");
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
        return "- Room= " + this.room.getRoomName() +
                "\n- Room Number= " + this.room.getRoomNumber() +
                "\n- Floor= " + this.room.getFloor() +
                "\n- Guest= " + this.guest.firstName + " " + this.guest.lastName +
                "\n- Enter Date= " + this.startDate +
                "\n- Check out date= " + this.endDate;
    }
}
