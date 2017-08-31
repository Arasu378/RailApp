package com.kyros.technologies.railapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.data.DataStorage;
import com.kyros.technologies.railapp.model.LoginModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by kyros on 20-07-2017.
 */

public class ProfileDetailFragment extends Fragment {
    private EditText first_name,mobile_number,email;
    private Button update_user;
    private DataStorage dataStorage;
    private String Name=null;
    private String Email=null;
    private String MobileNumber=null;
    private ArrayList<LoginModel> loginModelArrayList=new ArrayList<LoginModel>();
    private String UserProfileId=null;
    private int ProId=0;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_details, container, false);
        dataStorage=DataStorage.getStore(getContext());
        Name=dataStorage.getUserName();
        UserProfileId=dataStorage.getUserProfileId();
        ProId=Integer.parseInt(UserProfileId);
        Email=dataStorage.getUserEmail();
        MobileNumber=dataStorage.getUserMobileNumber();
        String userprofilevaluesaved=dataStorage.getUserProfile();
        Log.d("Data",userprofilevaluesaved);
        email=(EditText)view.findViewById(R.id.email);
        if(Email!=null){
            email.setText(Email);
        }
        mobile_number=(EditText)view.findViewById(R.id.mobile_number);
        if(MobileNumber!=null){
            mobile_number.setText(MobileNumber);
        }
        first_name=(EditText)view.findViewById(R.id.first_name);
        if(Name!=null){
            first_name.setText(Name);
        }
        update_user=(Button)view.findViewById(R.id.update_user);
        update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  newemaildata=email.getText().toString();
                String newmobilenumberdata=mobile_number.getText().toString();
                String firstnamedata=first_name.getText().toString();

                UpdateUserData(newemaildata,newmobilenumberdata,firstnamedata);

            }
        });
        return view;
    }

    private void UpdateUserData(String newemaildata, String newmobilenumberdata, String firstnamedata) {
        try{
            loginModelArrayList.clear();
            String userprofilevaluesaved=dataStorage.getUserProfile();
            Log.d("Data",userprofilevaluesaved);
            Gson gsons=new Gson();
            Type type1=new TypeToken<ArrayList<LoginModel>>(){}.getType();
            loginModelArrayList=gsons.fromJson(userprofilevaluesaved,type1);
            for(int i=0; i<loginModelArrayList.size();i++){
                LoginModel model=loginModelArrayList.get(i);
                int id=model.getProfileId();
                if(id==ProId){
                    if(newemaildata.isEmpty()&&newemaildata==null){
                        newemaildata=model.getUserEmail();
                    }
                    model.setUserEmail(newemaildata);
                    dataStorage.putUserEmail(newemaildata);
                    email.setText(newemaildata);
                    if(newmobilenumberdata.isEmpty()&&newmobilenumberdata==null){
                        newmobilenumberdata=model.getMobileNumber();
                    }
                    model.setMobileNumber(newmobilenumberdata);
                    dataStorage.putUserMobileNumber(newmobilenumberdata);
                    mobile_number.setText(newmobilenumberdata);

                    if(firstnamedata.isEmpty()&&firstnamedata==null){
                        firstnamedata=model.getUserName();
                    }
                    model.setUserName(firstnamedata);
                    dataStorage.putUserName(firstnamedata);
                    first_name.setText(firstnamedata);

//                    loginModelArrayList.add(model);
                }
            }
            try{
                Gson gson=new Gson();
                String userlist=gson.toJson(loginModelArrayList);
                Log.d("Data",userlist);
                dataStorage.putUserProfile(userlist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }

            Toasty.success(getContext(),"Updated Successfully! ", Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            e.printStackTrace();
            Toasty.error(getContext(),"Not Updated! ", Toast.LENGTH_SHORT).show();

        }

    }
}
