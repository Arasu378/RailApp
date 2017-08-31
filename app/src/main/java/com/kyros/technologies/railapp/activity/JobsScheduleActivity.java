package com.kyros.technologies.railapp.activity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.adapter.JobsAdapter;
import com.kyros.technologies.railapp.adapter.LandingAdapter;

import es.dmoral.toasty.Toasty;

public class JobsScheduleActivity extends AppCompatActivity {
    private String Name= null;
    private int Picture=0;
    private RecyclerView recycler_jobs;
    private JobsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_jobs_schedule);
        try{
            Bundle bundle=getIntent().getExtras();
            Name=bundle.getString("name");
            if(Name!=null){
                actionBar.setTitle(Name+" Jobs Schedule");
            }
            Picture=bundle.getInt("image");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(Name!=null){
            Toasty.success(getApplicationContext(), "Name Is : "+Name, Toast.LENGTH_SHORT, true).show();

        }else{
            Toasty.error(getApplicationContext(), "Could not get the name!", Toast.LENGTH_SHORT, true).show();

        }
        recycler_jobs=(RecyclerView)findViewById(R.id.recycler_jobs);
        adapter=new JobsAdapter(JobsScheduleActivity.this);
        RecyclerView.LayoutManager layoutManagersecond=new LinearLayoutManager(getApplicationContext());
        recycler_jobs.setLayoutManager(layoutManagersecond);
        recycler_jobs.setItemAnimator(new DefaultItemAnimator());
        recycler_jobs.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                JobsScheduleActivity.this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
