package com.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.events.ModelFactoryInitialized;
import com.myapplication.fragments.BrowseFragment;
import com.webservices.model.ClientCredModel;
import com.webservices.model.ModelFactory;
import com.webservices.model.seriesEndpoints.AnimeModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private NavigationView navView;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Main Activity Starting!");
        EventBus.getDefault().register(this);
        ModelFactory.init(this);

        setContentView(R.layout.activity_main);
        // Set up the navigation drawer
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        drawer.addDrawerListener(drawerToggle);
        setupDrawerContent(navView);
    }

    /**
     * When this function is called, it implies that ModelFactory has been initialized
     * and the app is ready for use.
     *
     * The app needs to display a loading screen before this function is called (or else we might be
     * donezo ecksdee)
     *
     * @param initialized dummy marker object
     */
    @Subscribe
    public void onModelFactoryInitialized(ModelFactoryInitialized initialized){
        Log.d(TAG, "ModelFactoryDone");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    private void setupDrawerContent(NavigationView navView) {
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectItem(menuItem);
                        return true;
                    }
                });

    }

    /**
     * Navigation Drawer click logic
     * @param item  MenuItem that was selected
     */
    public void selectItem(MenuItem item) {
        Fragment fragment;
        Class fragmentClass = BrowseFragment.class;
        Bundle bundle = new Bundle();
        switch (item.getItemId()) {
            case R.id.nav_login_temp:
                // Login
                break;
            default:
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    bundle.putInt("id", item.getItemId());
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.flPlaceholder, fragment).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }
        item.setChecked(true);
        drawer.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
