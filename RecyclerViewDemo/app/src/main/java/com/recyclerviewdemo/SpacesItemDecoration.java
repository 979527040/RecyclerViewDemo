package com.recyclerviewdemo;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 97952 on 2017/12/4.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        int childCounts=parent.getChildLayoutPosition(view);
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}
