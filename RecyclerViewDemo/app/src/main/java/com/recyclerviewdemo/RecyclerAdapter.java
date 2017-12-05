package com.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

/**
 * Created by Administrator on 2017/3/6.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private List<ListDataBean> listDatas;
    private View.OnClickListener listener;
    public RecyclerAdapter(List<ListDataBean> listDatas)
    {
        this.listDatas = listDatas;
    }

    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View childView = null;
        childView = inflater.inflate(R.layout.recyclerview_itemview, parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(childView);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.RecyclerHolder holder, int position)
    {
        ListDataBean listDataBean = listDatas.get(position);
        holder.imageView.setImageResource(listDataBean.getImageUrl());
        if(listDataBean.getImgHeight()!=0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, listDataBean.getImgHeight());
            holder.itemView.setLayoutParams(layoutParams);
        }
        holder.title.setText(listDataBean.getTitle());
        holder.subTitle.setText(listDataBean.getSubTitle());
        holder.itemView.setOnClickListener(listener);
    }
    public void setOnItemClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public int getItemCount()
    {
        if(listDatas==null)
            return 0;
        return listDatas.size();
    }

    //TODO 创建ViewHolder
    class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView subTitle;

        public RecyclerHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title);
            subTitle = (TextView) itemView.findViewById(R.id.subTitle);
        }
    }


    //TODO 移动item

    public void moveItem(int fromPosition,int toPosition)
    {
        //TODO 做数据交换
        if(fromPosition<toPosition)
        {
            for(int index=fromPosition;index<toPosition;index++)
            {
                Collections.swap(listDatas,index,index+1);
            }
        }else{
            for(int index=fromPosition;index>toPosition;index--)
            {
                Collections.swap(listDatas,index,index-1);
            }
        }
        notifyItemMoved(fromPosition,toPosition);
    }

    //TODO 滑动item,删除

    public void removeItem(int position)
    {
        listDatas.remove(position);//TODO 删除数据
        notifyItemRemoved(position);
    }

}
