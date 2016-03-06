package id.co.technomotion.smass.repository;

import android.content.SharedPreferences;

import id.co.technomotion.smass.model.User;

/**
 * Created by omayib on 12/29/14.
 */
public class SmassPreference {
    public static String NAME="smass_preference";
    public static String USER_TOKEN="user_token";
    public static String USER_EMAIL="user_email";
    public static String USER_NAME="user_name";
    public static String GCM_REGID="gcm_registration_id";
    public static String APP_VERSION="app_version";

    private SharedPreferences sp;
    private  SharedPreferences.Editor editor;

    public SmassPreference(SharedPreferences sp) {
        this.sp = sp;
        this.editor=sp.edit();
    }
    public void putUser(String email,String token){
        editor.putString(USER_EMAIL,email).commit();
        editor.putString(USER_TOKEN,token).commit();
    }
    public String getUserEmail(){
        return sp.getString(USER_EMAIL,"");
    }
    public String getUserToken(){
        return sp.getString(USER_TOKEN,"");
    }
    public void putGcmRegistrationId(String regId){
        editor.putString(GCM_REGID,regId).commit();
    }
    public String getGcmRegistrationId(){
        return sp.getString(GCM_REGID,"");
    }
    public void clear(){
        editor.clear().commit();
    }

}
