
package fair.com.example.gevik.amadeus.realm_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Result extends RealmObject implements Parcelable
{

    @SerializedName("provider")
    @Expose
    private Provider provider;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("cars")
    @Expose
    private RealmList<Car> cars = null;
    @SerializedName("airport")
    @Expose
    private String airport;
    public final static Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    protected Result(Parcel in) {
        this.provider = ((Provider) in.readValue((Provider.class.getClassLoader())));
        this.location = ((Location) in.readValue((Location.class.getClassLoader())));
        this.address = ((Address) in.readValue((Address.class.getClassLoader())));
        in.readList(this.cars, (Car.class.getClassLoader()));
        this.airport = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Result() {
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RealmList<Car> getCars() {
        return cars;
    }

    public void setCars(RealmList<Car> cars) {
        this.cars = cars;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(provider);
        dest.writeValue(location);
        dest.writeValue(address);
        dest.writeList(cars);
        dest.writeValue(airport);
    }

    public int describeContents() {
        return  0;
    }

}
