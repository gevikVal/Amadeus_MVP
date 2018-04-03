package fair.com.example.gevik.amadeus.di.database.dao;

import fair.com.example.gevik.amadeus.di.database.dao.util.RealmLiveData;
import fair.com.example.gevik.amadeus.realm_models.Result;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import android.arch.lifecycle.LiveData;

/**
 * Created by gevik on 2/28/2018.
 */

public class ResultDao extends Dao<ResultResponse>  {
    Realm db = Realm.getDefaultInstance();
    public ResultDao(Realm db) {
        super(db);
    }
 public LiveData<ResultResponse> findAllAync(){
     return new RealmLiveData<>(
             db.where(ResultResponse.class)
                     .findFirstAsync());

 }
    private RealmQuery<ResultResponse> where() {
        return db.where(ResultResponse.class);
    }

}
