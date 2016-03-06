package id.co.technomotion.smass.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.R;
import id.co.technomotion.smass.application.SmassApp;
import id.co.technomotion.smass.controller.event.RegisterGcmFailed;
import id.co.technomotion.smass.controller.event.RegisterGcmSucceded;
import id.co.technomotion.smass.controller.event.SplashScreenCompleted;
import id.co.technomotion.smass.controller.event.SplashScreenOnProgress;
import id.co.technomotion.smass.utils.FontUtils;
import id.co.technomotion.smass.utils.Gcm;

/**
 * Created by omayib on 1/1/15.
 */
public class SplashscreenActivity extends Activity {
    private SmassApp app;
    private ProgressBar progressBar;
    private GoogleCloudMessaging gcm = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);

        app= (SmassApp) getApplication();

        EventBus.getDefault().register(this);

        TextView judul=(TextView)findViewById(R.id.textView2);
        TextView keterangan=(TextView)findViewById(R.id.textView3);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(20);
        progressBar.setVisibility(View.VISIBLE);

        judul.setTypeface(FontUtils.loadFontFromAssets(FontUtils.FONT_BLACK_DECEMBER));
        keterangan.setTypeface(FontUtils.loadFontFromAssets(FontUtils.FONT_CAVIAR_DREAMS));


        if(Gcm.isPlayServicesAvailable(this)&&!app.isGcmRegistered()){
            app.getCredential().splashScreen(20);
            System.out.println("gps availabl "+ Gcm.isPlayServicesAvailable(this));
            gcm = GoogleCloudMessaging.getInstance(this);
            if(gcm!=null){
                System.out.println("login gcm ok, then register");
                app.getCredential().registerGcm(gcm,Gcm.SENDER_ID);
            }
        }else{
            gotoNextActivity();
        }


    }

    private void gotoNextActivity() {
        if(app.isTokenAvailable()){
            startActivity(new Intent(this, MainActivity.class));
        }else{
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(RegisterGcmFailed e){


    }
    public void onEvent(RegisterGcmSucceded e){

    }

    public void onEvent(SplashScreenCompleted e){
        System.out.println("completed");
        if(!app.isGcmRegistered()){
            Toast.makeText(this,"perangkat kamu tidak mendukung push notif",Toast.LENGTH_SHORT).show();
        }
        gotoNextActivity();

    }
    public void onEvent(SplashScreenOnProgress e){
        System.out.println(e.progress+"");
        progressBar.setProgress(e.progress);
    }


}
