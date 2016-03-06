package id.co.technomotion.smass.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.R;
import id.co.technomotion.smass.application.SmassApp;
import id.co.technomotion.smass.controller.event.LoginFailed;
import id.co.technomotion.smass.controller.event.LoginSucceeded;
import id.co.technomotion.smass.controller.event.SignupFailed;
import id.co.technomotion.smass.controller.event.SignupSucceded;
import id.co.technomotion.smass.utils.ClientToServer;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by omayib on 12/26/14.
 */
public class LoginActivity extends Activity {
    private EditText inEmail,inPassword;
    private Button btnLogin,btnSignup;
    private SmassApp app;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app= (SmassApp) getApplication();
        EventBus.getDefault().register(this);

        System.out.println("gcm reg id" +app.getGcmRegistrationId());

        pd=new ProgressDialog(this);
        pd.setMessage("please wait...");

        inEmail= (EditText) findViewById(R.id.et_email);
        inPassword= (EditText) findViewById(R.id.et_password);
        btnLogin= (Button) findViewById(R.id.btn_login);
        btnSignup= (Button) findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=inEmail.getText().toString();
                String pw=inPassword.getText().toString();

                if(em.isEmpty()||pw.isEmpty())
                    return;

                if(!pd.isShowing())pd.show();
                app.getCredential().signup(em,pw,app.getGcmRegistrationId());

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=inEmail.getText().toString();
                String pw=inPassword.getText().toString();

                if(em.isEmpty()||pw.isEmpty())
                    return;

                if(!pd.isShowing())pd.show();
                app.getCredential().login(em, pw,app.getGcmRegistrationId());

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(LoginFailed e){
        if(pd.isShowing())pd.dismiss();

    }
    public void onEvent(LoginSucceeded e){
        if(pd.isShowing())pd.dismiss();
        System.out.println("on event login seccedded happen!");
        app.init(e.email,e.token);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
    public void onEvent(SignupFailed e){
        if(pd.isShowing())pd.dismiss();

    }
    public void onEvent(SignupSucceded e){
        if(pd.isShowing())pd.dismiss();
        System.out.println("on event signup seccedded happen!");
        app.init(e.email, e.token);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }



}
