package kingtuoware.com.wymusic.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import kingtuoware.com.wymusic.controller.fragments.DiantaiFragment;
import kingtuoware.com.wymusic.controller.fragments.MusicFragment;
import kingtuoware.com.wymusic.controller.fragments.PersonFragment;

/**
 * Created by xww on 2017/8/16.
 */

public class MainTabAdapter extends FragmentPagerAdapter {

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MusicFragment();
            case 1:
                return new DiantaiFragment();
            case 2:
                return new PersonFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
