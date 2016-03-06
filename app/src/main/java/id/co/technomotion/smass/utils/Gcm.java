package id.co.technomotion.smass.utils;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by omayib on 1/1/15.
 */
public class Gcm {
    private Activity activity;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static String SENDER_ID="1005137909291";


    public static boolean isPlayServicesAvailable(Activity activity) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
//                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
//                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
                Log.i("smass", "This device is need download play service.");
            } else {
                Log.i("smass", "This device is not supported.");
            }
            return false;
        }
        return true;
    }

}
