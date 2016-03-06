package id.co.technomotion.smass.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.R;
import id.co.technomotion.smass.application.SmassApp;
import id.co.technomotion.smass.controller.event.FollowCategoryFailed;
import id.co.technomotion.smass.controller.event.FollowCategorySucceded;
import id.co.technomotion.smass.controller.event.LoadCategoriesFailed;
import id.co.technomotion.smass.controller.event.LoadCategoriesSucceded;
import id.co.technomotion.smass.controller.event.LoadFollowedCategoriesSucceded;
import id.co.technomotion.smass.controller.event.UnfollowCategoryFailed;
import id.co.technomotion.smass.controller.event.UnfollowCategorySucceded;
import id.co.technomotion.smass.model.Category;
import id.co.technomotion.smass.model.ItemMenu;
import id.co.technomotion.smass.model.User;
import id.co.technomotion.smass.view.adapter.CategoriesAdapter;

/**
 * Created by omayib on 12/25/14.
 */
public class CategoriesFragment extends Fragment implements CategoriesAdapter.OnFollowListener {
    private CategoriesAdapter adapter;
    private List<Category> listCategory=new ArrayList<>();
    private User user;
    private SmassApp app;
    private ProgressDialog pd;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("categories fragment oncreated");
        EventBus.getDefault().register(this);

        app= (SmassApp) getActivity().getApplication();
        user=app.getUser();

        pd=new ProgressDialog(getActivity());
        pd.setMessage("please wait...");


        adapter=new CategoriesAdapter(getActivity(),R.layout.item_category,listCategory);
        adapter.setOnFollowListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_category, container, false);

        ListView lvCategories= (ListView) v.findViewById(R.id.listview_categories);
        lvCategories.setAdapter(adapter);
        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        if(!pd.isShowing())pd.show();
        user.getCategories();

        return v;
    }
    public void onEvent(LoadCategoriesFailed e){

    }
    public void onEvent(LoadCategoriesSucceded e){
        if(pd.isShowing())pd.dismiss();
        listCategory.addAll(e.categoryList);
        adapter.notifyDataSetChanged();
    }

    public void onEvent(FollowCategoryFailed e){
        if(pd.isShowing())pd.dismiss();
        System.out.println("following succes from categories fragment");
        if(listCategory.contains(e.category)){
            for (Category c:listCategory){
                if(c.equals(e.category)){
                    c.setFollowed(false);
                    System.out.println("c "+c);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void onEvent(FollowCategorySucceded e){
        if(pd.isShowing())pd.dismiss();
        System.out.println("following succes from categories fragment");
        if(listCategory.contains(e.category)){
            for (Category c:listCategory){
                if(c.equals(e.category)){
                    c.setFollowed(true);
                    System.out.println("c "+c);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onFollowChecked(Category category) {
        System.out.println("checked "+category.toString());
        if(!pd.isShowing())pd.show();
        user.follow(category);
    }

    @Override
    public void onFollowUnchecked(Category category) {
        System.out.println("unchecked "+category.toString());
        if(!pd.isShowing())pd.show();
        user.unfollow(category);
    }

    public void onEvent(UnfollowCategorySucceded e){
        if(pd.isShowing())pd.dismiss();
        System.out.println("unfollowing succes from categories fragment");
        if(listCategory.contains(e.category)){
            for (Category c:listCategory){
                if(c.equals(e.category)){
                    c.setFollowed(false);
                    System.out.println("c "+c);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
    public void onEvent(UnfollowCategoryFailed e){
        if(pd.isShowing())pd.dismiss();
        System.out.println("unfollowing failed from categories fragment");
        if(listCategory.contains(e.category)){
            for (Category c:listCategory){
                if(c.equals(e.category)){
                    c.setFollowed(true);
                    System.out.println("c "+c);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

}
