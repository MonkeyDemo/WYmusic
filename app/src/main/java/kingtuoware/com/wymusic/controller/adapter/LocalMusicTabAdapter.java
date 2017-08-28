package kingtuoware.com.wymusic.controller.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kingtuoware.com.wymusic.controller.fragments.DiantaiFragment;
import kingtuoware.com.wymusic.controller.fragments.PersonFragment;
import kingtuoware.com.wymusic.controller.fragments.SingleSongFragment;

/**
 * Created by xww on 2017/8/16.
 */

public class LocalMusicTabAdapter extends FragmentPagerAdapter {

    private String[] title=null;
    public LocalMusicTabAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (title[position]){
            case "单曲":
                fragment =  new SingleSongFragment();
                break;
            case "歌手":
                fragment =  new DiantaiFragment();
                break;
            case "专辑":
                fragment =  new PersonFragment();
                break;
            case "文件夹":
                fragment = new PersonFragment();
            default:
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        if (title!=null){
            return title.length;
        }
        return 0;
    }
}
