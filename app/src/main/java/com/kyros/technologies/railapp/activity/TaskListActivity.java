package com.kyros.technologies.railapp.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.adapter.JobDetailsAdapter;
import com.kyros.technologies.railapp.adapter.TaskListAdapter;
import com.kyros.technologies.railapp.data.DataStorage;
import com.kyros.technologies.railapp.model.LoginModel;
import com.kyros.technologies.railapp.model.TaskDataModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class TaskListActivity extends AppCompatActivity {
    private String[] CommonArea={"Cleaning of Automatic Fare collection system","Cleaning of we floors","Cleaning of heavy electrical equipments","Cleaning of stairs","Cleaning of Hand rails","Cleaning of light fixtures"};
    private String [] Passages={"Cleaning of light fixtures and accessories","Cleaning of Fans","Cleaning of Doors and Windows","Cleaning of Fire Hydrants","Cleaning of Sign Boards and Notice Boards"};
    private String [] Tracks={"Cleaning and washing of track plinth","Cleaning of platform screen doors and shutters"};
    private String[] Lifts={"Cleaning of lift floors","Cleaning of Lift Glasses","Cleaning of Notice boards"};
    private String [] CirculatingArea={"Scrubbing","Cleaning of External light fittings and accessories","Cleaning and attention of all drains"};
    private String [] Pavement={"Scrubbing","Cleaning of wet floors","Cleaning of Lifts","Cleaning of Stainless steel/PVC hand railing"};
    private String SubDomain=null;
    private RecyclerView recycler_task_list;
    private TaskListAdapter adapter;
    private String actionBarName=null;
    private DataStorage dataStorage;
    private ArrayList<TaskDataModel>pavementslist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>circulatingarealist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>liftslist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>commonarealist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>passageslist=new ArrayList<TaskDataModel>();
    private ArrayList<TaskDataModel>trackslist=new ArrayList<TaskDataModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_task_list);
        dataStorage=DataStorage.getStore(getApplicationContext());

        try{
            Bundle bundle=getIntent().getExtras();
            SubDomain=bundle.getString("name");
            actionBarName=bundle.getString("actionbar");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(actionBarName!=null){
            actionBar.setTitle(actionBarName);
        }
        if(SubDomain!=null){
            Toasty.info(getApplicationContext(),"Sub Domain is : "+SubDomain, Toast.LENGTH_SHORT).show();
        }else{
            Toasty.error(getApplicationContext(),"Sub Domain is Empty! ", Toast.LENGTH_SHORT).show();

        }
        recycler_task_list=(RecyclerView)findViewById(R.id.recycler_task_list);
        RecyclerView.LayoutManager layoutManagersecond=new LinearLayoutManager(getApplicationContext());
        try{
            switch (SubDomain){
                case "CommonArea":
                    try{
                        commonarealist.clear();
                        String userprofilevaluesaved=dataStorage.getCommonAreaList();
                        Gson gsons=new Gson();
                        Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                        commonarealist=gsons.fromJson(userprofilevaluesaved,type1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    adapter=new TaskListAdapter(TaskListActivity.this,CommonArea,"Common Area",commonarealist);
                    break;
                case "Passages":
                    try{
                        passageslist.clear();
                        String userprofilevaluesaved=dataStorage.getPassagesList();
                        Gson gsons=new Gson();
                        Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                        passageslist=gsons.fromJson(userprofilevaluesaved,type1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    adapter=new TaskListAdapter(TaskListActivity.this,Passages,"Passages",passageslist);
                    break;
                case "Tracks":
                    try{
                        trackslist.clear();
                        String userprofilevaluesaved=dataStorage.getTracksList();
                        Gson gsons=new Gson();
                        Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                        trackslist=gsons.fromJson(userprofilevaluesaved,type1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    adapter=new TaskListAdapter(TaskListActivity.this,Tracks,"Tracks",trackslist);
                    break;
                case "Lifts":
                    try{
                        liftslist.clear();
                        String userprofilevaluesaved=dataStorage.getLiftsList();
                        Gson gsons=new Gson();
                        Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                        liftslist=gsons.fromJson(userprofilevaluesaved,type1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    adapter=new TaskListAdapter(TaskListActivity.this,Lifts,"Lifts",liftslist);
                    break;
                case "CirculatingArea":
                    try{
                        circulatingarealist.clear();
                        String userprofilevaluesaved=dataStorage.getCirculatingAreaList();
                        Gson gsons=new Gson();
                        Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                        circulatingarealist=gsons.fromJson(userprofilevaluesaved,type1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    adapter=new TaskListAdapter(TaskListActivity.this,CirculatingArea,"Circulating Area",circulatingarealist);
                    break;
                case "Pavements":
                    try{
                        pavementslist.clear();
                        String userprofilevaluesaved=dataStorage.getPavementsList();
                        Gson gsons=new Gson();
                        Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                        pavementslist=gsons.fromJson(userprofilevaluesaved,type1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    adapter=new TaskListAdapter(TaskListActivity.this,Pavement,"Pavement",pavementslist);
                    break;
            }
            recycler_task_list.setLayoutManager(layoutManagersecond);
            recycler_task_list.setItemAnimator(new DefaultItemAnimator());
            recycler_task_list.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }catch (Exception e){
            e.printStackTrace();
        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                TaskListActivity.this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
