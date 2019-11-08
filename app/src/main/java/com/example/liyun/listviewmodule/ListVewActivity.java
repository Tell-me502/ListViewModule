package com.example.liyun.listviewmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListVewActivity extends AppCompatActivity {
    private ListView lv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vew);
        //ArrayAdapter适配器
        /*//获取ListView布局
        lv_content=findViewById(R.id.lv_content);
        //创建Item数据
        String[] str={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
        //创建适配器
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.list_view_item,str);
        //设置listView的Item布局和数据
        lv_content.setAdapter(adapter);*/
        //SimpleAdapter适配器
        //获取ListView布局
        lv_content=findViewById(R.id.lv_content);
        //创建Item数据
        List<Map<String,Object>> data=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f1);
        map.put("name","name--1");
        map.put("content","content--1");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f2);
        map.put("name","name--2");
        map.put("content","content--2");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f3);
        map.put("name","name--3");
        map.put("content","content--3");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f4);
        map.put("name","name--4");
        map.put("content","content--4");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f5);
        map.put("name","name--5");
        map.put("content","content--5");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f6);
        map.put("name","name--6");
        map.put("content","content--6");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f7);
        map.put("name","name--7");
        map.put("content","content--7");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f8);
        map.put("name","name--8");
        map.put("content","content--8");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f9);
        map.put("name","name--9");
        map.put("content","content--9");
        data.add(map);
        map=new HashMap<String, Object>();
        map.put("icon",R.drawable.f10);
        map.put("name","name--10");
        map.put("content","content--10");
        data.add(map);

        //map对象中的key数组，用于得到对应的value
        String[] str1={"icon","name","content"};
        //Item中的子View的id数组，用于适配对应的值
        int[] i={R.id.iv_icon,R.id.tv_name,R.id.tv_content};
        //创建适配器
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.simple_item,str1,i);
        //设置listView的Item布局和数据
        lv_content.setAdapter(adapter);
    }
}
