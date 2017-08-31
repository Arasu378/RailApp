package com.kyros.technologies.railapp.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyros.technologies.railapp.R;
import com.kyros.technologies.railapp.data.DataStorage;
import com.kyros.technologies.railapp.model.LoginModel;
import com.kyros.technologies.railapp.model.TaskHome;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class SignInActivity extends AppCompatActivity  {
    private EditText email_address,password;
    private Button login_user,register_user;
    private ArrayList<LoginModel>loginModelArrayList=new ArrayList<LoginModel>();
    private ArrayList<TaskHome>taskHomeArrayList=new ArrayList<TaskHome>();
    private AlertDialog forget_dialog;
    private TextView forget_password_login;
    private DataStorage dataStorage;
    private String UserProfile=null;
    private String[] UserNames={"Lionel Amalan","Admin"};
    private String [] UserEmail={"lionel@gmail.com","admin@gmail.com"};
    private String[] MobileNumber={"1234567890","9876543210"};
    private String[] Password={"123456","123456"};
    private int[] userProfileId={1,2};
    private String TaskHome=null;
    private String[]TaskHeaderString={"Road Level","Concourse","Platform Level"};
    private String[] TaskSubHeaderOneString={"Pavements","Lifts","Passages"};
    private String [] TaskSubHdeaderTwoString={"Circulating Area","Common Area","Tracks"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_sign_in);
        dataStorage=DataStorage.getStore(getApplicationContext());
        UserProfile=dataStorage.getUserProfile();
        TaskHome=dataStorage.getTaskHome();
        if(UserProfile==null){
            for(int i=0; i<2;i++){
                LoginModel loginModel=new LoginModel();
                loginModel.setProfileId(userProfileId[i]);
                loginModel.setUserName(UserNames[i]);
                loginModel.setUserEmail(UserEmail[i]);
                loginModel.setUserPassword(Password[i]);
                loginModel.setMobileNumber(MobileNumber[i]);
                loginModelArrayList.add(loginModel);
            }
            try{
                Gson gson=new Gson();
                String userlist=gson.toJson(loginModelArrayList);
                dataStorage.putUserProfile(userlist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }

        }else{
            try{
                loginModelArrayList.clear();
                String userprofilevaluesaved=dataStorage.getUserProfile();
                    Gson gsons=new Gson();
                    Type type1=new TypeToken<ArrayList<LoginModel>>(){}.getType();
                loginModelArrayList=gsons.fromJson(userprofilevaluesaved,type1);

                }catch (Exception e){
                    e.printStackTrace();
                }
        }
        if(TaskHome==null){
            for(int i=0; i<3; i++){
                com.kyros.technologies.railapp.model.TaskHome taskHome=new TaskHome();
                taskHome.setTaskHeader(TaskHeaderString[i]);
                taskHome.setTaskSubHeaderOne(TaskSubHeaderOneString[i]);
                taskHome.setTaskSubHeaderTwo(TaskSubHdeaderTwoString[i]);
                taskHomeArrayList.add(taskHome);
            }
            try{
                Gson gson=new Gson();
                String tasklist=gson.toJson(taskHomeArrayList);
                dataStorage.putTaskHome(tasklist);
            }catch (Exception e){
                Log.d("exception_conve_gson",e.getMessage());
            }
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        email_address=(EditText)findViewById(R.id.email_address);
        register_user=(Button)findViewById(R.id.register_user);
        email_address.requestFocus();
        email_address.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {
                // TODO Auto-generated method stub
                if (view.getId() ==R.id.email_address) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction()&MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });
        register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
        password=(EditText)findViewById(R.id.password);
        password.requestFocus();
        login_user=(Button)findViewById(R.id.login_user);
        email_address.addTextChangedListener(mWatcher);
        password.addTextChangedListener(mWatcher);
        checkValidation();
        login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=email_address.getText().toString();
                String pass=password.getText().toString();

                gotoLandingPage(email,pass);

            }
        });
        forget_password_login=(TextView)findViewById(R.id.forget_password_login);
        forget_password_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpopup();
            }
        });

    }
    private void checkValidation() {
        if ((TextUtils.isEmpty((email_address.getText())) || (TextUtils.isEmpty(password.getText())))) {
            login_user.setEnabled(false);
            login_user.setBackground(getResources().getDrawable(R.drawable.buttoncorner_grey));

        } else {
            login_user.setEnabled(true);
            login_user.setBackground(getResources().getDrawable(R.drawable.button_background));

        }
    }

    private final TextWatcher mWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkValidation();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        closepopup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closepopup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataStorage=DataStorage.getStore(getApplicationContext());
        UserProfile=dataStorage.getUserProfile();
    }

    private void closepopup(){
        if(forget_dialog!=null&& forget_dialog.isShowing()){
            forget_dialog.dismiss();
        }
    }
    private void openpopup() {
        if(forget_dialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(SignInActivity.this);
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.forgot_password_layout,null);
            builder.setView(view);
            final EditText email_forget_password=(EditText)view.findViewById(R.id.email_forget_password);
            TextView back_forget=(TextView)view.findViewById(R.id.back_forget);
            TextView reset_forget=(TextView)view.findViewById(R.id.reset_forget);
            back_forget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closepopup();
                }
            });
            reset_forget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email=email_forget_password.getText().toString();
                    if(!email.isEmpty()&&email!=null){


                    }else{
                    }
                }
            });
            forget_dialog=builder.create();
            forget_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            forget_dialog.setCancelable(false);
            forget_dialog.setCanceledOnTouchOutside(false);

        }
        forget_dialog.show();

    }
    private void gotoLandingPage(String email, String pass) {
                    for(int i=0; i<loginModelArrayList.size();i++){
                        LoginModel loginModel=loginModelArrayList.get(i);
                        String emailsaved=loginModel.getUserEmail();
                        Log.d("Email saved : ",emailsaved);
                        if(emailsaved.equals(email)){
                            String passsaved=loginModel.getUserPassword();
                            if(passsaved.equals(pass)){
                                int UserProfileId=loginModel.getProfileId();

                                if(UserProfileId==1){
                                    dataStorage.putUserProfileId(String.valueOf(UserProfileId));
                                    dataStorage.putUserPassword(passsaved);
                                    dataStorage.putUserName(loginModel.getUserName());
                                    dataStorage.putUserEmail(loginModel.getUserEmail());
                                    dataStorage.putUserMobileNumber(loginModel.getMobileNumber());
                                    Intent is=new Intent(SignInActivity.this,LandingActivity.class);
                                    is.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(is);
                                    Log.d("email : ",""+email);
                                }else if(UserProfileId==2){
                                    dataStorage.putUserProfileId(String.valueOf(UserProfileId));
                                    dataStorage.putUserPassword(passsaved);
                                    dataStorage.putUserName(loginModel.getUserName());
                                    dataStorage.putUserEmail(loginModel.getUserEmail());
                                    dataStorage.putUserMobileNumber(loginModel.getMobileNumber());
                                    Intent is=new Intent(SignInActivity.this,AdminActivity.class);
                                    is.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(is);
                                    Log.d("email : ",""+email);
                                }

                            }else{
                                Toasty.error(getApplicationContext(),"Password does not match! ", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toasty.error(getApplicationContext(),"Email does not Exist ! ", Toast.LENGTH_SHORT).show();

                        }
                    }



    }


}
