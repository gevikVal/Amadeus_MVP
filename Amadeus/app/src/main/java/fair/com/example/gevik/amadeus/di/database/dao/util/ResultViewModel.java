package fair.com.example.gevik.amadeus.di.database.dao.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import fair.com.example.gevik.amadeus.di.database.dao.ResultDao;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import io.realm.Realm;

/**
 * Created by gevik on 2/28/2018.
 */

public class ResultViewModel extends ViewModel {
    private Realm realm;
    private ResultDao dao;
    private LiveData<ResultResponse> result;

    public ResultViewModel() {
        this.realm = realm;
        dao = new ResultDao(realm);
        result = dao.findAllAync();
    }

    protected void onCleared() {

    }

    public LiveData<ResultResponse> getResult() {
        return result;
    }

}
