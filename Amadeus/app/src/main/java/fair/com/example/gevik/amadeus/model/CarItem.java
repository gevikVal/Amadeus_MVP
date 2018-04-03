package fair.com.example.gevik.amadeus.model;

/**
 * Created by gevik on 3/6/2018.
 */

public class CarItem {
    String id;
    String comapnyName;
    String lat;
    double latitude;
    double longitude;
    double distance;
    String address;
    String transmission;
    String fuel;
    boolean ariconditioner;
    String type;
    String price;
    String currency;

    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public boolean isAriconditioner() {
        return ariconditioner;
    }

    public void setAriconditioner(boolean ariconditioner) {
        this.ariconditioner = ariconditioner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProviderName() {
        return comapnyName;
    }

    public void setProviderName(String providerName) {
        this.comapnyName = providerName;
    }
}
