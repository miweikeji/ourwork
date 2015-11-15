package app.activity.mywork.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miwei.jzj_system.R;

import java.util.List;

import app.adapter.AllAdapter;
import app.entity.Comment;
import app.entity.MyWork;

/**
 * Created by TuLiangTan on 2015/9/25.
 */
public class CraftValueAdapter extends AllAdapter {

    FragmentActivity activity;
    List<Comment> commentList;
    public CraftValueAdapter(FragmentActivity activity, List<Comment> commentList) {
        this.activity = activity;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList==null?0:commentList.size();
    }

    @Override
    public View getView(int position, View layout, ViewGroup parent) {
        ViewHolder holder=null;
        if(layout==null){
            holder = new ViewHolder();
             layout = activity.getLayoutInflater().inflate(R.layout.item_craft_value, null);
            holder.tv_content = (TextView) layout.findViewById(R.id.tv_content);
            holder.tv_name = (TextView) layout.findViewById(R.id.tv_name);
            holder.tv_time = (TextView) layout.findViewById(R.id.tv_time);
            layout.setTag(holder);
        }else {
            holder = (ViewHolder) layout.getTag();
        }
        Comment comment = commentList.get(position);
        holder.tv_content.setText(comment.getAdvise());
        holder.tv_time.setText(comment.getTime());

        holder.tv_name.setText(comment.getComment_craftsman_name());
        return layout;
    }

    public class ViewHolder{
        TextView tv_content;
        TextView tv_name;
        TextView tv_time;
    }
}
