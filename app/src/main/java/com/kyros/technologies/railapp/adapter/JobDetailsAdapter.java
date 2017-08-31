package com.kyros.technologies.railapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kyros.technologies.railapp.R;

import es.dmoral.toasty.Toasty;

/**
 * Created by kyros on 20-07-2017.
 */

public class JobDetailsAdapter extends RecyclerView .Adapter<JobDetailsAdapter.JobDetailAdapterviewHolder> {

    private Context mContext;
    private boolean clicked=true;
    public JobDetailsAdapter (Context mContext){
        this.mContext=mContext;
    }
    public class JobDetailAdapterviewHolder extends RecyclerView.ViewHolder{
        public LinearLayout comment_job_details,linear_comment_show;

        public JobDetailAdapterviewHolder(View itemView) {
            super(itemView);
            comment_job_details=(LinearLayout)itemView.findViewById(R.id.comment_job_details);
            linear_comment_show=(LinearLayout)itemView.findViewById(R.id.linear_comment_show);
        }
    }
    @Override
    public JobDetailAdapterviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_job_detail,parent,false);
        return new JobDetailAdapterviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final JobDetailAdapterviewHolder holder, final int position) {
        holder.comment_job_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(mContext, "clicked Is : "+position, Toast.LENGTH_SHORT, true).show();

                if(!clicked){
                    clicked=false;
                    holder.linear_comment_show.setVisibility(View.VISIBLE);
                }else{
                    clicked=true;
                    holder.linear_comment_show.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
