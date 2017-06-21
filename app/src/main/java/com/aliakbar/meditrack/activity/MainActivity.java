package com.aliakbar.meditrack.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.fragment.HomeFragment;
import com.aliakbar.meditrack.fragment.MedicineListFragment;
import com.aliakbar.meditrack.fragment.UserAccountFragment;
import com.aliakbar.meditrack.utils.BaseActivity;
import com.aliakbar.meditrack.utils.Utils;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewClickListeners();

        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .commit();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_home);
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


           return true;
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
                .addToBackStack(fragment.getTag())
                .commit();
    }

    private void intentMedicineListFragment() {
        fragment = new MedicineListFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .addToBackStack(fragment.getTag())
                .commit();
    }

    private void intentUserAccountFragment() {
        fragment = new UserAccountFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.home_fragment_container, fragment, Utils.getTagForFragment(fragment))
                .addToBackStack(fragment.getTag())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
