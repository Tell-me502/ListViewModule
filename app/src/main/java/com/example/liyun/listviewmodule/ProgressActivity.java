package com.example.liyun.listviewmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgressActivity extends AppCompatActivity {
    public LinearLayout ll_progress_loading;
    public ProgressBar pb_progress_loading;
    public SeekBar sb_progress_loading;
    public SeekBar.OnSeekBarChangeListener onSeekBarChangeListener=new SeekBar.OnSeekBarChangeListener() {
        //滑杆移动
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.e("TAG","滑动滑杆");
            //设置水平滑杆的进度，同步
            pb_progress_loading.setProgress(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {//按下滑杆
            Log.e("TAG","按下滑杆");

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {//离开滑杆
            Log.e("TAG","离开滑杆");
            //获得滑杆的进度
            int progress=seekBar.getProgress();

            //判断seekBar是否达到最大值，
            if (progress==seekBar.getMax()){
                // 如果达到最大值，设置ll_progress_loading不可见
                ll_progress_loading.setVisibility(View.GONE);
            }else {
                //如果没有达到，设置ll_progress_loading可见显示
                ll_progress_loading.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_progress);
        ll_progress_loading=findViewById(R.id.ll_progress_loading);
        pb_progress_loading=findViewById(R.id.pb_progress_loading);
        sb_progress_loading=findViewById(R.id.sb_progress_loading);

        //为SeekBar设置监听
        sb_progress_loading.setOnSeekBarChangeListener(onSeekBarChangeListener);

    }
}
