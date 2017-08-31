package com.kyros.technologies.railapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.activity.JobDetailsActiviy;

/**
 * Created by kyros on 20-07-2017.
 */

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {
    private String[]data_name={"Daily","Weekly","Monthly","Night"};
    private int[] data_image={R.drawable.daily_50_job_shce,R.drawable.calendar_50_jobsche,R.drawable.monthly_50_job_sche,R.drawable.today_50_job_sche};
private Context mContext;
    public class JobsViewHolder extends  RecyclerView.ViewHolder{
        public LinearLayout floor_level_linear_adapter_jobs;
    public ImageView image_adapter_jobs;
        public TextView text_view_adapter_jobs;
        public JobsViewHolder(View itemView) {
            super(itemView);
            floor_level_linear_adapter_jobs=(LinearLayout)itemView.findViewById(R.id.floor_level_linear_adapter_jobs);
            image_adapter_jobs=(ImageView)itemView.findViewById(R.id.image_adapter_jobs);
            text_view_adapter_jobs=(TextView)itemView.findViewById(R.id.text_view_adapter_jobs);
        }
    }
public JobsAdapter(Context mContext){
    this.mContext=mContext;
}
    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_jobs_layout,parent,false);
        return new JobsViewHolder( view);
    }

    @Override
    public void onBindViewHolder(JobsViewHolder holder, final int position) {
    holder.image_adapter_jobs.setImageResource(data_image[position]);
        holder.text_view_adapter_jobs.setText(data_name[position]);
        holder.floor_level_linear_adapter_jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, JobDetailsActiviy.class);
                intent.putExtra("name",data_name[position]);
                intent.putExtra("image",data_image[position]);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_name.length;
    }
}
