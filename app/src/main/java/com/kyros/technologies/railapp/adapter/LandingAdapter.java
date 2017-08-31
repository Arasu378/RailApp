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
import android.widget.Toast;

import com.google.android.gms.gcm.Task;
import com.google.android.gms.vision.text.Line;
import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.activity.AdminTaskListActivity;
import com.kyros.technologies.railapp.activity.JobsScheduleActivity;
import com.kyros.technologies.railapp.activity.TaskListActivity;
import com.kyros.technologies.railapp.model.TaskHome;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by kyros on 19-07-2017.
 */

public class LandingAdapter extends RecyclerView.Adapter<LandingAdapter.MyViewHolder> {
   private Context mContext;
    private int proId=0;
    private String[] name={"Road Level","Concore","Platform Level"};
    private int[] picture={R.drawable.floor_level_light_50,R.drawable.concore_icon_50px,R.drawable.train_icon_50px};
    private String[] sub_domain={"Pavement","Circulating Area","Lifts","Common Area","Passages","Tracks"};
    private ArrayList<TaskHome>taskHomeArrayList;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView  image_adapter_landing;
        public TextView  text_view_adapter_landing,sub_level_one,sub_level_two;
        public LinearLayout floor_level_linear_adapter;


        public MyViewHolder(View itemView) {
            super(itemView);
            floor_level_linear_adapter=(LinearLayout)itemView.findViewById(R.id.floor_level_linear_adapter);
            text_view_adapter_landing=(TextView)itemView.findViewById(R.id.text_view_adapter_landing);
            image_adapter_landing=(ImageView)itemView.findViewById(R.id.image_adapter_landing);
            sub_level_one=(TextView)itemView.findViewById(R.id.sub_level_one);
            sub_level_two=(TextView)itemView.findViewById(R.id.sub_level_two);

        }
    }
    public LandingAdapter(Context mContext,ArrayList<TaskHome>taskHomeArrayList,int ProId){
     this.mContext=mContext;
        this.taskHomeArrayList=taskHomeArrayList;
        this.proId=ProId;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_landing,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TaskHome taskHome=taskHomeArrayList.get(position);
        holder.image_adapter_landing.setImageResource(picture[position]);
        holder.text_view_adapter_landing.setText(taskHome.getTaskHeader());
//        holder.text_view_adapter_landing.setText(name[position]);
        holder.sub_level_one.setText(taskHome.getTaskSubHeaderOne());
        holder.sub_level_two.setText(taskHome.getTaskSubHeaderTwo());
//        switch (position){
//            case 0:
//                holder.sub_level_one.setText(sub_domain[0]);
//                holder.sub_level_two.setText(sub_domain[1]);
//                break;
//            case 1:
//                holder.sub_level_one.setText(sub_domain[2]);
//                holder.sub_level_two.setText(sub_domain[3]);
//                break;
//            case 2:
//                holder.sub_level_one.setText(sub_domain[4]);
//                holder.sub_level_two.setText(sub_domain[5]);
//                break;
//        }

//        holder.floor_level_linear_adapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(mContext, JobsScheduleActivity.class);
//                intent.putExtra("name",name[position]);
//                intent.putExtra("image",picture[position]);
//                mContext.startActivity(intent);
//            }
//        });
        holder.sub_level_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proId==1){
                    Toasty.info(mContext,"Sub Level One!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mContext, TaskListActivity.class);
                    String value=holder.sub_level_one.getText().toString();
                    value=value.replace(" ","");
                    intent.putExtra("name",value);
                    intent.putExtra("actionbar",taskHome.getTaskHeader());
                    mContext.startActivity(intent);
                }else if(proId==2){
                    Toasty.info(mContext,"Sub Level One!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(mContext, AdminTaskListActivity.class);
                    String value=holder.sub_level_one.getText().toString();
                    value=value.replace(" ","");
                    intent.putExtra("name",value);
                    intent.putExtra("actionbar",taskHome.getTaskHeader());
                    mContext.startActivity(intent);
                }


            }
        });
        holder.sub_level_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proId==1){
                    Toasty.info(mContext,"Sub Level Two!",Toast.LENGTH_SHORT).show();
                    String value=holder.sub_level_two.getText().toString();
                    value=value.replace(" ","");
                    Intent intent=new Intent(mContext, TaskListActivity.class);
                    intent.putExtra("name",value);
                    intent.putExtra("actionbar",taskHome.getTaskHeader());
                    mContext.startActivity(intent);
                }else if(proId==2){
                    Toasty.info(mContext,"Sub Level Two!",Toast.LENGTH_SHORT).show();
                    String value=holder.sub_level_two.getText().toString();
                    value=value.replace(" ","");
                    Intent intent=new Intent(mContext, AdminTaskListActivity.class);
                    intent.putExtra("name",value);
                    intent.putExtra("actionbar",taskHome.getTaskHeader());
                    mContext.startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return taskHomeArrayList.size();
    }
}
