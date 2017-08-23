package kingtuoware.com.wymusic.controller.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import kingtuoware.com.wymusic.R;
import kingtuoware.com.wymusic.controller.adapter.MusicFragmentAdapter;
import kingtuoware.com.wymusic.model.beans.Mp3Info;
import kingtuoware.com.wymusic.model.beans.SongGroupBean;
import kingtuoware.com.wymusic.model.beans.SongSheetBean;
import kingtuoware.com.wymusic.model.utils.MusicUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,ExpandableListView.OnGroupClickListener{

    Context mContext;
    List<SongGroupBean> groupList;
    List<List<SongSheetBean>> childList;
    MusicFragmentAdapter mAdapter;
    private SwipeRefreshLayout mRefresh;
    private ExpandableListView mExListView;
    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_music,container,false);
        mContext = getContext();
        initView(layout);
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        groupList.clear();
        SongGroupBean g1 = new SongGroupBean("本地音乐",23,true);
        groupList.add(g1);
        SongGroupBean g2 = new SongGroupBean("最近播放",0,false);
        groupList.add(g2);
        SongGroupBean g3 = new SongGroupBean("下载管理",0,false);
        groupList.add(g3);
        SongGroupBean g4 = new SongGroupBean("我的电台",0,false);
        groupList.add(g4);
        SongGroupBean g5 = new SongGroupBean("我的收藏",0,false);
        groupList.add(g5);
        SongGroupBean g6 = new SongGroupBean("创建的歌单",0,false);
        groupList.add(g6);
        for (int i=0;i<5;i++){
            List<SongSheetBean> temp = new ArrayList<>();
            childList.add(temp);
        }
        SongSheetBean c1 = new SongSheetBean("我喜欢的音乐","","",null,23);
        ArrayList<SongSheetBean> list = new ArrayList<>();
        list.add(c1);
        g6.setGroupNum(list.size());
        childList.add(list);
        mAdapter.notifyDataSetChanged();
    }

    private void initView(View layout) {
        mRefresh = (SwipeRefreshLayout) layout.findViewById(R.id.srl_refresh);
        mRefresh.setOnRefreshListener(this);
        mExListView = (ExpandableListView) layout.findViewById(R.id.el_list);
        groupList = new ArrayList<>();
        childList = new ArrayList<>();
        mAdapter = new MusicFragmentAdapter(groupList,childList,getContext());
        mExListView.setAdapter(mAdapter);
        mExListView.setOnGroupClickListener(this);
    }

    @Override
    public void onRefresh() {
        //刷新监听
        List<Mp3Info> list = MusicUtils.getInstanse(mContext).getMp3List();
        groupList.get(0).setGroupNum(list.size());
        mAdapter.notifyDataSetChanged();
        mRefresh.setRefreshing(false);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        switch (groupPosition){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        return false;
    }
}
