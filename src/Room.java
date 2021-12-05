import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
    // TODO
    // Check if room flor and room number are already taken

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

    public static void menu(){
        boolean keepAsking = true;

        while (keepAsking) {

            System.out.println("\n----- Room options -----" +
                    "\n· 1. Create room" +
                    "\n· 2. Change room price" +
                    "\n· 9. Go back" +
                    "\n" + Main.rooms);

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
                System.out.println("\n ! Please choose a number from the list !");
            }
        }
    }

    public static void createRoom(){

        String roomName = "";
        int roomType = 0;
        int roomPrice = 0;
        int roomNumber = 0;
        int roomBeds = 0;
        int roomFloor = 0;

        System.out.println("\nChoose room type: " +
                "\n· 1. Single bed (starting from 300dkk)" +
                "\n· 2. Double bed (starting from 700dkk)" +
                "\n· 3. Suite (starting from 1000dkk)");
        roomType = Main.userChoice();

        switch (roomType){
            case 1:
                roomName = "Single bed";
                roomPrice = 300;
                break;

            case 2:
                roomName = "Double bed";
                roomPrice = 700;
                break;

            case 3:
                roomName = "Suite";
                roomPrice = 1000;
                break;
        }

        boolean enterFloorLoop = true;
        while (enterFloorLoop) {

            System.out.println("\n· Enter Room Floor (1-5):");
            roomFloor = Main.userChoice();

            if (roomFloor >= 1 && roomFloor <= 5) {
                enterFloorLoop = false;
            } else {
                System.out.println("\n ! The hotel has only five floors. Try again !");
            }
        }

        boolean roomNumberLoop = true;
        while (roomNumberLoop) {

            switch (roomFloor) {
                case 1:
                    System.out.println("\nThe first floor has 20 rooms." +
                            "\n· Enter room number (e.g 107)");
                    roomNumber = Main.userChoice();
                    if (roomNumber >= 100 && roomNumber <= 120) {
                        roomNumberLoop = false;
                    } else {
                        System.out.println("\n ! Room number has to be between 100 and 120 ! ");
                    }
                    break;

                case 2:
                    System.out.println("\nThe second floor has 10 rooms." +
                            "\n· Enter room number (e.g 207)");
                    roomNumber = Main.userChoice();
                    if (roomNumber >= 200 && roomNumber <= 210) {
                        roomNumberLoop = false;
                    } else {
                        System.out.println("\n ! Room number has to be between 200 and 210 ! ");
                    }
                    break;

                case 3:
                    System.out.println("\nThe third floor has 15 rooms." +
                            "\n· Enter room number (e.g 307)");
                    roomNumber = Main.userChoice();
                    if (roomNumber >= 300 && roomNumber <= 315) {
                        roomNumberLoop = false;
                    } else {
                        System.out.println("\n ! Room number has to be between 300 and 315 ! ");
                    }
                    break;

                case 4:
                    System.out.println("\nThe fourth floor has 10 rooms." +
                            "\n· Enter room number (e.g 407)");
                    roomNumber = Main.userChoice();
                    if (roomNumber >= 400 && roomNumber <= 410) {
                        roomNumberLoop = false;
                    } else {
                        System.out.println("\n ! Room number has to be between 400 and 410 ! ");
                    }
                    break;

                case 5:
                    System.out.println("\nThe fifth floor has 15 rooms." +
                            "\n· Enter room number (e.g 507)");
                    roomNumber = Main.userChoice();
                    if (roomNumber >= 500 && roomNumber <= 515) {
                        roomNumberLoop = false;
                    } else {
                        System.out.println("\n ! Room number has to be between 500 and 515 ! ");
                    }
                    break;
            }
            for (Room room : Main.rooms){
                if (roomNumber == room.roomNumber){
                    System.out.println("\n ! Room already taken, try again !");
                    roomNumberLoop = true;
                } else {
                    roomNumberLoop = false;
                }
            }
        }//roomNumberLoop closed

        System.out.println("\nDo you want to include Wi-fi (extra 50dkk per night)?");
        boolean roomInternet = Main.userBoolean();

        boolean bedsLoop = true;
        while (bedsLoop) {

            if (roomName.equalsIgnoreCase("suite")) {
                System.out.println("\n· How many beds in the room (max 6, 150 dkk each)?");
                roomBeds = Main.userChoice();
                if (roomBeds >= 2 && roomBeds <= 6 && roomInternet) {
                    bedsLoop = false;
                    roomPrice = roomPrice + (roomBeds * 150) + 50;
                }
                if (roomBeds >= 2 && roomBeds <= 6 && !roomInternet) {
                    bedsLoop = false;
                    roomPrice = roomPrice + (roomBeds * 150);
                }
                if (roomBeds < 2 || roomBeds > 6) {
                    System.out.println("\n ! Number of beds has to be between 2 and 6 !");
                }

            } else if (roomName.equalsIgnoreCase("single bed")) {
                bedsLoop = false;
                roomBeds = 1;
                if (roomInternet){
                    roomPrice = roomPrice + 50;
                }

            } else if (roomName.equalsIgnoreCase("double bed")){
                bedsLoop = false;
                roomBeds = 2;
                if (roomInternet){
                    roomPrice = roomPrice + 50;
                }
            }
        } // bedsLoop closed

        Room newRoom = new Room(roomNumber, roomFloor, roomName, roomInternet, roomPrice, roomBeds);
        Main.rooms.add(newRoom);
        System.out.println("\nYou have correctly created a new room:" +
               newRoom );

        Room.menu();
    }

    public static void changePriceMenu(){
        System.out.println("\nRooms: ");
        for (Room room : Main.rooms){
            System.out.println("-" + room.roomNumber);
        }

        System.out.println("\n· 9. Go back" +
                "\n· Enter room number:");

        int choice = Main.userChoice();

        if (choice == 9) {
            menu();
        }

        boolean found = false;
        for (Room room : Main.rooms){
            if (choice == room.roomNumber){

                found = true;
                System.out.println("\nRoom current price: " + room.pricePerNight + "dkk" +
                        "\n· Enter new price:");
                int newPrice = Main.userChoice();

                room.setPricePerNight(newPrice);
                System.out.println("\nPrice correctly changed !" +
                        "\n- Room " + room.getRoomNumber() + ": " + room.getPricePerNight() + "dkk per night");
                changePriceMenu();
            }
        }

        if (!found){
            System.out.println("\n! Room not found, try again !");
            changePriceMenu();
        }
    }

    public static void changePrice(String typeOfRoom, int newPrice){
        for (Room room : Main.rooms){
            if (room.getRoomName().equalsIgnoreCase(typeOfRoom)){
                room.setPricePerNight(newPrice);
            }
        }
    }

    @Override
    public String toString(){
        String internet = "";
        if (withInternet){
            internet = "yes";
        } else {
            internet = "no";
        }
        return  "\n- Room type = " + roomName +
                "\n- Room Number = " + roomNumber +
                "\n- Floor = " + floor +
                "\n- Wi-fi = " + internet +
                "\n- Number of beds = " + numberOfBeds +
                "\n- Price per Night = " + pricePerNight + "dkk per night\n";
    }
}
