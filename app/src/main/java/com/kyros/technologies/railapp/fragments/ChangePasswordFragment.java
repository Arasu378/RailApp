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

public class ChangePasswordFragment extends Fragment {
    private View view;
    private EditText old_password,new_password,confirm_password;
    private Button save_password;
    private DataStorage datastorage;
    private ArrayList<LoginModel>loginModelArrayList;
    private String UserProfileID=null;
    private int ProId=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.fragment_change_password,container,false);
        datastorage=DataStorage.getStore(getContext());
        UserProfileID=datastorage.getUserProfileId();
        ProId=Integer.parseInt(UserProfileID);
        save_password=(Button)view.findViewById(R.id.save_password);
        old_password=(EditText) view.findViewById(R.id.old_password);
        new_password=(EditText) view.findViewById(R.id.new_password);
        confirm_password=(EditText) view.findViewById(R.id.confirm_password);
        String userprofilevaluesaved=datastorage.getUserProfile();
        Log.d("Data",userprofilevaluesaved);
        save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpassword=old_password.getText().toString();
                String newpassword=new_password.getText().toString();
                String confirmpassword=confirm_password.getText().toString();
                Toasty.success(getContext(),"saveButton! ", Toast.LENGTH_SHORT).show();

                SavePassword(oldpassword,newpassword,confirmpassword);

            }


        });
        return view;
    }

    private void SavePassword(String oldpassword, String newpassword, String confirmpassword) {
        String value=datastorage.getUserPassword();
        String data=datastorage.getUserProfile();
        if (value.equals(oldpassword)) {
            if (newpassword.equals(confirmpassword)) {
                try{
                    String userprofilevaluesaved=datastorage.getUserProfile();
                    Log.d("Data",userprofilevaluesaved);
                    Gson gsons=new Gson();
                    Type type1=new TypeToken<ArrayList<LoginModel>>(){}.getType();
                    loginModelArrayList=gsons.fromJson(userprofilevaluesaved,type1);
                    for (int i=0; i<loginModelArrayList.size();i++){
                        LoginModel model=loginModelArrayList.get(i);
                        int  UserProfileId=model.getProfileId();
                        if(UserProfileId==ProId){
                            if(newpassword.isEmpty()&&newpassword==null){
                                newpassword=model.getUserPassword();
                            }
                            model.setUserPassword(newpassword);
                          //  loginModelArrayList.add(model);
                        }

                    }
                    Toasty.success(getContext(),"Updated Successfully! ", Toast.LENGTH_SHORT).show();
                    datastorage.putUserPassword(newpassword);

                    try{
                        Gson gson=new Gson();
                        String userlist=gson.toJson(loginModelArrayList);
                        Log.d("Data",userlist);

                        datastorage.putUserProfile(userlist);
                    }catch (Exception e){
                        Log.d("exception_conve_gson",e.getMessage());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toasty.error(getContext(),"New Password and confirm password does not match!",Toast.LENGTH_SHORT).show();

            }

        }else{
            Toasty.error(getContext(),"Old Password does not match!",Toast.LENGTH_SHORT).show();
        }

    }
}
