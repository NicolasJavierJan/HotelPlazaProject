import java.util.ArrayList;

public class Room {
    // Room (change price, booking)
    private int roomNumber;
    private int floor;
    private String roomName;
    private boolean withInternet;
    private int pricePerNight;
    private int numberOfBeds;
    private ArrayList<Booking> bookings = new ArrayList<>();

    public Room(int roomNumber, int floor, String roomName, boolean withInternet, int pricePerNight, int numberOfBeds) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.roomName = roomName;
        this.withInternet = withInternet;
        this.pricePerNight = pricePerNight;
        this.numberOfBeds = numberOfBeds;
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isWithInternet() {
        return withInternet;
    }

    public void setWithInternet(boolean withInternet) {
        this.withInternet = withInternet;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString(){
        return this.getRoomName() + " " + this.pricePerNight;
    }

    public static void menu(){
        // Testing purposes only
        System.out.println(Main.rooms);

        boolean keepAsking = true;

        while (keepAsking) {

            System.out.println("Press 1 to create a Room");
            System.out.println("Press 2 to change the price of the rooms");
            System.out.println("Press 9 to exit");

            int answer = Main.userChoice();

            if (answer == 1) {
                keepAsking = false;
                Room.createRoom();
            } else if (answer == 2) {
                keepAsking = false;
                Room.changePriceMenu();
            } else if (answer == 9) {
                keepAsking = false;
                Main.menu();
            } else {
                System.out.println("Please choose a number from the list");
            }
        }
    }

    public static void createRoom(){
        // TODO
        // Maybe change this so it's chosen by number? Less problems and typing that way.
        System.out.println("Enter Room Name (single bed, double bed or suite)");
        String roomName = Main.userString();

        System.out.println("Enter Room Floor");
        int roomFloor = Main.userChoice();

        System.out.println("Enter Room Number");
        int roomNumber = Main.userChoice();

        System.out.println("Does the room have internet?");
        boolean roomInternet = Main.userBoolean();

        int roomBeds = 0;
        if (roomName.equalsIgnoreCase("suite")) {
            System.out.println("How many beds are in the room?");
            roomBeds = Main.userChoice();
        } else if (roomName.equalsIgnoreCase("single Bed")){
            roomBeds = 1;
        } else {
            roomBeds = 2;
        }

        System.out.println("How much does the room cost per night?");
        int roomPrice = Main.userChoice();

        Room newRoom = new Room(roomNumber, roomFloor, roomName, roomInternet, roomPrice, roomBeds);
        Main.rooms.add(newRoom);

        Room.menu();
    }

    public static void changePriceMenu(){
        System.out.println("Which type of room do you want to change the price to?");
        System.out.println("Press 1 for Single bed rooms");
        System.out.println("Press 2 for Double bed rooms");
        System.out.println("Press 3 for Suites");
        System.out.println("Press 9 to exit");

        boolean keepAsking = true;
        if (keepAsking) {
            int answer = Main.userChoice();

            if (answer > 0 && answer <= 3){
                System.out.println("Please enter the new price");
                int newPrice = Main.userChoice();
                if (answer == 1){
                    Room.changePrice("Single Bed", newPrice);
                    Room.menu();
                } else if (answer == 2){
                    Room.changePrice("Double Bed", newPrice);
                    Room.menu();
                } else {
                    Room.changePrice("Suite", newPrice);
                    Room.menu();
                }
            } else if (answer == 9) {
                Room.menu();
            } else {
                System.out.println("Please write a number from the list");
            }
        }
    }

    public static void changePrice(String typeOfRoom, int newPrice){
        for (Room room : Main.rooms){
            if (room.getRoomName().equalsIgnoreCase(typeOfRoom)){
                room.setPricePerNight(newPrice);
            }
        }
    }

}
