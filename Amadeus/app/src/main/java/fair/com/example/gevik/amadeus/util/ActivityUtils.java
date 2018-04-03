package fair.com.example.gevik.amadeus.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import fair.com.example.gevik.amadeus.R;


/**
 * Created by gvalijani on 03/06/18.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, String tag, boolean isOverlapped) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left);

        if (isOverlapped) {
            transaction.add(frameId, fragment);
        } else {
            transaction.replace(frameId, fragment, tag);
        }
        transaction.addToBackStack(tag);
        transaction.commit();
    }

}
