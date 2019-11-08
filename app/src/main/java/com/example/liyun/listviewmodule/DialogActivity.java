package com.example.liyun.listviewmodule;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    /**
     * 显示一般的AlertDialog
     * @param v
     */
    public void showAD(View v){
        //方法链调用
        new AlertDialog.Builder(this)
                .setTitle("删除数据") //设置标题
                .setMessage("确定删除数据吗？") //设置内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this,"确定删除",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this,"取消删除",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
    /**
     * 显示单选列表AlertDialog
     */
    public void showLD(View view){
        final String[] items={"红","蓝","绿","灰"};//final变量再方法执行完后，还存在
        new AlertDialog.Builder(this)
                .setTitle("选择背景颜色")
                .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {//checkedItem指的是初始选择的列表下标
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//which就是选中的position
                        //提示颜色
                        Toast.makeText(DialogActivity.this,items[which],Toast.LENGTH_SHORT).show();
                        //移除dialog
                        dialog.dismiss();
                    }
                })
                .show();
    }
    /**
     * 显示自定义AlertDialog
     */
    public void showCD(View v){
        //动态加载布局文件，得到对应的View对象
        View view=View.inflate(this,R.layout.dialog_view,null);
        //问题1：view的真实类型？是布局文件根标签的类型，包含子View的对象
        //问题2：如何得到一个View的子View？view.findViewById(id);
        //findViewById(id);是在setContentView();中的View中找
        final EditText et_name=view.findViewById(R.id.et_dialog_name);
        final EditText et_pwd=view.findViewById(R.id.et_dialog_pwd);
        new AlertDialog.Builder(DialogActivity.this)
                .setView(view)//设置动态加载的布局
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取输入框的数据
                        String name=et_name.getText().toString();
                        String pwd=et_pwd.getText().toString();
                        //提示
                        Toast.makeText(DialogActivity.this,name+":"+pwd,Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
     * 显示圆形进度条ProgressDialog
     * @param v
     */
    public void showPD(View v){//回调方法都在主线程执行的
        //创建ProgressDialog对象
        final ProgressDialog dialog=ProgressDialog.show(DialogActivity.this,"加载数据","数据加载中...");
        //模拟一个加载长时间的工作
        //耗时操作都得在子线程中执行，new Thread();
        new Thread(){
            @Override
            public void run() {//子线程
                super.run();
                for (int i=0;i<20;i++){
                    try {//捕获异常
                        //休眠休息2秒
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //移除ProgressDialog对象,for循环执行完后才执行
                dialog.dismiss();//dismiss();内部调用了handler处理器，实现在主线程中执行的
                //runOnUiThread();是在子线程上执行的。
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {//在主线程上执行，那为什么与上面的不同呢？前提条件：需要new Runnable内的run方法
                        //显示Taost，不能再子线程中直接更新UI线程
                        Toast.makeText(DialogActivity.this,"加载完成！！！",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }.start();


    }
    /**
     * 显示水平进度条ProgressDialog
     * @param v
     */
    public void showPD1(View v){
        //创建ProgressDialog 对象
        final ProgressDialog dialog=new ProgressDialog(DialogActivity.this);
        //设置加载进度条的样式
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        //显示ProgressDialog
        dialog.show();
        //启动子线程，加载数据，并显示进度，加载完成，移除Dialog
        new Thread(){
            @Override
            public void run() {
                super.run();
                //设置进度条的进度
                int count=20;
                //设置最大进度
                dialog.setMax(count);
                for (int i=0;i<count;i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dialog.setProgress(dialog.getProgress()+1);
                }
                //移除ProgressDialog
                dialog.dismiss();
            }
        }.start();

    }

    /**
     * 显示日期DatePickerDialog
     * @param v
     */
    public void showDPD(View v){
        //创建日历时间对象
        Calendar calendar=Calendar.getInstance();
        //得到当前的年月日
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        //创建对象
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //月份是从0-11的，所以month要加1
                Toast.makeText(DialogActivity.this,year+"_"+(month+1)+"_"+dayOfMonth,Toast.LENGTH_SHORT).show();
            }
        },year,month,day).show();
    }
    /**
     * 显示时间TimePickerDialog
     * @param v
     */
    public void showTPD(View v){
        //创建日历时间对象
        Calendar calendar=Calendar.getInstance();
        //得到当前的时间
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        Toast.makeText(DialogActivity.this,hour+":"+minute,Toast.LENGTH_SHORT).show();
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(DialogActivity.this,hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();
            }
        },hour,minute,true).show();
    }
}
