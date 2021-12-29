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
import com.sakura.project_shop.entity.User;
import com.sakura.project_shop.utils.SQlite;

import java.util.ArrayList;

public class PasswordActivity extends AppCompatActivity {

    private SQlite mSQlite;
    private EditText newpassword;
    private Button reday;
    private Button back;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        reday = findViewById(R.id.reday);
        back = findViewById(R.id.back);
        newpassword = findViewById(R.id.newpassword);
        username = findViewById(R.id.userName);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        reday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass = newpassword.getText().toString().trim();
                String name = username.getText().toString().trim();
                if (TextUtils.isEmpty(newpass)||TextUtils.isEmpty(name)){
                    Toast.makeText(PasswordActivity.this,"选项不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<User> data = mSQlite.getAllDATA();
                boolean userdata = false;
                for (int i = 0; i < data.size(); i++) {
                    User user = data.get(i);   //可存储账号数量
                    if (name.equals(user.getName())) {
                        userdata = true;
                        break;
                    } else {
                        userdata = false;
                    }
                }
                if(userdata){
                    mSQlite.updata(name,newpass);
                    Toast.makeText(PasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(PasswordActivity.this,MainActivity.class);
                    startActivity(intent1);
                    finish();
                }else {
                    Toast.makeText(PasswordActivity.this,"用户名或旧密码不正确",Toast.LENGTH_SHORT).show();
                }



            }
        });
        mSQlite = new SQlite(PasswordActivity.this);

    }
}