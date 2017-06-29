package com.aliakbar.meditrack.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.fragment.AddMedicineFragment;
import com.aliakbar.meditrack.fragment.HomeFragment;
import com.aliakbar.meditrack.fragment.MedicineListFragment;
import com.aliakbar.meditrack.fragment.SosSettingsFragment;
import com.aliakbar.meditrack.fragment.UserAccountFragment;
import com.aliakbar.meditrack.manager.ObjectFactory;
import com.aliakbar.meditrack.utils.BaseActivity;
import com.aliakbar.meditrack.utils.Utils;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    SearchView search_view_medicines;
    Fragment fragment;
    boolean doubleBackToExitPressedOnce = false;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(ObjectFactory.BROADCAST_RESPONSE)) {
                if (intent.getBooleanExtra(ObjectFactory.BROADCAST_RESPONSE_STATUS, false)) {
                    showSearchBar();
                } else {
                    hideSearchBar();
                }
            }
        }


    };

    private void hideSearchBar() {
        search_view_medicines.setVisibility(View.GONE);

    }

    private void showSearchBar() {
        search_view_medicines.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intent = new IntentFilter();
        intent.addAction(ObjectFactory.BROADCAST_RESPONSE);
        setBroadcastReceiver(broadcastReceiver, intent);

        initViews();
        viewClickListeners();

        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        search_view_medicines = (SearchView) findViewById(R.id.search_view_medicines);
        search_view_medicines.setActivated(true);
        search_view_medicines.setQueryHint("Type medicine name here");
        search_view_medicines.onActionViewExpanded();
        search_view_medicines.setIconified(false);
        search_view_medicines.clearFocus();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

    }

    private void viewClickListeners() {
        setSupportActionBar(toolbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_SOS) {
            intentSosFragment();

//            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_1:
                intentHome();
                break;
            case R.id.action_2:
                intentMedicineListFragment();
                break;
            case R.id.action_3:
                intentUserAccountFragment();
                break;
        }

        return true;
    }

    private void intentHome() {
        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

    private void intentMedicineListFragment() {
        fragment = new MedicineListFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

    private void intentUserAccountFragment() {
        fragment = new UserAccountFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

    private void intentSosFragment() {
        fragment = new SosSettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

    @Override
    public void onBackPressed() {

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.home_fragment_container);

        if (currentFragment instanceof HomeFragment) {
            if (doubleBackToExitPressedOnce) {
                this.finish();
            } else {
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
        if (currentFragment instanceof AddMedicineFragment) {
            intentUserAccountFragment();
        } else if (currentFragment instanceof SosSettingsFragment) {
            intentUserAccountFragment();
        } else if (currentFragment instanceof UserAccountFragment) {
            intentHome();
        } else if (currentFragment instanceof MedicineListFragment) {
            intentHome();
        } else if (!(currentFragment instanceof HomeFragment)) {
            super.onBackPressed();
        }
    }

}
