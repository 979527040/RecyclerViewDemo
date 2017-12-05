package com.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Administrator on 2017/3/6.
 */
//TODO 设置可以拖拽的RecyclerView的item
class DragItemCallBack extends ItemTouchHelper.Callback {
    RecyclerAdapter recyclerAdapter;

    public DragItemCallBack(RecyclerAdapter recyclerAdapter)
    {
        this.recyclerAdapter = recyclerAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        //todo actionState:action状态类型，有三类，ACTION_STATE_DRAG(拖拽)、ACTION_STATE_SWIPE(滑动)、ACTION_STATE_IDLE(静止)
        int dragFlags = makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.UP | ItemTouchHelper.DOWN
                | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//TODO 支持上下左右拖拽
        int swipeFlags = makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        );//TODO 支持左右滑动
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
    {
        //TODO viewHolder是拖动的item,target是放置item的目标位置
        int fromPosition = viewHolder.getAdapterPosition();//TODO 要拖拽的位置
        int toPosition = target.getAdapterPosition();//TODO 要放置的目标位置
        recyclerAdapter.moveItem(fromPosition, toPosition);
        return true;
    }

    //TODO 滑动移除的item
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        int position = viewHolder.getAdapterPosition();//TODO 获取要滑动删除的item位置
        recyclerAdapter.removeItem(position);
    }

    //TODO 不支持长按拖拽效果直接返回false
    @Override
    public boolean isLongPressDragEnabled()
    {
        return super.isLongPressDragEnabled();
    }

    //TODO 不支持滑动效果直接返回false
    @Override
    public boolean isItemViewSwipeEnabled()
    {
        return super.isItemViewSwipeEnabled();
    }

}
