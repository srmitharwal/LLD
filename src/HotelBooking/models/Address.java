package HotelBooking.models;

public class Address {
    private long longitute;
    private long latitude;
    private String zipCode;

    private String city;

    private String landmark;


    public long getLongitute() {
        return longitute;
    }

    public void setLongitute(long longitute) {
        this.longitute = longitute;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
