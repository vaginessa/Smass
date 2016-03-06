package id.co.technomotion.smass.view.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import id.co.technomotion.smass.application.SmassApp;
import id.co.technomotion.smass.model.ItemMenu;
import id.co.technomotion.smass.model.User;
import id.co.technomotion.smass.view.fragment.CategoriesFragment;
import id.co.technomotion.smass.view.fragment.DefaultFragment;
import id.co.technomotion.smass.view.fragment.ListAnnouncementFragment;
import id.co.technomotion.smass.view.fragment.NavigationDrawerFragment;
import id.co.technomotion.smass.R;
import id.co.technomotion.smass.view.fragment.PlaceholderFragment;
import id.co.technomotion.smass.view.fragment.SettingFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private List<ItemMenu> listMenu;
    private SmassApp app;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app= (SmassApp) getApplication();
        user=app.getUser();


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        FragmentManager fragmentManager = getSupportFragmentManager();


        fragmentManager.beginTransaction()
                .replace(R.id.container,new DefaultFragment())
                .commit();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        // Prepare list menu
        listMenu=new ArrayList<>();
        listMenu.add(new ItemMenu(-123,"Daftar kategori"));
        listMenu.add(new ItemMenu(-124, "Setting"));

        mNavigationDrawerFragment.updateListMenu(listMenu);

        user.getFollowedCategories();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position,int selectedId) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment=null;
        switch (selectedId){
            case -123:
                System.out.println("id -123 terpilih");
                fragment=new CategoriesFragment();
                break;
            case -124:
                System.out.println("id -124 terpilih");
                fragment=new SettingFragment();
                break;
            default:
                System.out.println("id lainnya terpilih");
                fragment=new ListAnnouncementFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("id_category",selectedId);
                fragment.setArguments(bundle);
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container,fragment )
                .commit();

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
