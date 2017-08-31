package com.kyros.technologies.railapp.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.adapter.JobDetailsAdapter;
import com.kyros.technologies.railapp.adapter.JobsAdapter;

import es.dmoral.toasty.Toasty;

public class JobDetailsActiviy extends AppCompatActivity {
    private String Name=null;
    private int Image=0;
    private JobDetailsAdapter adapter;
    private RecyclerView recycler_jobs_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_job_details_activiy);
        try{
            Bundle bundle=getIntent().getExtras();
            Name=bundle.getString("name");
            Image=bundle.getInt("image");

        }catch (Exception e){
            e.printStackTrace();
        }
        if(Name!=null){
            Toasty.success(getApplicationContext(), "Name Is : "+Name, Toast.LENGTH_SHORT, true).show();

        }else{
            Toasty.error(getApplicationContext(), "Could not get the name!", Toast.LENGTH_SHORT, true).show();

        }
        recycler_jobs_details=(RecyclerView)findViewById(R.id.recycler_jobs_details);
        adapter=new JobDetailsAdapter(JobDetailsActiviy.this);
        RecyclerView.LayoutManager layoutManagersecond=new LinearLayoutManager(getApplicationContext());
        recycler_jobs_details.setLayoutManager(layoutManagersecond);
        recycler_jobs_details.setItemAnimator(new DefaultItemAnimator());
        recycler_jobs_details.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                JobDetailsActiviy.this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
