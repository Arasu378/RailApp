package com.kyros.technologies.railapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyros.technologies.railapp.R;

/**
 * Created by kyros on 20-07-2017.
 */

public class NotificationFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.fragment_notification,container,false);
        return view;
    }
}
