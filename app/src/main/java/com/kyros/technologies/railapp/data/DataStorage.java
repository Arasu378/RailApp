package com.kyros.technologies.railapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by kyros on 21-07-2017.
 */

public class DataStorage {
    private static DataStorage store;
    private final SharedPreferences sp;

    private DataStorage(Context context){
        String filename = "data";
        sp=context.getApplicationContext().getSharedPreferences(filename,0);
    }
    public static DataStorage getStore(Context context){
        if(store==null){
            store=new DataStorage(context);
        }
        return store;
    }
    public String getUserProfile(){
        return sp.getString("UserProfile",null);
    }
    public void putUserProfile(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("UserProfile", value);
        editor.commit();
    }
    public String getTaskHome(){
        return sp.getString("TaskHome",null);
    }
    public void putTaskHome(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("TaskHome", value);
        editor.commit();
    }
    public String getUserProfileId(){
        return sp.getString("UserProfileId",null);
    }
    public void putUserProfileId(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("UserProfileId", value);
        editor.commit();
    }
    public String getUserPassword(){
        return sp.getString("UserPassword",null);
    }
    public void putUserPassword(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("UserPassword", value);
        editor.commit();
    }
    public String getUserEmail(){
        return sp.getString("UserEmail",null);
    }
    public void putUserEmail(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("UserEmail", value);
        editor.commit();
    }
    public String getUserMobileNumber(){
        return sp.getString("UserMobileNumber",null);
    }
    public void putUserMobileNumber(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("UserMobileNumber", value);
        editor.commit();
    }
    public String getUserName(){
        return sp.getString("UserName",null);
    }
    public void putUserName(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("UserName", value);
        editor.commit();
    }
    public String getTaskDataList(){
        return sp.getString("TaskDataList",null);
    }
    public void putTaskDataList(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("TaskDataList", value);
        editor.commit();
    }
    public String getPavementsList(){
        return sp.getString("PavementsList",null);
    }
    public void putPavementsList(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("PavementsList", value);
        editor.commit();
    }
    public String getCirculatingAreaList(){
        return sp.getString("CirculatingAreaList",null);
    }
    public void putCirculatingAreaList(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("CirculatingAreaList", value);
        editor.commit();
    }
    public String getLiftsList(){
        return sp.getString("LiftsList",null);
    }
    public void putLiftsList(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("LiftsList", value);
        editor.commit();
    }
    public String getCommonAreaList(){
        return sp.getString("CommonAreaList",null);
    }
    public void putCommonAreaList(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("CommonAreaList", value);
        editor.commit();
    }
    public String getPassagesList(){
        return sp.getString("PassagesList",null);
    }
    public void putPassagesList(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("PassagesList", value);
        editor.commit();
    }
    public String getTracksList(){
        return sp.getString("TracksList",null);
    }
    public void putTracksList(String value){
        SharedPreferences.Editor editor;
        editor=sp.edit();
        editor.putString("TracksList", value);
        editor.commit();
    }
}
