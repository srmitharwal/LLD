package HotelBooking.models;

import java.time.LocalDate;
import java.util.Map;

public class Room {
    private String roomId;

    private RoomType roomtype;

    private String hotelId;

    private int totalCount;

    private int minPrice;

    private int maxPrice;

    private Map<LocalDate,Integer>availableMap;

   // List<Facilities> facilitiesList;


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(RoomType roomtype) {
        this.roomtype = roomtype;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Map<LocalDate, Integer> getAvailableMap() {
        return availableMap;
    }

    public void setAvailableMap(Map<LocalDate, Integer> availableMap) {
        this.availableMap = availableMap;
    }
}
