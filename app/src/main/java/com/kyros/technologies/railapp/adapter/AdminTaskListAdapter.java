package com.kyros.technologies.railapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.data.DataStorage;
import com.kyros.technologies.railapp.model.TaskDataModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by kyros on 21-07-2017.
 */

public class AdminTaskListAdapter extends RecyclerView.Adapter<AdminTaskListAdapter.AdminTaskListAdapterViewHolder> {
    private Context mContext;
    private String[]SubDomain;
    private String Heading;
    private boolean clicked=true;
    private ArrayList<TaskDataModel> commonarealist;
    private DataStorage dataStorage;

    public AdminTaskListAdapter(Context mContext,String[] SubDomain,String Heading, ArrayList<TaskDataModel> commonarealist){
        this.mContext=mContext;
        this.SubDomain=SubDomain;
        this.Heading=Heading;
        this.commonarealist=commonarealist;
    }
public class AdminTaskListAdapterViewHolder extends RecyclerView.ViewHolder{
    public TextView task_details_list_heading,task_message,date_task;
    public LinearLayout comment_job_details_task,linear_comment_show_task;
    public ImageView send_task_comment;
    public CheckBox checkbox_task;
    public TextView edit_comment_task;
    public AdminTaskListAdapterViewHolder(View itemView) {
        super(itemView);
        task_details_list_heading=(TextView)itemView.findViewById(R.id.task_details_list_heading);
        task_message=(TextView)itemView.findViewById(R.id.task_message);
        edit_comment_task=(TextView)itemView.findViewById(R.id.edit_comment_task);
        date_task=(TextView)itemView.findViewById(R.id.date_task);
        comment_job_details_task=(LinearLayout)itemView.findViewById(R.id.comment_job_details_task);
        linear_comment_show_task=(LinearLayout)itemView.findViewById(R.id.linear_comment_show_task);
        send_task_comment=(ImageView)itemView.findViewById(R.id.send_task_comment);
        checkbox_task=(CheckBox)itemView.findViewById(R.id.checkbox_task);
    }
}
    @Override
    public AdminTaskListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_task_list_admin,parent,false);
        return new AdminTaskListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdminTaskListAdapterViewHolder holder, int position) {
        dataStorage=DataStorage.getStore(mContext);

        final TaskDataModel model=commonarealist.get(position);

        String comment=model.getComments();
        holder.checkbox_task.setChecked(model.isChecked());
        if(comment!=null && !comment.equals("")){
            holder.edit_comment_task.setText(comment);
        }

        holder.task_details_list_heading.setText(Heading);

        holder.task_message.setText(model.getTaskDataValue());
        holder.date_task.setText(model.getDate());

//


    }

    @Override
    public int getItemCount() {
        return commonarealist.size();
    }
}
