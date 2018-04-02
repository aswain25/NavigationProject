package com.example.admin.navigationproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FrameLayout content_frame;
    Toolbar toolbar;

    WebFragment webView = new WebFragment();
    MediaFragment mediaView = new MediaFragment();
    CalculatorFragment calculatorView = new CalculatorFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        content_frame = findViewById(R.id.content_frame);

        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, (Fragment)calculatorView).commit();

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // set item as selected to persist highlight
                        item.setChecked(true);

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        switch (item.getTitle().toString())
                        {
                            case "Browser":
                                transaction.replace(R.id.content_frame, webView);
                                break;
                            case "Media Player":
                                transaction.replace(R.id.content_frame, mediaView);
                                break;
                            case "Calculator":
                                transaction.replace(R.id.content_frame, calculatorView);
                                break;
                            default:
                                break;
                        }
                        transaction.addToBackStack(null);
                        transaction.commit();


                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        initToolBar();
    }

    public void initToolBar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle(R.string.toolbarTitle);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.mipmap.ic_launcher_foreground);
        //toolbar.setNavigationIcon(R.drawable.ic_action_name);
        //toolbar.setNavigationOnClickListener(
        //new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Toast.makeText(MainActivity.this, "clicking the toolbar!", Toast.LENGTH_SHORT).show();
        //    }
        //}
        //);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!mDrawerLayout.isDrawerOpen(GravityCompat.START))
                    mDrawerLayout.openDrawer(GravityCompat.START);
                else
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.back:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0){
                    boolean done = getSupportFragmentManager().popBackStackImmediate();
                }
                break;
            case R.id.preferences_item:
                PreferencesFragment preferencesView = new PreferencesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, preferencesView);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.animalsmenu,menu);
    //    return true;
    //}

}
