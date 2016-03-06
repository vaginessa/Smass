package id.co.technomotion.smass.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.R;
import id.co.technomotion.smass.application.SmassApp;
import id.co.technomotion.smass.controller.event.LoadAnnouncementListFailed;
import id.co.technomotion.smass.controller.event.LoadAnnouncementListSucceded;
import id.co.technomotion.smass.model.Announcement;
import id.co.technomotion.smass.model.User;
import id.co.technomotion.smass.view.activity.DetailAnnouncementActivity;
import id.co.technomotion.smass.view.adapter.AnnouncementsAdapter;

/**
 * Created by omayib on 12/25/14.
 */
public class ListAnnouncementFragment extends Fragment {
    private AnnouncementsAdapter adapter;
    private List<Announcement>  listAnnouncement =new ArrayList<>();;
    private SmassApp app;
    private User user;
    private int idCategory=-1;
    private ProgressDialog pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("categories fragment oncreated");

        app= (SmassApp)getActivity().getApplication();
        user=app.getUser();

        EventBus.getDefault().register(this);


        pd=new ProgressDialog(getActivity());
        pd.setMessage("please wait...");

        Bundle bundle=getArguments();
        if(bundle.containsKey("id_category")){
               idCategory=bundle.getInt("id_category");
        }



        adapter=new AnnouncementsAdapter(getActivity(),R.layout.item_announcement, listAnnouncement);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_announcement, container, false);

        ListView lvAnnouncements= (ListView) v.findViewById(R.id.listview_announcement);
        lvAnnouncements.setAdapter(adapter);
        lvAnnouncements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Announcement ano= (Announcement) parent.getAdapter().getItem(position);
                getActivity().startActivity(new Intent(getActivity(), DetailAnnouncementActivity.class).putExtra("id",ano.getId()));
            }
        });

        if(idCategory!=-1) {
            if(!pd.isShowing())pd.show();
            user.getAnnouncements(idCategory);
        }
        new AdFragment();
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    public void onEvent(LoadAnnouncementListSucceded e){
        System.out.println("load list announcement success");
        if(pd.isShowing())pd.dismiss();
        listAnnouncement.addAll(e.announcementList);
        adapter.notifyDataSetChanged();

        if(listAnnouncement.isEmpty())
            Toast.makeText(getActivity(),"tidak ada pengumuman",Toast.LENGTH_SHORT).show();

    }
    public void onEvent(LoadAnnouncementListFailed e){
        System.out.println("load list announcement failed");
        if(pd.isShowing())pd.dismiss();
        Toast.makeText(getActivity(),"error connection",Toast.LENGTH_SHORT).show();
    }
}
