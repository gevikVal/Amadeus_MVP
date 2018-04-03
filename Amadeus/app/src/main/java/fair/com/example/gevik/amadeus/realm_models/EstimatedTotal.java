
package fair.com.example.gevik.amadeus.realm_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class EstimatedTotal extends RealmObject implements Parcelable
{

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    public final static Creator<EstimatedTotal> CREATOR = new Creator<EstimatedTotal>() {


        @SuppressWarnings({
            "unchecked"
        })
        public EstimatedTotal createFromParcel(Parcel in) {
            return new EstimatedTotal(in);
        }

        public EstimatedTotal[] newArray(int size) {
            return (new EstimatedTotal[size]);
        }

    }
    ;

    protected EstimatedTotal(Parcel in) {
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.currency = ((String) in.readValue((String.class.getClassLoader())));
    }

    public EstimatedTotal() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(amount);
        dest.writeValue(currency);
    }

    public int describeContents() {
        return  0;
    }

}
