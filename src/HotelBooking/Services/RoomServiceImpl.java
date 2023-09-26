package HotelBooking.Services;

import HotelBooking.models.Room;

import java.time.LocalDate;
import java.util.*;

public class RoomServiceImpl implements RoomService {
    static Map<String,Room> roomMap;
    static Map<String, List<Room> >hotelRoomMap;
    RoomServiceImpl () {
        if(roomMap == null) {
            synchronized (this) {
                if (roomMap == null) {
                    roomMap = new HashMap<>();
                    hotelRoomMap = new HashMap<>();
                }
            }
        }
    }
    @Override
    public Room addRoom(Room room) {
        String roomId = UUID.randomUUID().toString();
        room.setRoomId(roomId);

        roomMap.put(roomId, room);
        List<Room> rooms = hotelRoomMap.getOrDefault(room.getHotelId(), new ArrayList<>());
        rooms.add(room);
        hotelRoomMap.put(room.getHotelId(), rooms);
        return room;

    }

    @Override
    public List<Room> getRooms(String hotelId, LocalDate startDate, LocalDate endDate) {
        List<Room> rooms = hotelRoomMap.get(hotelId);

        while(startDate.compareTo(endDate) < 0) {

        }
        return rooms;
    }

    @Override
    public Room getRoom(String roomId) {
        return null;
    }

    @Override
    public void removeRoom(String roomId) {

    }
}
