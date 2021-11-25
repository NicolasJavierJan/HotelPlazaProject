public class Room {
    // Room (change price, booking)
    private int roomNumber;
    private int floor;
    private String roomName;
    private boolean withInternet;
    private double pricePerNight;
    private int numberOfBeds;

    public Room(int roomNumber, int floor, String roomName, boolean withInternet, double pricePerNight, int numberOfBeds) {
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

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }
}
