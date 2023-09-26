package HotelBooking.Services;

import HotelBooking.models.Hotel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface HotelService {
    public Hotel addHotel(Hotel hotel);

    public List<Hotel> getHotels(String cityName, String hotelName);

    public Hotel getHotel(String hotelId);

    public void removeHotel(String hotelId);
}
