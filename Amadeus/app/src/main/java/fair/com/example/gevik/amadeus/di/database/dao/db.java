package fair.com.example.gevik.amadeus.di.database.dao;

import fair.com.example.gevik.amadeus.realm_models.Result;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by gevik on 3/5/2018.
 */

public class db {
    private void test() {
        Realm realm = Realm.getDefaultInstance();
        ResultResponse resultResponses = realm.where(ResultResponse.class).findFirst();
        RealmList<Result> realmList = resultResponses.getResulList();
        for (Result result : realmList) {


        }

    }
}

