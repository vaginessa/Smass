package id.co.technomotion.smass.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import id.co.technomotion.smass.controller.action.UserAction;
import id.co.technomotion.smass.controller.client.ApiClient;
import id.co.technomotion.smass.controller.client.UserClient;
import id.co.technomotion.smass.controller.client.UserCredential;
import id.co.technomotion.smass.model.User;
import id.co.technomotion.smass.repository.RepositoryService;
import id.co.technomotion.smass.repository.SmassPreference;

/**
 * Created by omayib on 12/28/14.
 */
public class SmassApp extends Application {
    public static Context context;
    private UserAction userAction;
    private ApiClient apiClient;
    private UserCredential credential;
    private User user;
    private SmassPreference pref;
    private RepositoryService repositoryService;


    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static String SENDER_ID="1005137909291";

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        SharedPreferences sharedPreferences=getSharedPreferences(SmassPreference.NAME,0);
        pref=new SmassPreference(sharedPreferences);

        repositoryService=new RepositoryService(pref);
        repositoryService.init();

        apiClient=new ApiClient();
        credential=new UserCredential(apiClient);

    }

    public boolean isTokenAvailable(){
        return pref.getUserToken().isEmpty()?false:true;
    }

    public boolean isGcmRegistered(){ return pref.getGcmRegistrationId().isEmpty()?false:true;}

    public String getGcmRegistrationId(){
        return pref.getGcmRegistrationId();
    }
    public UserCredential getCredential() {
        return credential;
    }

    public void init(String email,String token){
        userAction=new UserClient(apiClient);
        user=new User(email,token,userAction);
    }

    public void tearDown(){
        repositoryService.tearDown();
    }

    public User getUser() {
        init(pref.getUserEmail(),pref.getUserToken());
        return user;
    }

}
