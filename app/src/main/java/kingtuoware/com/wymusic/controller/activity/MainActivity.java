package kingtuoware.com.wymusic.controller.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kingtuoware.com.wymusic.I;
import kingtuoware.com.wymusic.R;
import kingtuoware.com.wymusic.controller.adapter.MainTabAdapter;
import kingtuoware.com.wymusic.model.beans.Mp3Info;
import kingtuoware.com.wymusic.model.utils.MFGT;
import kingtuoware.com.wymusic.model.utils.MusicUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView mIvMenu,mIvMusic,mIvDiantai,mIvPerson;
    DrawerLayout mDrawer;
    ViewPager mViewPager;
    MainTabAdapter mTabAdapter;
    private int mCurrentPager=1;
    RelativeLayout mFooter;
    Mp3Info mMp3Info;
    MainReceiver mReceiver;
    TextView mTvName,mTvAuthor;
    ImageView mIvAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        initView();
        register();
    }

    private void initView() {
        mIvMenu = (ImageView) findViewById(R.id.iv_menu);
        mIvMenu.setOnClickListener(this);
        mIvDiantai = (ImageView) findViewById(R.id.iv_diantai);
        mIvDiantai.setOnClickListener(this);
        mIvMusic = (ImageView) findViewById(R.id.iv_music);
        mIvMusic.setOnClickListener(this);
        mIvPerson = (ImageView) findViewById(R.id.iv_person);
        mIvPerson.setOnClickListener(this);
        mFooter = (RelativeLayout) findViewById(R.id.main_footer);
        mFooter.setOnClickListener(this);
        mTvName = (TextView) findViewById(R.id.tv_music_name);
        mTvAuthor = (TextView)findViewById(R.id.tv_music_author);
        mIvAlbum = (ImageView) findViewById(R.id.iv_music_thum);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mViewPager = (ViewPager) findViewById(R.id.main_content);
        mTabAdapter = new MainTabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabAdapter);
        mViewPager.setCurrentItem(mCurrentPager);
        tabChange(mCurrentPager);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_SYNOC);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver!=null){
            unregisterReceiver(mReceiver);
        }
    }

    private void initData(){
        if (mMp3Info!=null){
            mTvName.setText(mMp3Info.getTitle());
            mTvAuthor.setText(mMp3Info.getArtist());
            Bitmap bitmap = MusicUtils.createAlbumThumbnail(mMp3Info.getPath());
            if (bitmap!=null){
                mIvAlbum.setImageBitmap(bitmap);
            }
        }
    }

    private void register() {
        //注册广播接收器
        mReceiver = new MainReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(I.ACTION_MUSIC_CURRENT);
        filter.addAction(I.ACTION_MUSIC_DURATION);
        filter.addAction(I.ACTION_MUNIC_INFO);
        registerReceiver(mReceiver,filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_menu:
                mDrawer.openDrawer(Gravity.LEFT);
            break;
            case R.id.iv_diantai:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.iv_music:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.iv_person:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.main_footer:
                MFGT.gotoPlayer(MainActivity.this);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e(TAG,"position = "+position+",positionOffset="+positionOffset+",positionOffsetPixels="+positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        tabChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void tabChange(int currentPager){
        switch (currentPager){
            case 0:
                mIvMusic.setSelected(true);
                mIvDiantai.setSelected(false);
                mIvPerson.setSelected(false);
                break;
            case 1:
                mIvMusic.setSelected(false);
                mIvDiantai.setSelected(true);
                mIvPerson.setSelected(false);
                break;
            case 2:
                mIvMusic.setSelected(false);
                mIvDiantai.setSelected(false);
                mIvPerson.setSelected(true);
                break;
            default:
                break;
        }
    }

    public class MainReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            switch (action){
                case I.ACTION_MUSIC_CURRENT:
                    int currentTime = intent.getIntExtra(I.Current_time, -1);
                    if (currentTime!=-1){
//                        mSbSeek.setProgress(currentTime);
//                        mTvCurrentTime.setText(msFormat(currentTime));
                    }
                    break;
                case I.ACTION_MUSIC_DURATION:
                    int duration = intent.getIntExtra(I.Duration_time,-1);
                    if (duration!=-1){
//                        mSbSeek.setMax(duration);
//                        mTvTotalTime.setText(msFormat(duration));
                    }
                    break;
                case I.ACTION_MUNIC_INFO:
                    mMp3Info = (Mp3Info) intent.getSerializableExtra(I.info);
                    initData();
                    break;
            }
        }
    }

}
