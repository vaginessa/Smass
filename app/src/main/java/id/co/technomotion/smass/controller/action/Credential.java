package id.co.technomotion.smass.controller.action;

import android.content.Context;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by omayib on 12/28/14.
 */
public interface Credential {
    void login(String email,String password,String gcmKeyId);
    void signup(String email,String password,String gcmKeyId);
    void registerGcm(GoogleCloudMessaging gcm,String senderId);
    void splashScreen(int duration);
}
