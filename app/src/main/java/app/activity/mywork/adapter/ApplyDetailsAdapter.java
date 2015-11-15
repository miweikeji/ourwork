package app.activity.mywork.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import app.adapter.AllAdapter;
import app.entity.ApplyCrafts;
import app.entity.DetailPlan;
import app.entity.InviteCrafts;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.StatusTools;
import app.utils.Uihelper;
import app.views.ProgressDialogView;

/**
 * Created by Administrator on 2015/10/27.
 */
public class ApplyDetailsAdapter extends AllAdapter {

    private final boolean isApply;
    private final Dialog mWaitingDialog;
    private final ImageLoader imageLoader;
    private ArrayList<DetailPlan> allCases;
    private Activity activity;
    private DisplayImageOptions options;
    private List<ApplyCrafts> applyCraftList;
    private List<InviteCrafts> inviteCraftList;
    private String messageId;

    public ApplyDetailsAdapter(Activity activity, DisplayImageOptions options, boolean isApply) {
        imageLoader = ImageLoader.getInstance();
        mWaitingDialog = ProgressDialogView.create(activity);
        this.activity = activity;
        this.options = options;
        this.isApply = isApply;
    }

    @Override
    public int getCount() {
        if (applyCraftList != null && applyCraftList.size() > 0) {
            return applyCraftList.size();
        } else if (inviteCraftList != null && inviteCraftList.size() > 0) {
            return inviteCraftList.size();
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View layout, ViewGroup parent) {
        ViewHolder holder = null;
        if (layout == null) {
            holder = new ViewHolder();
            layout = activity.getLayoutInflater().inflate(R.layout.item_messageb, null);
            holder.tv_name_and_case = (TextView) layout.findViewById(R.id.tv_name_and_case);
            holder.tv_choose_or_state = (TextView) layout.findViewById(R.id.tv_choose_or_state);
            holder.tv_refuse = (TextView) layout.findViewById(R.id.tv_refuse);
            holder.img_head = (ImageView) layout.findViewById(R.id.img_head);
            layout.setTag(holder);
        } else {
            holder = (ViewHolder) layout.getTag();
        }
        holder.tv_choose_or_state.setVisibility(View.GONE);
        holder.tv_refuse.setVisibility(View.GONE);
        handleData(holder, position);

        return layout;
    }

    private void handleData(ViewHolder holder, int position) {
        if (isApply) {
            final ApplyCrafts applyCrafts = applyCraftList.get(position);
            holder.tv_name_and_case.setText(applyCrafts.getName());
            imageLoader.displayImage(applyCrafts.getCimg(), holder.img_head, options);
            String status = applyCrafts.getStatus();
            if (!TextUtils.isEmpty(status)) {
                if ("0".equals(status)) {
                    holder.tv_choose_or_state.setVisibility(View.VISIBLE);
                    holder.tv_refuse.setVisibility(View.VISIBLE);
                    holder.tv_choose_or_state.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mWaitingDialog.show();
                            HttpRequest.choseCrafts(activity, applyCrafts.getWorkid(), applyCrafts.getId(), new ICallback<Meta>() {
                                @Override
                                public void onSucceed(Meta result) {
                                    mWaitingDialog.dismiss();
                                    Uihelper.showToast(activity, "发送成功");
                                }

                                @Override
                                public void onFail(String error) {
                                    mWaitingDialog.dismiss();
                                    Uihelper.showToast(activity, error);
                                }
                            });

                        }
                    });
                    holder.tv_refuse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mWaitingDialog.show();
                            HttpRequest.refuseOder(activity, messageId, new ICallback<Meta>() {
                                @Override
                                public void onSucceed(Meta result) {
                                    mWaitingDialog.dismiss();
                                    Uihelper.showToast(activity, "发送成功");
                                }

                                @Override
                                public void onFail(String error) {
                                    mWaitingDialog.dismiss();
                                    Uihelper.showToast(activity, error);
                                }
                            });
                        }
                    });

                } else if ("1".equals(status)) {
                    holder.tv_choose_or_state.setVisibility(View.VISIBLE);
                    holder.tv_choose_or_state.setBackgroundResource(R.color.white);
                    holder.tv_choose_or_state.setTextColor(Color.GRAY);
                    holder.tv_choose_or_state.setText("已同意");
                } else if (("2".equals(status))) {
                    holder.tv_choose_or_state.setVisibility(View.VISIBLE);
                    holder.tv_choose_or_state.setBackgroundResource(R.color.white);
                    holder.tv_choose_or_state.setTextColor(Color.GRAY);
                    holder.tv_choose_or_state.setText("已拒绝");
                }
            }

        } else {
            //邀请人列表
            final InviteCrafts inviteCrafts = inviteCraftList.get(position);
            holder.tv_name_and_case.setText(inviteCrafts.getName());
            imageLoader.displayImage(inviteCrafts.getCimg(), holder.img_head, options);
            String status = inviteCrafts.getStatus();
            if (!TextUtils.isEmpty(status)) {

                if ("4".equals(status)) {
                    holder.tv_choose_or_state.setVisibility(View.VISIBLE);
                    holder.tv_choose_or_state.setBackgroundResource(R.color.white);
                    holder.tv_choose_or_state.setTextColor(Color.GRAY);
                    holder.tv_choose_or_state.setText("已接受邀请");
                } else if (("5".equals(status))) {
                    holder.tv_choose_or_state.setVisibility(View.VISIBLE);
                    holder.tv_choose_or_state.setBackgroundResource(R.color.white);
                    holder.tv_choose_or_state.setTextColor(Color.GRAY);
                    holder.tv_choose_or_state.setText("已拒绝邀请");
                }

            }
        }
    }

    public void setApplyList(List<ApplyCrafts> applyCraftList) {
        this.applyCraftList = applyCraftList;
    }

    public void setInviteList(List<InviteCrafts> inviteCraftList) {
        this.inviteCraftList = inviteCraftList;
    }

    public void setMessageID(String messageId) {
        this.messageId = messageId;
    }

    public class ViewHolder {
        TextView tv_name_and_case;
        TextView tv_choose_or_state;
        TextView tv_refuse;
        ImageView img_head;
    }
}
