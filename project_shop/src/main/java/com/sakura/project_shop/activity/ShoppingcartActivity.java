package com.sakura.project_shop.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sakura.project_shop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingcartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);

        ListView listView = findViewById(R.id.shoppinglistView);

        int[] imageID = new int[]{
                R.drawable.sp1,
                R.drawable.sp2

        };
        String[] name = new String[]{
                "￥30000",
                "￥29999"
        };

        List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0;i<imageID.length;i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("image",imageID[i]);
            map.put("name",name[i]);
            listItems.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listItems,
                R.layout.mainforshoppingcart,
                new String[]{"name","image"},
                new int[]{
                        R.id.name,
                        R.id.image
                }
        );
        listView.setAdapter(adapter);


    }
}
