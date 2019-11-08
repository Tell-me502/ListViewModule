package com.example.liyun.listviewmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListVewActivity_SimpleAdapter extends AppCompatActivity {
    private ListView lv_content;
    public List<ShopInfo> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vew);

        //BaseAdapter适配器
        //获取ListView布局
        lv_content=findViewById(R.id.lv_content);
        //创建Item数据
        data=new ArrayList<ShopInfo>();
        data.add(new ShopInfo(R.drawable.f1,"name--1","content--1"));
        data.add(new ShopInfo(R.drawable.f2,"name--2","content--2"));
        data.add(new ShopInfo(R.drawable.f3,"name--3","content--3"));
        data.add(new ShopInfo(R.drawable.f4,"name--4","content--4"));
        data.add(new ShopInfo(R.drawable.f5,"name--5","content--5"));
        data.add(new ShopInfo(R.drawable.f6,"name--6","content--6"));
        data.add(new ShopInfo(R.drawable.f7,"name--7","content--7"));
        data.add(new ShopInfo(R.drawable.f8,"name--8","content--8"));
        data.add(new ShopInfo(R.drawable.f9,"name--9","content--9"));
        data.add(new ShopInfo(R.drawable.f10,"name--10","content--10"));

        //创建适配器
        final MyAdapter adapter=new MyAdapter();
        //设置listView的Item布局和数据
        lv_content.setAdapter(adapter);
        //给List View的Item设置点击监听事件
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             *
             * @param parent   List
             * @param view  当前行的Item视图对象
             * @param position  当前行的下标
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str=data.get(position).getName();
                Toast.makeText(ListVewActivity_SimpleAdapter.this,str,Toast.LENGTH_LONG).show();
            }
        });
        //给List View设置长按监听
        lv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //删除当前行的数据
                data.remove(position);
                //显示列表，会重新加载视图
                //lv_content.setAdapter(adapter);

                //通知更新列表，会使用所有缓存的Item的视图。更高效
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private class MyAdapter extends BaseAdapter{
        //返回集合数据的数量
        @Override
        public int getCount() {

            return data.size();
        }
        //返回指定下标对应的数据的对象
        @Override
        public Object getItem(int position) {

            return data.get(position);
        }
        //
        @Override
        public long getItemId(int position) {

            return 0;
        }

        /*返回指定下标所对应的Item的View布局对象
         * position:对象下标
         * convertView:可复用的缓存Item视图对象，前n+1个为空
         * parent:Item的ListView布局
         * */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果convertView没有缓存的Item视图对象
            if(convertView==null){
                //加载Item布局，得到View对象
                convertView=View.inflate(ListVewActivity_SimpleAdapter.this,R.layout.simple_item,null);
            }
            //根据position设置对应的数据
            //得到当前行的对象
            ShopInfo shopInfo=data.get(position);
            //得到子View对象，并设置数据
            ImageView iv_icon=convertView.findViewById(R.id.iv_icon);
            TextView tv_name=convertView.findViewById(R.id.tv_name);
            TextView tv_content=convertView.findViewById(R.id.tv_content);
            //设置数据
            iv_icon.setImageResource(shopInfo.getIcon());
            tv_name.setText(shopInfo.getName());
            tv_content.setText(shopInfo.getContent());

            return convertView;
        }
    }
}
