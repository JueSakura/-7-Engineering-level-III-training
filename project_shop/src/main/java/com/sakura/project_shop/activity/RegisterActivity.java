package com.sakura.project_shop.activity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sakura.project_shop.R;
import com.sakura.project_shop.utils.SQlite;


public class RegisterActivity extends AppCompatActivity {

    private SQlite mSQlite;
    private Button reday;
    private Button back;
    private EditText username;
    private EditText userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reday = findViewById(R.id.reday);
        back = findViewById(R.id.back);
        username = findViewById(R.id.userName);
        userpassword =findViewById( R.id.userpassword);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        reday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    mSQlite.add(name,password);
                    Intent intent1 = new Intent(RegisterActivity.this,UserDetailActivity.class);
                    startActivity(intent1);
                    finish();
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }else {Toast.makeText(RegisterActivity.this,"信息不完备，注册失败",Toast.LENGTH_SHORT).show();}
            }
        });
        mSQlite = new SQlite(RegisterActivity.this);
    }
}