package com.sakura.project_shop.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.sakura.project_shop.R;
import com.sakura.project_shop.entity.UserDetail;
import com.sakura.project_shop.utils.PostUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UserDetailActivity extends AppCompatActivity {
    EditText name = null;
    EditText age = null;
    EditText sex = null;
    EditText phone = null;
    EditText address = null;
    EditText idcard = null;
    EditText birthday = null;

    ////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ////////////////////////////////////
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        sex = findViewById(R.id.sex);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        idcard = findViewById(R.id.idcard);
        birthday = findViewById(R.id.birthday);

    }


    public void enter(View view){



        String cname = name.getText().toString();
        String cage = age.getText().toString();
        String csex = sex.getText().toString();
        String cphone = phone.getText().toString();
        String caddress = address.getText().toString();
        String cidcard = idcard.getText().toString();
        String cbirthday = birthday.getText().toString();





        final UserDetail userdetail = new UserDetail();

        userdetail.setName(cname);
        userdetail.setAge(cage);
        userdetail.setSex(csex);
        userdetail.setPhone(cphone);
        userdetail.setAddress(caddress);
        userdetail.setIdcard(cidcard);
        userdetail.setBirthday(cbirthday);

        new Thread(){
            @Override
            public void run() {

                String data="";
                try {
                    data = "&name="+ URLEncoder.encode(userdetail.getName(), "UTF-8")+
                            "&age="+ URLEncoder.encode(userdetail.getAge()+"", "UTF-8")+
                            "&sex="+ URLEncoder.encode(userdetail.getSex()+"", "UTF-8")+
                            "&phone="+ URLEncoder.encode(userdetail.getPhone()+"", "UTF-8")+
                            "&address="+ URLEncoder.encode(userdetail.getAddress()+"", "UTF-8")+
                            "&idcard="+ URLEncoder.encode(userdetail.getIdcard()+"", "UTF-8")+
                            "&birthday="+ URLEncoder.encode(userdetail.getBirthday()+"", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String request = PostUtil.Post("UserDetailServlet",data);

                int msg = 0;
                if(request.equals("成功")){
                    msg = 2;
                }
                //已存在
                if(request.equals("已存在")){
                    msg = 1;
                }

                hand.sendEmptyMessage(msg);

            }
        }.start();


    }
    final Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"该账号已经存在，请换一个账号",Toast.LENGTH_LONG).show();

            }
            if(msg.what == 2)
            {
                //startActivity(new Intent(getApplication(),MainActivity.class));

                Intent intent = new Intent();
                //将想要传递的数据用putExtra封装在intent中
                intent.putExtra("a","注册");
                Intent intent1 = new Intent(UserDetailActivity.this,MainActivity.class);
                startActivity(intent1);
                setResult(RESULT_CANCELED,intent);
                finish();
            }

        }
    };
}
