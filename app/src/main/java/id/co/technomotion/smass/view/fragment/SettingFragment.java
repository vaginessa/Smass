package id.co.technomotion.smass.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.R;
import id.co.technomotion.smass.application.SmassApp;
import id.co.technomotion.smass.controller.event.SignoutFailed;
import id.co.technomotion.smass.controller.event.SignoutSucceded;
import id.co.technomotion.smass.model.Category;
import id.co.technomotion.smass.model.User;
import id.co.technomotion.smass.view.adapter.CategoriesAdapter;

/**
 * Created by omayib on 12/25/14.
 */
public class SettingFragment extends Fragment {
    private SmassApp app;
    private User user;
    private ProgressDialog pd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("setting fragment oncreated");

        app= (SmassApp) getActivity().getApplication();
        user=app.getUser();

        EventBus.getDefault().register(this);

        pd=new ProgressDialog(getActivity());
        pd.setMessage("please wait...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        v.findViewById(R.id.btn_signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pd.isShowing())pd.show();
                user.signout();
            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(SignoutSucceded e){
        if(pd.isShowing()) pd.dismiss();
        getActivity().finish();
    }
    public void onEvent(SignoutFailed e){
        if(pd.isShowing()) pd.dismiss();
        Toast.makeText(getActivity(),"signout failed, try again",Toast.LENGTH_SHORT).show();
    }
}
