package id.co.technomotion.smass.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.R;
import id.co.technomotion.smass.application.SmassApp;
import id.co.technomotion.smass.controller.event.LoadAnnouncementDetailFailed;
import id.co.technomotion.smass.controller.event.LoadAnnouncementDetailSucceded;
import id.co.technomotion.smass.model.Category;
import id.co.technomotion.smass.model.User;
import id.co.technomotion.smass.view.adapter.CategoriesAdapter;
import id.co.technomotion.smass.view.fragment.AdFragment;

/**
 * Created by omayib on 12/25/14.
 */
public class DetailAnnouncementActivity extends Activity {
    private TextView tvDate,tvTitle;
    private WebView wvContent;
    private SmassApp app;
    private User user;
    private int id;
    private ProgressDialog pd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("categories fragment oncreated");
        setContentView(R.layout.fragment_content_announcement);

        app= (SmassApp) getApplication();
        user=app.getUser();

        EventBus.getDefault().register(this);

        pd=new ProgressDialog(this);
        pd.setMessage("please wait...");

        id= getIntent().getIntExtra("id",-1);

        tvDate= (TextView) findViewById(R.id.tv_content_date);
        tvTitle= (TextView) findViewById(R.id.tv_content_title);
        wvContent= (WebView) findViewById(R.id.wv_content);

        wvContent.getSettings().setJavaScriptEnabled(true);


        if(!pd.isShowing())pd.show();
        user.getAnnouncementDetail(id);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void onEvent(LoadAnnouncementDetailSucceded e){
        if(pd.isShowing())pd.dismiss();
        wvContent.loadData(e.announcement.getContent(), "text/html; charset=UTF-8", null);
        tvDate.setText(e.announcement.getDate());
        tvTitle.setText(e.announcement.getTitle());
    }
    public void onEvent(LoadAnnouncementDetailFailed e){
        if(pd.isShowing())pd.dismiss();
        tvDate.setText("");
        tvTitle.setText("");
        Toast.makeText(this,"gagal mengambil data",Toast.LENGTH_SHORT).show();
    }
}
