
package fair.com.example.gevik.amadeus.realm_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Provider extends RealmObject implements Parcelable {

    @SerializedName("company_code")
    @Expose
    private String companyCode;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    public final static Creator<Provider> CREATOR = new Creator<Provider>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Provider createFromParcel(Parcel in) {
            return new Provider(in);
        }

        public Provider[] newArray(int size) {
            return (new Provider[size]);
        }

    };

    protected Provider(Parcel in) {
        this.companyCode = ((String) in.readValue((String.class.getClassLoader())));
        this.companyName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Provider() {
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        if (companyName != null) {
            return companyName;

        } else {
            return companyName;
        }
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(companyCode);
        dest.writeValue(companyName);
    }

    public int describeContents() {
        return 0;
    }

}
