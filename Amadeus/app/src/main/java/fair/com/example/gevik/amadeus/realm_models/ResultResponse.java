package fair.com.example.gevik.amadeus.realm_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by gevik on 2/28/2018.
 */

public class ResultResponse extends RealmObject {
    @PrimaryKey
    String id;

    @SerializedName("results")
    @Expose
    private RealmList<Result> resultList;

    public RealmList<Result> getResulList() {
        return resultList;
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "resultList=" + resultList +
                '}';
    }
}
