
package fair.com.example.gevik.amadeus.realm_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Rate extends RealmObject implements Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("price")
    @Expose
    private Price price;
    public final static Creator<Rate> CREATOR = new Creator<Rate>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Rate createFromParcel(Parcel in) {
            return new Rate(in);
        }

        public Rate[] newArray(int size) {
            return (new Rate[size]);
        }

    }
    ;

    protected Rate(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((Price) in.readValue((Price.class.getClassLoader())));
    }

    public Rate() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(price);
    }

    public int describeContents() {
        return  0;
    }

}
