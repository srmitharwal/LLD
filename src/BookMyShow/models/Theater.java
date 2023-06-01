package BookMyShow.models;

import java.util.ArrayList;
import java.util.List;


public class Theater {
    private String id;
    private String name;
    private String cityName;
    private Address address;
    private List<Screen> screenList;

    public Theater(String name, String cityName) {
        this.name = name;
        this.cityName = cityName;
        this.screenList = new ArrayList<>();
    }

    public Theater (String id, String name, String cityName, Address address, List<Screen> screenList) {
        this.id = id;
        this.name = name;
        this.cityName = cityName;
        this.address = address;
        this.screenList = screenList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Screen> getscreenList() {
        return screenList;
    }

    public void setscreenList(List<Screen> screenList) {
        this.screenList = screenList;
    }
}
