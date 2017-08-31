package com.kyros.technologies.railapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.fragments.LandingPageFragment;
import com.kyros.technologies.railapp.fragments.NotificationFragment;
import com.kyros.technologies.railapp.fragments.ProfileFragment;

import es.dmoral.toasty.Toasty;

public class AdminActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private AlertDialog forget_dialog;
    private TextView action_bar_title;
    private FrameLayout container_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_barlayout);
        setContentView(R.layout.activity_admin);
        action_bar_title=(TextView)findViewById(R.id.action_bar_title);
        container_fragment=(FrameLayout)findViewById(R.id.container_fragment);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setBackgroundColor(getResources().getColor(R.color.bottom_navi_background));
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        LandingPageFragment frag=new LandingPageFragment();
        FragmentTransaction fragmentTrans=
                getSupportFragmentManager().beginTransaction();
        fragmentTrans.replace(R.id.container_fragment,frag);
        fragmentTrans.addToBackStack(null);
        fragmentTrans.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_logout:
                openpopup();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStop() {
        super.onStop();
        closepopup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closepopup();
    }

    private void closepopup(){
        if(forget_dialog!=null&& forget_dialog.isShowing()){
            forget_dialog.dismiss();
        }
    }
    private void openpopup() {
        if(forget_dialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(AdminActivity.this);

            forget_dialog=builder.create();
            forget_dialog.setMessage("Do you want to logout?");
            forget_dialog.setButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Logout();
                }
            });
            forget_dialog.setButton2("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    closepopup();
                }
            });
            //  forget_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            forget_dialog.setCancelable(true);
            forget_dialog.setCanceledOnTouchOutside(true);

        }
        forget_dialog.show();

    }
    private void Logout() {
        Intent m=new Intent(AdminActivity.this,SignInActivity.class);
        m.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(m);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(actionBar!=null){
                        //actionBar.setTitle("Home");
                        action_bar_title.setText("Admin Panel");
                    }
                    Toasty.info(AdminActivity.this, "Admin Panel", Toast.LENGTH_SHORT, true).show();
                    LandingPageFragment landinfrag=new LandingPageFragment();
                    FragmentTransaction fragmentTrans=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTrans.replace(R.id.container_fragment,landinfrag);
                    fragmentTrans.addToBackStack(null);
                    fragmentTrans.commit();

                    return true;
                case R.id.navigation_profile:
                    if(actionBar!=null){
                        //actionBar.setTitle("User Profile");
                        action_bar_title.setText("User Profile");

                    }
                    Toasty.info(AdminActivity.this, "profile", Toast.LENGTH_SHORT, true).show();

                    ProfileFragment fragprofile=new ProfileFragment();
                    FragmentTransaction fragmentTrans1=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTrans1.replace(R.id.container_fragment,fragprofile);
                    fragmentTrans1.addToBackStack(null);
                    fragmentTrans1.commit();
                    return true;
                case R.id.navigation_notifications:
                    NotificationFragment notificationFragment=new NotificationFragment();
                    FragmentTransaction fragmentTransaction=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_fragment,notificationFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    Toasty.info(AdminActivity.this, "notification", Toast.LENGTH_SHORT, true).show();

                    return true;
            }
            return false;
        }

    };
}
