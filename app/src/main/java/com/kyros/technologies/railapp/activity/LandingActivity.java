package com.kyros.technologies.railapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.data.DataStorage;
import com.kyros.technologies.railapp.fragments.LandingPageFragment;
import com.kyros.technologies.railapp.fragments.NotificationFragment;
import com.kyros.technologies.railapp.fragments.ProfileFragment;
import com.kyros.technologies.railapp.model.LoginModel;
import com.kyros.technologies.railapp.model.TaskDataModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class LandingActivity extends AppCompatActivity {
    private FrameLayout container_fragment;
    private ActionBar actionBar;
    private TextView action_bar_title;
    private AlertDialog forget_dialog;
    private DataStorage dataStorage;
    private ArrayList<TaskDataModel>pavementslist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>circulatingarealist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>liftslist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>commonarealist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>passageslist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>trackslist=new ArrayList<TaskDataModel>();
    private String[] CommonArea={"Cleaning of Automatic Fare collection system","Cleaning of we floors","Cleaning of heavy electrical equipments","Cleaning of stairs","Cleaning of Hand rails","Cleaning of light fixtures"};
    private String [] Passages={"Cleaning of light fixtures and accessories","Cleaning of Fans","Cleaning of Doors and Windows","Cleaning of Fire Hydrants","Cleaning of Sign Boards and Notice Boards"};
    private String [] Tracks={"Cleaning and washing of track plinth","Cleaning of platform screen doors and shutters"};
    private String[] Lifts={"Cleaning of lift floors","Cleaning of Lift Glasses","Cleaning of Notice boards"};
    private String [] CirculatingArea={"Scrubbing","Cleaning of External light fittings and accessories","Cleaning and attention of all drains"};
    private String [] Pavement={"Scrubbing","Cleaning of wet floors","Cleaning of Lifts","Cleaning of Stainless steel/PVC hand railing"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_barlayout);
        setContentView(R.layout.activity_landing);
        dataStorage=DataStorage.getStore(getApplicationContext());
        String pavementsstored=dataStorage.getPavementsList();
        String circulatingareastored=dataStorage.getCirculatingAreaList();
        String liftsliststored=dataStorage.getLiftsList();
        String commonareastored=dataStorage.getCommonAreaList();
        String passagestored=dataStorage.getPassagesList();
        String tracksliststored=dataStorage.getTracksList();
        DateFormat df = new SimpleDateFormat("dd-MM-yyy");
        final String now = df.format(new Date());
        if(pavementsstored==null){
            for(int i=0; i<Pavement.length;i++){
                TaskDataModel loginModel=new TaskDataModel();
                loginModel.setChecked(false);
                loginModel.setComments(null);
                loginModel.setDate(now);
                loginModel.setTaskDataValue(Pavement[i]);
                pavementslist.add(loginModel);
            }
            try{
                Gson gson=new Gson();
                String userlist=gson.toJson(pavementslist);
                dataStorage.putPavementsList(userlist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }
        }
        if(circulatingareastored==null){
            for(int i=0; i<CirculatingArea.length;i++){
                TaskDataModel loginModel=new TaskDataModel();
                loginModel.setChecked(false);
                loginModel.setComments(null);
                loginModel.setDate(now);
                loginModel.setTaskDataValue(CirculatingArea[i]);
                circulatingarealist.add(loginModel);
            }
            try{
                Gson gson=new Gson();
                String userlist=gson.toJson(circulatingarealist);
                dataStorage.putCirculatingAreaList(userlist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }

        }
        if(liftsliststored==null){
            for(int i=0; i<Lifts.length;i++){
                TaskDataModel loginModel=new TaskDataModel();
                loginModel.setChecked(false);
                loginModel.setComments(null);
                loginModel.setDate(now);
                loginModel.setTaskDataValue(Lifts[i]);
                liftslist.add(loginModel);
            }
            try{
                Gson gson=new Gson();
                String userlist=gson.toJson(liftslist);
                dataStorage.putLiftsList(userlist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }

        }
        if(passagestored==null){
            for(int i=0; i<Passages.length;i++){
                TaskDataModel loginModel=new TaskDataModel();
                loginModel.setChecked(false);
                loginModel.setComments(null);
                loginModel.setDate(now);
                loginModel.setTaskDataValue(Passages[i]);
                passageslist.add(loginModel);
            }
            try{
                Gson gson=new Gson();
                String userlist=gson.toJson(passageslist);
                dataStorage.putPassagesList(userlist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }
        }
        if(tracksliststored==null){
            for(int i=0; i<Tracks.length;i++){
                TaskDataModel loginModel=new TaskDataModel();
                loginModel.setChecked(false);
                loginModel.setComments(null);
                loginModel.setDate(now);
                loginModel.setTaskDataValue(Tracks[i]);
                trackslist.add(loginModel);
            }
            try{
                Gson gson=new Gson();
                String userlist=gson.toJson(trackslist);
                dataStorage.putTracksList(userlist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }
        }
        if(commonareastored==null){
            for(int i=0; i<CommonArea.length;i++){
                TaskDataModel loginModel=new TaskDataModel();
                loginModel.setChecked(false);
                loginModel.setComments(null);
                loginModel.setDate(now);
                loginModel.setTaskDataValue(CommonArea[i]);
                commonarealist.add(loginModel);
            }
            try{
                Gson gson=new Gson();
                String userlist=gson.toJson(commonarealist);
                dataStorage.putCommonAreaList(userlist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }
        }
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
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(actionBar!=null){
                        //actionBar.setTitle("Home");
                        action_bar_title.setText("Home");
                    }
                    Toasty.info(LandingActivity.this, "home", Toast.LENGTH_SHORT, true).show();
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
                    Toasty.info(LandingActivity.this, "profile", Toast.LENGTH_SHORT, true).show();

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
                    Toasty.info(LandingActivity.this, "notification", Toast.LENGTH_SHORT, true).show();

                    return true;
            }
            return false;
        }

    };
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
            AlertDialog.Builder builder=new AlertDialog.Builder(LandingActivity.this);

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
        Intent m=new Intent(LandingActivity.this,SignInActivity.class);
        m.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(m);

    }

}
