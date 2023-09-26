package HotelBooking.Services;

import HotelBooking.models.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    public Room addRoom(Room room);

    public List<Room> getRooms(String hotelId, LocalDate startDate, LocalDate endDate);

    public Room getRoom(String roomId);

    public void removeRoom(String roomId);
}
