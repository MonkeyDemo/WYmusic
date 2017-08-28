package kingtuoware.com.wymusic.controller.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kingtuoware.com.wymusic.I;
import kingtuoware.com.wymusic.R;
import kingtuoware.com.wymusic.controller.adapter.LocalMusicTabAdapter;
import kingtuoware.com.wymusic.controller.fragments.SingleSongFragment;
import kingtuoware.com.wymusic.model.beans.Mp3Info;
import kingtuoware.com.wymusic.model.utils.MFGT;
import kingtuoware.com.wymusic.model.utils.MusicUtils;

public class LocalMusicActivity extends BaseActivity implements View.OnClickListener,SingleSongFragment.OnListFragmentInteractionListener{

    /**
     * Tab标题
     */
    private static final String[] TITLE = new String[] { "单曲", "歌手", "专辑", "文件夹"};
    ImageButton ibBack,ibAction;
    ImageView ivSearch;
    TextView tvTitle;
    LocalMusicReceiver mReceiver;
    Mp3Info mMp3Info;
    RelativeLayout mFooter;
    TextView mTvName,mTvAuthor;
    ImageView mIvAlbum;
    ViewPager mViewpager;
    TabLayout mIndicator;
    LocalMusicTabAdapter mTabAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        initView();
        register();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_SYNOC);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void initView() {
        ibBack = (ImageButton) findViewById(R.id.ib_back);
        ibBack.setVisibility(View.VISIBLE);
        ibBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("本地音乐");
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivSearch.setVisibility(View.VISIBLE);
        ivSearch.setOnClickListener(this);
        ibAction = (ImageButton) findViewById(R.id.ib_action);
        ibAction.setVisibility(View.VISIBLE);
        mFooter = (RelativeLayout) findViewById(R.id.main_footer);
        mFooter.setOnClickListener(this);
        mTvName = (TextView) findViewById(R.id.tv_music_name);
        mTvAuthor = (TextView)findViewById(R.id.tv_music_author);
        mIvAlbum = (ImageView) findViewById(R.id.iv_music_thum);
        mViewpager = (ViewPager) findViewById(R.id.vp_pager);
        mTabAdapter = new LocalMusicTabAdapter(getSupportFragmentManager(),TITLE);
        mViewpager.setAdapter(mTabAdapter);
        mIndicator = (TabLayout) findViewById(R.id.indicator);
        mIndicator.setupWithViewPager(mViewpager);
    }

    private void register() {
        //注册广播接收器
        mReceiver = new LocalMusicReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(I.ACTION_MUSIC_CURRENT);
        filter.addAction(I.ACTION_MUSIC_DURATION);
        filter.addAction(I.ACTION_MUNIC_INFO);
        registerReceiver(mReceiver,filter);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_back:
                finish();
                break;
            case R.id.iv_search:
                break;
            case R.id.main_footer:
                MFGT.gotoPlayer(LocalMusicActivity.this);
                break;
        }
    }

    @Override
    public void onListFragmentInteraction(Mp3Info item) {
        MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_PLAY,(int)item.getId());
    }

    public class LocalMusicReceiver extends BroadcastReceiver {

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
