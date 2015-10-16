package app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.miweikeij.app.R;

import java.util.List;

import app.entity.MyWork;

/**
 * Created by TuLiangTan on 2015/9/25.
 */
public class MyWorkAdapter extends RecyclerView.Adapter {

    private List<MyWork> dataList;
    private MyItemClickListener onItemClickListener;
    private int mPosition = -1;


    public MyWorkAdapter(List<MyWork> dataList) {
        this.dataList = dataList;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mywork, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyWork myWork = dataList.get(position);
        ((ViewHolder) holder).tvTitle.setText(myWork.getTitle());

    }

    @Override
    public int getItemCount() {
        if (dataList != null) {
            return dataList.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPosition != getLayoutPosition()) {
                        mPosition = getLayoutPosition();
                    } else {
                        mPosition = -1;
                    }
                    onItemClickListener.onItemClick(v, mPosition);
                    notifyDataSetChanged();
                }
            });
            tvTitle = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }


    public void setOnItemClickListener(MyItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }
}
