package com.recyclerviewdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import refresh_recyclerview.adapter.RefreshHeaderAdapter;
import refresh_recyclerview.interfaces.OnRefreshListener;
import refresh_recyclerview.view.RefreshHeaderRecyclerView;

import static com.recyclerviewdemo.ListDataBean.SetStaggeredListDatas;

public class MainActivity extends AppCompatActivity {
    private RefreshHeaderRecyclerView recyclerView;
    private List<ListDataBean> listDatas;
    private RecyclerAdapter recyclerAdapter;
    private ItemTouchHelper itemTouchHelper;
    private RefreshHeaderAdapter mAdapter;
    private List<String> mStringList;
    private static final int FINISH = 1;
    private LinearLayoutManager linearLayoutManager;

    private Handler sHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FINISH) {
                Toast.makeText(MainActivity.this,"刷新完成！",Toast.LENGTH_SHORT).show();
                recyclerView.refreshComplete();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RefreshHeaderRecyclerView) this.findViewById(R.id.recyclerView);
        //TODO 初始化recyclerView数据
        listDatas=ListDataBean.SetListDatas();
        //TODO 生成recyclerView的适配器
        initAdapter();
        //TODO 设置支持拖拽和滑动
        setDragRecyclerView();
        //TODO 局部刷新
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDatas.set(2,new ListDataBean(R.drawable.shitou,"局部刷新","子标题"));
                recyclerAdapter.notifyItemChanged(2);
            }
        });
        //TODO 添加
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewItem();
            }
        });
        //TODO 删除
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
            }
        });
        //TODO 瀑布流
        findViewById(R.id.staggeredGrid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStaggeredGrid();
            }
        });
        //TODO 下拉刷新
        findViewById(R.id.downRefresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRefreshAdapter();
            }
        });
        //TODO Item点击事件
        recyclerAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //TODO 设置添加和删除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    //TODO 默认
    private void initAdapter()
    {
        recyclerAdapter=new RecyclerAdapter(listDatas);
        //TODO 设置recyclerView的显示方向，必须设置，否则不显示内容
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //TODO 填充适配器
        recyclerView.setAdapter(recyclerAdapter);
    }
    //TODO 设置瀑布流
    private void setStaggeredGrid(){

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerAdapter=new RecyclerAdapter(SetStaggeredListDatas());
        recyclerView.setAdapter(recyclerAdapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }

    //TODO 下拉刷新
    private void initRefreshAdapter(){
        mStringList=new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mStringList.add("你好");
        }
        mAdapter = new RefreshHeaderAdapter(mStringList, this);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();//模拟数据的请求
            }
        });
    }
    //TODO 下拉刷新，模拟请求数据
    private void requestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    Message message = Message.obtain();
                    message.what = FINISH;
                    sHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    //TODO 设置RecyclerView可以拖拽
    private void setDragRecyclerView()
    {
        itemTouchHelper=new ItemTouchHelper(new DragItemCallBack(recyclerAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    //TODO 增加
    private void addNewItem(){
        if(listDatas==null){
            listDatas=new ArrayList<>();
        }
        listDatas.add(1,new ListDataBean(R.drawable.ic_pulltorefresh_arrow,"王者荣耀","TIM"));
        recyclerAdapter.notifyItemInserted(1);
        linearLayoutManager.scrollToPosition(0);
    }
    //TODO 删除
    private void deleteItem(){
        if(listDatas == null || listDatas.isEmpty()) {
            return;
        }
        listDatas.remove(0);
        recyclerAdapter.notifyItemRemoved(0);
        linearLayoutManager.scrollToPosition(0);
    }

}
