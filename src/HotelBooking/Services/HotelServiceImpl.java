package HotelBooking.Services;

import HotelBooking.models.Hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    static Map<String, Hotel> hotelMap;
    HotelServiceImpl() {
        if(hotelMap == null) {
            synchronized (this) {
                if(hotelMap == null) {
                    hotelMap = new HashMap<>();
                }
            }
        }
    }
    @Override
    public Hotel addHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        hotelMap.put(hotelId, hotel);
        return hotel;
    }

    @Override
    public List<Hotel> getHotels(String cityName, String hotelName) {
        List<Hotel>  hotelList = hotelMap.values().stream()
                .filter(hotel -> cityName!= null && hotel.getCity().equalsIgnoreCase(cityName))
                .collect(Collectors.toList());

        hotelList = hotelList.stream()
                .filter(hotel -> hotelName!= null && hotel.getHotelName().equalsIgnoreCase(hotelName))
                .collect(Collectors.toList());

        return hotelList;

    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelMap.get(hotelId);
    }

    @Override
    public void removeHotel(String hotelId) {

    }
}
