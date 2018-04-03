package fair.com.example.gevik.amadeus.di.database.dao;

/**
 * Created by gevik on 2/28/2018.
 */

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;

public  abstract class Dao<T extends RealmObject>   {
    public Dao(Realm db) {
        this.db = db;
    }

    protected Realm db;
    public T copyOrUpdate(T entity) {

        if(db.isInTransaction()) {
            entity = db.copyToRealmOrUpdate(entity);

        } else {
            try {
                db.beginTransaction();
                entity = db.copyToRealmOrUpdate(entity);
                db.commitTransaction();
            } catch (Exception e) {
                db.cancelTransaction();
                throw e;
            }
        }

        return entity;
    }





}
