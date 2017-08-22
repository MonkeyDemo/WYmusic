package kingtuoware.com.wymusic.controller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import kingtuoware.com.wymusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment {

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
        initView(layout);
        return layout;
    }

    private void initView(View layout) {
        mRefresh = (SwipeRefreshLayout) layout.findViewById(R.id.srl_refresh);
        mExListView = (ExpandableListView) layout.findViewById(R.id.el_list);
    }

}
