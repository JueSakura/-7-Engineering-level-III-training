package com.sakura.project_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sakura.project_shop.activity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        // 获取前台的id
        ListView listView = findViewById(R.id.listView);
        // 定义保存图片id的数组 ，并初始化
        int[] imageID = new int[]{
                R.drawable.getamessage,
                R.drawable.getamessage};
        // 定义保存名字的数组，并初始化
        String[] names = new String[]{
                "Your express has been shipped",
                "Your courier has been delivered",
        };
        // 创建List集合
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        // 把图片id和名字都存放到map中，并添加到list集合里面
        for (int i = 0; i < imageID.length; i++) {
            // 实例化Map对象
            Map<String, Object> map = new HashMap<String, Object>();
            // 下面两行代码表示将所有的头像和姓名全部添加到map中。
            map.put("image", imageID[i]);
            map.put("name", names[i]);
            // 将map添加到list集合里。
            listItems.add(map);
        }
        // 创建一个适配器
                SimpleAdapter adapter = new SimpleAdapter(
                this,
                listItems,
                R.layout.mainforusermessage,
                new String[]{"name", "image"},
                new int[]{
                        R.id.name,
                        R.id.image
                });
        // 将适配器和ListView关联
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取前台选择的值
                Map<String,Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(UserMessageActivity.this, map.get("name").toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
