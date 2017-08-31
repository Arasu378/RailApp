package com.kyros.technologies.railapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.adapter.LandingAdapter;
import com.kyros.technologies.railapp.data.DataStorage;
import com.kyros.technologies.railapp.model.LoginModel;
import com.kyros.technologies.railapp.model.TaskHome;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import es.dmoral.toasty.Toasty;

/**
 * Created by kyros on 19-07-2017.
 */

public class LandingPageFragment extends Fragment{
    private View view;
    private RecyclerView recycler_data;
    private LandingAdapter adapter;
    private TextView date_landing;
    private ArrayList<TaskHome>taskHomeArrayList;
    private DataStorage dataStorage;
    private int ProId=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_landing, container, false);
        dataStorage=DataStorage.getStore(getContext());
        recycler_data=(RecyclerView)view.findViewById(R.id.recycler_data);
        date_landing=(TextView)view.findViewById(R.id.date_landing);
        ProId=Integer.parseInt(dataStorage.getUserProfileId());
        try{
            String value=dataStorage.getTaskHome();
            if(value==null){
                Toasty.info(getContext(),"List is Empty! ", Toast.LENGTH_SHORT).show();
            }else{
                Gson gsons=new Gson();
                Type type1=new TypeToken<ArrayList<TaskHome>>(){}.getType();
                taskHomeArrayList=gsons.fromJson(value,type1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        DateFormat df = new SimpleDateFormat("dd-MM-yyy");
//        DateFormat df = new SimpleDateFormat("yyy-MM-dd");
        final String now = df.format(new Date());
        date_landing.setText(now);
        adapter=new LandingAdapter(getContext(),taskHomeArrayList,ProId);
        RecyclerView.LayoutManager layoutManagersecond=new LinearLayoutManager(getContext());
        recycler_data.setLayoutManager(layoutManagersecond);
        recycler_data.setItemAnimator(new DefaultItemAnimator());
        recycler_data.setAdapter(adapter);
        return  view;

    }
}
