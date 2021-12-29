package com.sakura.project_shop.activity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.sakura.project_shop.R;
import com.sakura.project_shop.UserMessageActivity;
import com.sakura.project_shop.entity.User;
import com.sakura.project_shop.utils.SQlite;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;  //利用SharedPreferences
    private SharedPreferences .Editor editor;
    private CheckBox remember;  //定义记住密码


    private SQlite mSQlite;
    private EditText username;
    private EditText userpassword;
    private Button login;
    private Button register;
    private User view;
    private TextView forgetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        username = findViewById(R.id.userName);
        userpassword = findViewById(R.id.userpassword);
        remember = (CheckBox) findViewById(R.id.remember);

        forgetpassword= findViewById(R.id.forgetpassword);


        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this, UserMessageActivity.class);
                startActivity(intent5);
                finish();
            }
        });



        boolean isRemember = preferences.getBoolean("remember_password",false);
        if (isRemember){
            String Name = preferences.getString("username","");
            String Password = preferences.getString("password","");
            username.setText(Name);
            userpassword.setText(Password);
            remember.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mSQlite.getAllDATA();
                    boolean userdata = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user= data.get(i);   //可存储账号数量
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            userdata = true;
                            break;
                        } else {
                            userdata = false;
                        }
                    }
                    if (userdata) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        editor = preferences.edit();
                        if(remember.isChecked()){
                            editor.putBoolean("remember_password",true);
                            editor.putString("username",name);
                            editor.putString("password",password);
                        }else{editor.clear();}
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this, ChoiceActivity.class);
                        intent.putExtra("username",name);
                        intent.putExtra("password",password);  //展示账号密码功能
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mSQlite = new SQlite(MainActivity.this);
    }
}
