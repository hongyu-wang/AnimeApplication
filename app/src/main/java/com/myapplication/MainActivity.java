package com.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
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

import com.events.LoggedOn;
import com.events.ModelFactoryInitialized;
import com.myapplication.fragments.BrowseFragment;
import com.singletons.CredentialSingleton;
import com.webservices.endpointBuilder.QueryString;
import com.webservices.model.ModelFactory;
import com.webservices.model.credentials.UserCredentialModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.android.volley.Request.Method.GET;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String CALLBACK_URI = "AnimeApp://com.myapplication.MainActivity";

    private NavigationView navView;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Main Activity Starting!");

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

    @Subscribe
    public void onLoggedOn(LoggedOn loggedOn){
        Log.d(TAG, loggedOn.code);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause(){
        super.onPause();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onResume(){
        super.onResume();
        EventBus.getDefault().register(this);
        ModelFactory.init(this);


        Uri uri = getIntent().getData();
        if (uri != null) {
            String access_token = uri.getQueryParameter("code");
            ModelFactory.requestModel(this, UserCredentialModel.class, access_token);
        }
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
                login();
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

    private void login() {
        QueryString qs = new QueryString("auth/authorize", GET);
        qs.add("client_id", CredentialSingleton.get().getClientID());
        qs.add("redirect_uri", CALLBACK_URI);
        qs.add("response_type", "code");

        String url = ModelFactory.BASE_ENDPOINT + qs.toString();
        Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(openUrlIntent);
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
