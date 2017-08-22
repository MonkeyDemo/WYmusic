package kingtuoware.com.wymusic.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import kingtuoware.com.wymusic.model.beans.SongSheetBean;

/**
 * Created by xww on 2017/8/22.
 */

public class MusicFragmentAdapter extends BaseExpandableListAdapter {
    List<String> groupList;
    List<SongSheetBean> childList;
    Context mContext;

    public MusicFragmentAdapter(List<String> groupList, List<SongSheetBean> childList, Context mContext) {
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
        return childList!=null?childList.size():0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList!=null&&groupList.size()>0?groupList.get(groupPosition):null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return getGroupCount()!=0&&childList!=null&&childList.size()>0?childList.get(childPosition):null;
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
