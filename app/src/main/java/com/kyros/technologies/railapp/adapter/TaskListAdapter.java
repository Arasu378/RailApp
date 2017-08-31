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
import com.kyros.technologies.railapp.model.LoginModel;
import com.kyros.technologies.railapp.model.TaskDataModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by kyros on 20-07-2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskAdapterViewHolder> {
    private Context mContext;
    private String[]SubDomain;
    private String Heading;
    private boolean clicked=true;
    private ArrayList<TaskDataModel> commonarealist;
    private DataStorage dataStorage;


    public TaskListAdapter(Context mContext,String[] SubDomain,String Heading, ArrayList<TaskDataModel> commonarealist){
     this.mContext=mContext;
        this.SubDomain=SubDomain;
        this.Heading=Heading;
        this.commonarealist=commonarealist;
    }
    public class TaskAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView task_details_list_heading,task_message,date_task;
        public LinearLayout comment_job_details_task,linear_comment_show_task;
        public ImageView send_task_comment;
        public CheckBox checkbox_task;
        public EditText edit_comment_task;

        public TaskAdapterViewHolder(View itemView) {
            super(itemView);
            task_details_list_heading=(TextView)itemView.findViewById(R.id.task_details_list_heading);
            task_message=(TextView)itemView.findViewById(R.id.task_message);
            edit_comment_task=(EditText)itemView.findViewById(R.id.edit_comment_task);
            date_task=(TextView)itemView.findViewById(R.id.date_task);
            comment_job_details_task=(LinearLayout)itemView.findViewById(R.id.comment_job_details_task);
            linear_comment_show_task=(LinearLayout)itemView.findViewById(R.id.linear_comment_show_task);
            send_task_comment=(ImageView)itemView.findViewById(R.id.send_task_comment);
            checkbox_task=(CheckBox)itemView.findViewById(R.id.checkbox_task);
        }
    }
    @Override
    public TaskAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_task_list,parent,false);
        return new TaskAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskAdapterViewHolder holder, final int position) {
        dataStorage=DataStorage.getStore(mContext);

        final TaskDataModel model=commonarealist.get(position);

        String comment=model.getComments();
        holder.checkbox_task.setChecked(model.isChecked());
        if(comment!=null && !comment.equals("")){
            clicked=false;
            holder.linear_comment_show_task.setVisibility(View.VISIBLE);
            holder.edit_comment_task.setText(comment);
        }

        holder.task_details_list_heading.setText(Heading);

        holder.task_message.setText(model.getTaskDataValue());
        holder.date_task.setText(model.getDate());
        holder.checkbox_task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toasty.info(mContext,"value : "+isChecked,Toast.LENGTH_SHORT).show();
                switch (Heading){
                    case "Common Area":
                        try{
                            String userprofilevaluesaved=dataStorage.getCommonAreaList();
                            Gson gsons=new Gson();
                            Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                            commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                            commonarealist.get(position).setChecked(isChecked);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try{
                            Gson gson=new Gson();
                            String userlist=gson.toJson(commonarealist);
                            Log.d("UserList",userlist);
                            dataStorage.putCommonAreaList(userlist);
                        }catch (Exception e){
                            Log.d("exception_conve_gson",e.getMessage());
                        }
                        break;
                    case "Passages":
                        try{
                            String userprofilevaluesaved=dataStorage.getPassagesList();
                            Gson gsons=new Gson();
                            Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                            commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                            commonarealist.get(position).setChecked(isChecked);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try{
                            Gson gson=new Gson();
                            String userlist=gson.toJson(commonarealist);
                            Log.d("UserList",userlist);
                            dataStorage.putPassagesList(userlist);
                        }catch (Exception e){
                            Log.d("exception_conve_gson",e.getMessage());
                        }
                        break;
                    case "Tracks":
                        try{
                            String userprofilevaluesaved=dataStorage.getTracksList();
                            Gson gsons=new Gson();
                            Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                            commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                            commonarealist.get(position).setChecked(isChecked);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try{
                            Gson gson=new Gson();
                            String userlist=gson.toJson(commonarealist);
                            Log.d("UserList",userlist);
                            dataStorage.putTracksList(userlist);
                        }catch (Exception e){
                            Log.d("exception_conve_gson",e.getMessage());
                        }
                        break;
                    case "Lifts":
                        try{
                            String userprofilevaluesaved=dataStorage.getLiftsList();
                            Gson gsons=new Gson();
                            Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                            commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                            commonarealist.get(position).setChecked(isChecked);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try{
                            Gson gson=new Gson();
                            String userlist=gson.toJson(commonarealist);
                            Log.d("UserList",userlist);
                            dataStorage.putLiftsList(userlist);
                        }catch (Exception e){
                            Log.d("exception_conve_gson",e.getMessage());
                        }
                        break;
                    case "Circulating Area":
                        try{
                            String userprofilevaluesaved=dataStorage.getCirculatingAreaList();
                            Gson gsons=new Gson();
                            Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                            commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                            commonarealist.get(position).setChecked(isChecked);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try{
                            Gson gson=new Gson();
                            String userlist=gson.toJson(commonarealist);
                            Log.d("UserList",userlist);
                            dataStorage.putCirculatingAreaList(userlist);
                        }catch (Exception e){
                            Log.d("exception_conve_gson",e.getMessage());
                        }
                        break;
                    case "Pavement":
                        try{
                            String userprofilevaluesaved=dataStorage.getPavementsList();
                            Gson gsons=new Gson();
                            Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                            commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                            commonarealist.get(position).setChecked(isChecked);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try{
                            Gson gson=new Gson();
                            String userlist=gson.toJson(commonarealist);
                            Log.d("UserList",userlist);
                            dataStorage.putPavementsList(userlist);
                        }catch (Exception e){
                            Log.d("exception_conve_gson",e.getMessage());
                        }
                        break;
                }
            }
        });
//        holder.task_message.setText(SubDomain[position]);
        holder.send_task_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.linear_comment_show_task.setVisibility(View.GONE);
                clicked=true;
                String value=holder.edit_comment_task.getText().toString();
                if(!value.isEmpty()&&value!=null){
                    switch (Heading){
                        case "Common Area":
                            try{
                            String userprofilevaluesaved=dataStorage.getCommonAreaList();
                            Gson gsons=new Gson();
                            Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                                commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                                commonarealist.get(position).setComments(value);

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                            try{
                                Gson gson=new Gson();
                                String userlist=gson.toJson(commonarealist);
                                Log.d("UserList",userlist);
                                dataStorage.putCommonAreaList(userlist);
                            }catch (Exception e){
                                Log.d("exception_conve_gson",e.getMessage());
                            }
                            break;
                        case "Passages":
                            try{
                                String userprofilevaluesaved=dataStorage.getPassagesList();
                                Gson gsons=new Gson();
                                Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                                commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                                commonarealist.get(position).setComments(value);

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            try{
                                Gson gson=new Gson();
                                String userlist=gson.toJson(commonarealist);
                                Log.d("UserList",userlist);
                                dataStorage.putPassagesList(userlist);
                            }catch (Exception e){
                                Log.d("exception_conve_gson",e.getMessage());
                            }
                            break;
                        case "Tracks":
                            try{
                                String userprofilevaluesaved=dataStorage.getTracksList();
                                Gson gsons=new Gson();
                                Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                                commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                                commonarealist.get(position).setComments(value);

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            try{
                                Gson gson=new Gson();
                                String userlist=gson.toJson(commonarealist);
                                Log.d("UserList",userlist);
                                dataStorage.putTracksList(userlist);
                            }catch (Exception e){
                                Log.d("exception_conve_gson",e.getMessage());
                            }
                            break;
                        case "Lifts":
                            try{
                                String userprofilevaluesaved=dataStorage.getLiftsList();
                                Gson gsons=new Gson();
                                Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                                commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                                commonarealist.get(position).setComments(value);

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            try{
                                Gson gson=new Gson();
                                String userlist=gson.toJson(commonarealist);
                                Log.d("UserList",userlist);
                                dataStorage.putLiftsList(userlist);
                            }catch (Exception e){
                                Log.d("exception_conve_gson",e.getMessage());
                            }
                            break;
                        case "Circulating Area":
                            try{
                                String userprofilevaluesaved=dataStorage.getCirculatingAreaList();
                                Gson gsons=new Gson();
                                Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                                commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                                commonarealist.get(position).setComments(value);

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            try{
                                Gson gson=new Gson();
                                String userlist=gson.toJson(commonarealist);
                                Log.d("UserList",userlist);
                                dataStorage.putCirculatingAreaList(userlist);
                            }catch (Exception e){
                                Log.d("exception_conve_gson",e.getMessage());
                            }
                            break;
                        case "Pavement":
                            try{
                                String userprofilevaluesaved=dataStorage.getPavementsList();
                                Gson gsons=new Gson();
                                Type type1=new TypeToken<ArrayList<TaskDataModel>>(){}.getType();
                                commonarealist=gsons.fromJson(userprofilevaluesaved,type1);
                                commonarealist.get(position).setComments(value);

                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            try{
                                Gson gson=new Gson();
                                String userlist=gson.toJson(commonarealist);
                                Log.d("UserList",userlist);
                                dataStorage.putPavementsList(userlist);
                            }catch (Exception e){
                                Log.d("exception_conve_gson",e.getMessage());
                            }
                            break;
                    }



                }


                Toasty.success(mContext,"Comment sent succesfully!", Toast.LENGTH_SHORT).show();

            }
        });
        holder.comment_job_details_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked){
                    clicked=false;
                    holder.linear_comment_show_task.setVisibility(View.VISIBLE);

                }else{
                    clicked=true;
                    holder.linear_comment_show_task.setVisibility(View.GONE);

                }
                Toasty.info(mContext,"Clicked !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return commonarealist.size();
    }
}
