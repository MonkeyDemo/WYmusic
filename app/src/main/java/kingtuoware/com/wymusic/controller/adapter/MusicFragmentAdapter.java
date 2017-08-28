package kingtuoware.com.wymusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kingtuoware.com.wymusic.R;
import kingtuoware.com.wymusic.model.beans.SongGroupBean;
import kingtuoware.com.wymusic.model.beans.SongSheetBean;
import kingtuoware.com.wymusic.model.utils.MusicUtils;

/**
 *  ExpandableListView适配器
 * =====================================
 * 作    者: 许登乔
 * 创建日期：2017-8-23
 * 描    述：
 * =====================================
 */
public class MusicFragmentAdapter extends BaseExpandableListAdapter {
    List<SongGroupBean> groupList;
    List<List<SongSheetBean>> childList;
    Context mContext;

    public MusicFragmentAdapter(List<SongGroupBean> groupList, List<List<SongSheetBean>> childList, Context mContext) {
        this.groupList = groupList;
        this.childList = childList;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return groupList!=null?groupList.size():0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList!=null?(childList.get(groupPosition)!=null?childList.get(groupPosition).size():0):0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList!=null&&groupList.size()>0?groupList.get(groupPosition):null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList!=null?(childList.get(groupPosition)!=null&&childList.get(groupPosition).size()>0?childList.get(groupPosition).get(childPosition):null):null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        GroupHolder holder = null;
        if (convertView==null){
            if (groupPosition==groupList.size()-1){
                convertView = inflater.inflate(R.layout.fragment_music_group_item2,null);
            }else{
                convertView = inflater.inflate(R.layout.fragment_music_group_item1, null);
            }
            holder = new GroupHolder();
            holder.ivRight = (ImageView) convertView.findViewById(R.id.iv_right);
            holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            holder.tvText = (TextView) convertView.findViewById(R.id.tv_text);
            holder.ivLeft = (ImageView) convertView.findViewById(R.id.iv_Left);
            convertView.setTag(holder);
        }else{
            holder = (GroupHolder) convertView.getTag();
        }
        if (groupList!=null&&groupList.size()>0){
            SongGroupBean bean = groupList.get(groupPosition);
            holder.tvNum.setText("("+bean.getGroupNum()+")");
            holder.tvText.setText(bean.getGroupName());
            if (bean.isPlaying()){
                holder.ivRight.setVisibility(View.VISIBLE);
            }else{
                holder.ivRight.setVisibility(View.GONE);
            }
            switch (groupPosition){
                case 0:
                    holder.ivLeft.setImageResource(R.drawable.local);
                    break;
                case 1:
                    holder.ivLeft.setImageResource(R.drawable.recent);
                    break;
                case 2:
                    holder.ivLeft.setImageResource(R.drawable.download);
                    break;
                case 3:
                    holder.ivLeft.setImageResource(R.drawable.diantai_red);
                    break;
                case 4:
                    holder.ivLeft.setImageResource(R.drawable.collection);
                    break;
                case 5:
                    holder.ivLeft.setImageResource(R.drawable.angle_down);
                    rorateAnim(isExpanded,holder.ivLeft);
                    break;
            }
        }
        return convertView;
    }

    public void rorateAnim(boolean isExpanded,View view){
        float startAngee ,endAngee;
        if (isExpanded){
            startAngee = -90.0f;
            endAngee=0.0f;
        }else{
            startAngee = 0.0f;
            endAngee =-90.0f;
        }
        RotateAnimation animation = new RotateAnimation(startAngee,endAngee, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(300);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ChildHolder holder = null;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.fragment_music_child_item,null);
            holder = new ChildHolder();
            holder.ivLeft = (ImageView) convertView.findViewById(R.id.iv_picLeft);
            holder.ivSelect = (ImageView) convertView.findViewById(R.id.iv_select);
            holder.ivRight = (ImageView) convertView.findViewById(R.id.iv_right);
            holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            holder.tvText = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        }else{
            holder = (ChildHolder) convertView.getTag();
        }
        if (childList!=null&&childList.size()>0){
            SongSheetBean bean = childList.get(groupPosition).get(childPosition);
            holder.tvText.setText(bean.getTitle());
            holder.tvNum.setText("("+bean.getContentNum()+")");
            if (bean.getPicPath()==null){
                //加载默认图片
            }else{
                holder.ivLeft.setImageBitmap(MusicUtils.createAlbumThumbnail(bean.getPicPath()));
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class GroupHolder{
        ImageView ivRight,ivLeft;
        TextView tvNum,tvText;
    }

    public class ChildHolder{
        ImageView ivLeft,ivRight,ivSelect;
        TextView tvText,tvNum;
    }

}
