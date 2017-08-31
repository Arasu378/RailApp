package com.kyros.technologies.railapp.fragments;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.data.DataStorage;

/**
 * Created by kyros on 19-07-2017.
 */

public class ProfileFragment extends Fragment {
    private View view;
    private FrameLayout frame_container;
    private DataStorage dataStorage;
    private TextView name_profile,email_profile_home;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        dataStorage=DataStorage.getStore(getContext());
        frame_container=(FrameLayout)view.findViewById(R.id.frame_container);
        RadioGroup radioGroup1 = (RadioGroup) view.findViewById(R.id.radio_group);
        email_profile_home=(TextView)view.findViewById(R.id.email_profile_home);
        name_profile=(TextView)view.findViewById(R.id.name_profile);
        String name=dataStorage.getUserName();
        String email=dataStorage.getUserEmail();
        if(name!=null){
            name_profile.setText(name);
        }
        if(email!=null){
            email_profile_home.setText(email);
        }

        radioGroup1.check(R.id.change_profile);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.change_profile:
                        ProfileDetailFragment fragprofile=new ProfileDetailFragment();
                        FragmentTransaction fragmentTrans1=
                                getActivity(). getSupportFragmentManager().beginTransaction();
                        fragmentTrans1.replace(R.id.frame_container,fragprofile);
                        fragmentTrans1.addToBackStack(null);
                        fragmentTrans1.commit();
                        break;
                    case R.id.change_password:
                        ChangePasswordFragment fragpass=new ChangePasswordFragment();
                        FragmentTransaction fragmentTrans=
                                getActivity(). getSupportFragmentManager().beginTransaction();
                        fragmentTrans.replace(R.id.frame_container,fragpass);
                        fragmentTrans.addToBackStack(null);
                        fragmentTrans.commit();
                        break;
                }
            }
        });


        ProfileDetailFragment fragprofile=new ProfileDetailFragment();
        FragmentTransaction fragmentTrans1=
               getActivity(). getSupportFragmentManager().beginTransaction();
        fragmentTrans1.replace(R.id.frame_container,fragprofile);
        fragmentTrans1.addToBackStack(null);
        fragmentTrans1.commit();


        return view;



    }
}
