package kingtuoware.com.wymusic.controller.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import kingtuoware.com.wymusic.I;
import kingtuoware.com.wymusic.R;
import kingtuoware.com.wymusic.model.beans.Mp3Info;
import kingtuoware.com.wymusic.model.utils.MFGT;
import kingtuoware.com.wymusic.model.utils.MusicUtils;
import kingtuoware.com.wymusic.model.weidgts.SwichPager;

public class PlayerActivity extends BaseActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener{
    private static final String TAG = PlayerActivity.class.getSimpleName();
    ImageView mIvBack,mIvShare;
    ImageView mIvPlay,mIvPrevious,mIvNext,mIvLoop;
    SeekBar mSbSeek;
    int mProgress;
    boolean isPause = false;
    Mp3Info mMp3Info;
    PlayerReceiver mReceiver;
    TextView mTvName,mTvAuthor,mTvCurrentTime,mTvTotalTime;
    int mLoopType= I.MSG_MUSIC_LIST_LOOP ;
    SwichPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();
        register();
    }

    private void register() {
        //注册广播接收器
        mReceiver = new PlayerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(I.ACTION_MUSIC_CURRENT);
        filter.addAction(I.ACTION_MUSIC_DURATION);
        filter.addAction(I.ACTION_MUNIC_INFO);
        registerReceiver(mReceiver,filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver!=null){
            unregisterReceiver(mReceiver);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_SYNOC);
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mIvShare = (ImageView) findViewById(R.id.iv_share);
        mIvShare.setOnClickListener(this);
        mSbSeek = (SeekBar) findViewById(R.id.sb_seek);
        mSbSeek.setOnSeekBarChangeListener(this);
        mIvPlay = (ImageView) findViewById(R.id.iv_play_or_pause);
        mIvPlay.setOnClickListener(this);
        mIvPrevious = (ImageView) findViewById(R.id.iv_previous);
        mIvPrevious.setOnClickListener(this);
        mIvNext = (ImageView) findViewById(R.id.iv_next);
        mIvNext.setOnClickListener(this);
        mIvLoop = (ImageView) findViewById(R.id.iv_loop);
        mIvLoop.setOnClickListener(this);
        mTvCurrentTime = (TextView) findViewById(R.id.tv_current_time);
        mTvAuthor = (TextView) findViewById(R.id.tv_author);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvTotalTime = (TextView) findViewById(R.id.tv_total_time);
        mPager = (SwichPager) findViewById(R.id.sp_content);

    }

    private void initData(){
        if (mMp3Info!=null){
            mTvName.setText(mMp3Info.getTitle());
            mTvAuthor.setText(mMp3Info.getArtist());
            mTvTotalTime.setText(MusicUtils.msFormat(mMp3Info.getDuration()));
            isPause = !mMp3Info.isPlaying();
            if (mMp3Info.isPlaying()){
                mIvPlay.setImageResource(R.drawable.pause);
            }else{
                mIvPlay.setImageResource(R.drawable.play);
            }
        }
        switch (mLoopType){
            case I.MSG_MUSIC_LIST_LOOP:
                mIvLoop.setImageResource(R.drawable.loop_list);
                break;
            case I.MSG_MUSIC_SINGRE_LOOP:
                mIvLoop.setImageResource(R.drawable.loop_singer);
                break;
            case I.MSG_MUSIC_RANDOM_LOOP:
                mIvLoop.setImageResource(R.drawable.loop_random);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_play_or_pause:
                if (isPause){
                    MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_PLAY);
                    isPause=!isPause;
                    mIvPlay.setImageResource(R.drawable.pause);
                }else{
                    isPause = !isPause;
                    MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_PAUSE);
                    mIvPlay.setImageResource(R.drawable.play);
                }
                break;
            case R.id.iv_previous:
                MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_PREVIOUS);
                break;
            case R.id.iv_next:
                MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_NEXT);
                break;
            case R.id.iv_loop:
                mLoopType++;
                if (mLoopType>I.MSG_MUSIC_RANDOM_LOOP){
                    mLoopType = I.MSG_MUSIC_LIST_LOOP;
                }
                initData();
                MFGT.gotoPlayerService(this,I.MSG_MUSIC_CONTROLL_LOOP,mLoopType);
                break;
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        Log.e(TAG,"progress="+progress+", fromUser="+fromUser);
        if (fromUser){
            mProgress = progress;
            mTvCurrentTime.setText(MusicUtils.msFormat(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
//        Log.e(TAG,"start");
        MFGT.gotoPlayerService(PlayerActivity.this,I.MSG_MUSIC_CONTROLL_PAUSE);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
//        Log.e(TAG,"stop");
        MFGT.gotoPlayerService(PlayerActivity.this,I.MSG_MUSIC_CONTROLL_SEEKTO,mProgress);
    }

    public class PlayerReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            switch (action){
                case I.ACTION_MUSIC_CURRENT:
                    int currentTime = intent.getIntExtra(I.Current_time, -1);
                    if (currentTime!=-1){
                        mSbSeek.setProgress(currentTime);
                        mTvCurrentTime.setText(MusicUtils.msFormat(currentTime));
                    }
                    break;
                case I.ACTION_MUSIC_DURATION:
                    int duration = intent.getIntExtra(I.Duration_time,-1);
                    if (duration!=-1){
                        mSbSeek.setMax(duration);
                        mTvTotalTime.setText(MusicUtils.msFormat(duration));
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
