package com.kashey.conference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Fragment fragment;
    private Switch mSwitch;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container,fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.greeting_text);
        fragment=new HomeFragment();
        loadFragment(fragment);
       // loadLanguage("en");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home)
                {
                    fragment=new HomeFragment();
                    loadFragment(fragment);
                    getSupportActionBar().setTitle("Home");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                if(item.getItemId()==R.id.nav_member)
                {
                    fragment=new MemberFragment();
                    loadFragment(fragment);
                    getSupportActionBar().setTitle("Member");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                if(item.getItemId()==R.id.nav_gallery)
                {
                    fragment=new GalleryFragment();
                    loadFragment(fragment);
                    getSupportActionBar().setTitle("Gallery");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                if(item.getItemId()==R.id.nav_college)
                {
                    fragment=new CollegeFragment();
                    loadFragment(fragment);
                    getSupportActionBar().setTitle("College");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }

                    return false;

               /* switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment=new HomeFragment();
                        loadFragment(fragment);
                        getSupportActionBar().setTitle("Home");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;

                    default:
                        return false;
                }*/

            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        if(item.getItemId()==R.id.menu_contact){
            Intent intent=new Intent(MainActivity.this,contact.class);
            startActivity(intent);
          //  Toast.makeText(this, "tttt", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.menu_feedback){

                //Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();


                Intent intent=new Intent(MainActivity.this,FeedbackActivity.class);
               // intent.putExtra("name","WELCOME");
                startActivity(intent);

        }
        if(item.getItemId()==R.id.menu_exit){

               // finish();
                System.exit(0);

        }
        if(item.getItemId()==R.id.nav_home){
            Toast.makeText(this, "kkkkk", Toast.LENGTH_SHORT).show();

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {             //display menu in toolbar
        getMenuInflater().inflate(R.menu.optioin_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_switch);

        View view = MenuItemCompat.getActionView(menuItem);
        Switch switcha = (Switch) view.findViewById(R.id.switch_id);
        String lang=LocaleHelper.getLanguage(this);
        if(lang.equalsIgnoreCase("bo")){
            switcha.setChecked(true);
        }
        switcha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    loadLanguage("bo");
                }else{
                    loadLanguage("en");
                }
            }
        });
        return  true;
    }

    private void loadLanguage(String lang) {
        LocaleHelper.setLocale(this,lang);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        startActivity(intent);
    }



}
