package refresh_recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.recyclerviewdemo.R;

import java.util.List;

import refresh_recyclerview.interfaces.IRefreshHeader;

/**
 * Created by wizardev on 17-12-2.
 */

public class RefreshHeaderAdapter extends RecyclerView.Adapter<RefreshHeaderAdapter.RefreshViewHolder>{
    private List<String> mLists;
    private Context mContext;
    private LayoutInflater mInflater;
    private static final int TYPE_REFRESH_HEADER = 100000;
//    private List<View> headers = new ArrayList<>();
    private static final int TYPE_NORMAL = 10001;
    private IRefreshHeader mRefreshHeader;
    private int viewTpye=TYPE_NORMAL;

    public RefreshHeaderAdapter(List<String> lists, Context context) {
        mLists = lists;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RefreshViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_REFRESH_HEADER) {
            viewTpye=TYPE_REFRESH_HEADER;
            return new RefreshViewHolder(mRefreshHeader.getHeaderView());
        }
        viewTpye=TYPE_NORMAL;
        return new RefreshViewHolder(mInflater.inflate(R.layout.normal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RefreshViewHolder holder, int position) {
        if(viewTpye!=TYPE_REFRESH_HEADER&&holder.mTextView!=null) {
            holder.mTextView.setText(mLists.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_REFRESH_HEADER;
        }
        return TYPE_NORMAL;
    }

    class RefreshViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public RefreshViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public void setRefreshHeader(IRefreshHeader header) {
        if (header != null) {
            mRefreshHeader = header;
        }
    }
}
