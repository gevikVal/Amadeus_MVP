
package fair.com.example.gevik.amadeus.realm_models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Car extends RealmObject implements Parcelable
{

    @PrimaryKey
    String id;
    @SerializedName("vehicle_info")
    @Expose
    private VehicleInfo vehicleInfo;
    @SerializedName("rates")
    @Expose
    private RealmList<Rate> rates = null;
    @SerializedName("estimated_total")
    @Expose
    private EstimatedTotal estimatedTotal;
    public final static Creator<Car> CREATOR = new Creator<Car>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        public Car[] newArray(int size) {
            return (new Car[size]);
        }

    }
    ;

    protected Car(Parcel in) {
        this.vehicleInfo = ((VehicleInfo) in.readValue((VehicleInfo.class.getClassLoader())));
        in.readList(this.rates, (Rate.class.getClassLoader()));
        this.estimatedTotal = ((EstimatedTotal) in.readValue((EstimatedTotal.class.getClassLoader())));
    }

    public Car() {
        id = java.util.UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(RealmList<Rate> rates) {
        this.rates = rates;
    }

    public EstimatedTotal getEstimatedTotal() {
        return estimatedTotal;
    }

    public void setEstimatedTotal(EstimatedTotal estimatedTotal) {
        this.estimatedTotal = estimatedTotal;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(vehicleInfo);
        dest.writeList(rates);
        dest.writeValue(estimatedTotal);
    }

    public int describeContents() {
        return  0;
    }
    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", vehicleInfo=" + vehicleInfo +
                ", rates=" + rates +
                ", estimatedTotal=" + estimatedTotal +
                '}';
    }

}
