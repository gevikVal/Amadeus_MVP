
package fair.com.example.gevik.amadeus.realm_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class VehicleInfo extends RealmObject implements Parcelable
{

    @SerializedName("acriss_code")
    @Expose
    private String acrissCode="";
    @SerializedName("transmission")
    @Expose
    private String transmission = "";
    @SerializedName("fuel")
    @Expose
    private String fuel = "";
    @SerializedName("air_conditioning")
    @Expose
    private Boolean airConditioning;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("type")
    @Expose
    private String type;
    public final static Creator<VehicleInfo> CREATOR = new Creator<VehicleInfo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public VehicleInfo createFromParcel(Parcel in) {
            return new VehicleInfo(in);
        }

        public VehicleInfo[] newArray(int size) {
            return (new VehicleInfo[size]);
        }

    }
    ;

    protected VehicleInfo(Parcel in) {
        this.acrissCode = ((String) in.readValue((String.class.getClassLoader())));
        this.transmission = ((String) in.readValue((String.class.getClassLoader())));
        this.fuel = ((String) in.readValue((String.class.getClassLoader())));
        this.airConditioning = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VehicleInfo() {
    }

    public String getAcrissCode() {
        return acrissCode;
    }

    public void setAcrissCode(String acrissCode) {
        this.acrissCode = acrissCode;
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

    public Boolean getAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(Boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(acrissCode);
        dest.writeValue(transmission);
        dest.writeValue(fuel);
        dest.writeValue(airConditioning);
        dest.writeValue(category);
        dest.writeValue(type);
    }

    public int describeContents() {
        return  0;
    }

}
