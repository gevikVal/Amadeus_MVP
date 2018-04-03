package fair.com.example.gevik.amadeus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import fair.com.example.gevik.amadeus.R;

/**
 * Created by gevik on 3/9/2018.
 */

public class Utility {
    Context context;

    public Utility(Context context) {
        this.context = context;
    }

    public String getPreferredCompanySort() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_company_key), context.getString(R.string.pref_company_sort_value_asc));
    }
}
