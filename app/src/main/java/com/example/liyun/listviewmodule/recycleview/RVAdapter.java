package com.example.liyun.listviewmodule.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ProjectLogViewHolder> {
    public static final int LOGPERMISSION=0x10;
    public static final int PROJECT=0x11;
    public static final int PROJECTLOG=0x12;
    public int type;
    public Context mContext;
    List<Object> datas;
    public RVAdapter(Context context,List<Object> datas,int type){
        this.mContext=context;
        this.datas=datas;
        this.type=type;
    }

    @NonNull
    @Override
    public ProjectLogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(getLayoutRes(),viewGroup,false);
        return new ProjectLogViewHolder(view);
    }

    public int getLayoutRes(){
        int layoutRes = 0;
        switch (type){
            case LOGPERMISSION:
                //返回权限设置的viewholder布局
                break;
            case PROJECT:
            case PROJECTLOG:
                //返回工程列表和工程日志列表的布局。这两个列表的布局是一样的，只是数据不一样
                break;
        }
        return layoutRes;
    }

    int position;

    @Override
    public void onBindViewHolder(@NonNull ProjectLogViewHolder projectLogViewHolder, int i) {
        position=i;
        projectLogViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    //把项目信息传入处理
                    onClickListener.onItemClick(datas.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    public class ProjectLogViewHolder extends RecyclerView.ViewHolder{

        public ProjectLogViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    //点击监听接口
    public interface OnItemClickListener{
        void onItemClick(Object object);
    }
    private OnItemClickListener onClickListener;
    //对外公开方法
    public void setOnClickListener(OnItemClickListener clickListener){
        this.onClickListener=clickListener;
    }
}
