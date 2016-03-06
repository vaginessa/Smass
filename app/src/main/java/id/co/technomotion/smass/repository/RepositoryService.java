package id.co.technomotion.smass.repository;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.controller.event.LoginSucceeded;
import id.co.technomotion.smass.controller.event.RegisterGcmSucceded;
import id.co.technomotion.smass.controller.event.SignoutSucceded;
import id.co.technomotion.smass.controller.event.SignupSucceded;

/**
 * Created by omayib on 1/1/15.
 */
public class RepositoryService {
    private SmassPreference smassPreference;

    public RepositoryService(SmassPreference smassPreference) {
        this.smassPreference = smassPreference;
    }
    public void init(){
        EventBus.getDefault().register(this);
    }
    public void tearDown(){
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(RegisterGcmSucceded e){
        smassPreference.putGcmRegistrationId(e.regId);
    }
    public void onEvent(LoginSucceeded e){
        smassPreference.putUser(e.email, e.token);
    }
    public void onEvent(SignupSucceded e){
        smassPreference.putUser(e.email,e.token);
    }
    public void onEvent(SignoutSucceded e){
        smassPreference.clear();
    }
}
