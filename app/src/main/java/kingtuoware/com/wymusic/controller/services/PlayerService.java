package kingtuoware.com.wymusic.controller.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import kingtuoware.com.wymusic.I;
import kingtuoware.com.wymusic.model.beans.Mp3Info;
import kingtuoware.com.wymusic.model.utils.MusicUtils;

/**
 * Created by xww on 2017/8/17.
 */

public class PlayerService extends Service implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener{

    MediaPlayer mMediaPlayer;
    boolean isPause;
    boolean isStop;
    String path;   //音乐文件路径
    int mPosition;   //音乐播放点
    Mp3Info mCurrentMp3info;//当前播放mp3实体类
    int currentPosition = 0;
    private int currentTime;
    private int durationTime;
    List<Mp3Info> mMp3List;
    int mLoopType = I.MSG_MUSIC_LIST_LOOP;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        isPause = true;
        isStop = true;
        //获得手机音频信息
        mMp3List= MusicUtils.getInstanse(this).getMp3List();
        if (mMp3List!=null&&mMp3List.size()>0){
            path = mMp3List.get(currentPosition).getPath();
            mCurrentMp3info = mMp3List.get(currentPosition);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mMp3List!=null&&mMp3List.size()>0){
            mCurrentMp3info = mMp3List.get(currentPosition);
            path = mCurrentMp3info.getPath();
        }
        int type = intent.getIntExtra(I.MSG_MUSIC_CONTROLL,0);
        switch (type){
            case I.MSG_MUSIC_CONTROLL_NEXT:
                next();
                break;
            case I.MSG_MUSIC_CONTROLL_PAUSE:
                pause();
                break;
            case I.MSG_MUSIC_CONTROLL_PLAY:
                if (isPause){
                    resume();
                }else if (mLoopType==I.MSG_MUSIC_RANDOM_LOOP){
                    randomPlay();
                }else{
                    play(0);
                }
                break;
            case I.MSG_MUSIC_CONTROLL_PREVIOUS:
                previous();
                break;
            case I.MSG_MUSIC_CONTROLL_STOP:
                stop();
                break;
            case I.MSG_MUSIC_CONTROLL_SEEKTO:
                int progress =intent.getIntExtra(I.MSG_MUSIC_PROGRESS,0);
                seek(progress);
//                play(progress);
                break;
            case I.MSG_MUSIC_CONTROLL_LOOP:
                int loopType = intent.getIntExtra(I.MSG_MUSIC_LOOP_TYPE,I.MSG_MUSIC_LIST_LOOP);
                mLoopType = loopType;
                break;
            case I.MSG_MUSIC_CONTROLL_SYNOC:
                //同步信号
                synoc();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                if (mMediaPlayer!=null&&mMediaPlayer.isPlaying()){
                    currentTime = mMediaPlayer.getCurrentPosition();//获取当前音乐播放的位置
                    Intent intent = new Intent();
                    intent.setAction(I.ACTION_MUSIC_CURRENT);
                    intent.putExtra(I.Current_time,currentTime);
                    sendBroadcast(intent);
                    mHandler.sendEmptyMessageDelayed(1,500);
                }
            }
        }
    };

    /**
     * 播放音乐
     * @param position
     */
    private void play(int position){
        if (mMediaPlayer!=null){
            try {
                mMediaPlayer.reset();
                if (path==null||!new File(path).exists()){
                    return;
                }
                mPosition = position;
                mMediaPlayer.setDataSource(path);
                mMediaPlayer.prepare();
                mHandler.sendEmptyMessage(1);
                synoc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void pause(){
        if (mMediaPlayer!=null&&mMediaPlayer.isPlaying()){
            mMediaPlayer.pause();
            currentTime = mMediaPlayer.getCurrentPosition();
            isPause = true;
            synoc();
        }
    }

    private void stop(){
        if (mMediaPlayer!=null&&mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            isStop = true;
        }
    }

    private void resume(){
        if (mMediaPlayer!=null&&isPause){
            mMediaPlayer.start();
            isPause = false;
            mHandler.sendEmptyMessage(1);
            synoc();
        }
    }

    private void previous(){
        if (currentPosition==0){
            currentPosition = mMp3List.size()-1;
        }else{
            currentPosition--;
        }
        path = mMp3List.get(currentPosition).getPath();
        mCurrentMp3info = mMp3List.get(currentPosition);
        play(0);
    }

    private void next(){
        if (currentPosition==mMp3List.size()-1){
            currentPosition=0;
        }else{
            currentPosition++;
        }
        path = mMp3List.get(currentPosition).getPath();
        mCurrentMp3info = mMp3List.get(currentPosition);
        play(0);
    }

    /**
     * 随机播放
     */
    private void randomPlay(){
        Random random = new Random();
        int rand= random.nextInt(mMp3List.size());
        currentPosition = rand;
        path = mMp3List.get(currentPosition).getPath();
        mCurrentMp3info = mMp3List.get(currentPosition);
        play(0);
    }

    private void seek(int progress){
        currentTime = progress;
        if (mMediaPlayer!=null){
            mMediaPlayer.seekTo(currentTime);
            resume();
        }
    }
    @Override
        public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
        if (mPosition>0){
            //说明不是重头开始播放
            mMediaPlayer.seekTo(mPosition);
            isPause = false;
            isStop = false;
        }
        Intent intent = new Intent();
        intent.setAction(I.ACTION_MUSIC_DURATION);
        durationTime = mMediaPlayer.getDuration();
        intent.putExtra(I.Duration_time,durationTime);
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        switch (mLoopType){
            case I.MSG_MUSIC_LIST_LOOP:
                next();
                break;
            case I.MSG_MUSIC_RANDOM_LOOP:
                randomPlay();
                break;
            case I.MSG_MUSIC_SINGRE_LOOP:
                play(0);
                break;
        }
    }

    /**
     * 发送同步广播
     */
    private void synoc(){
        Intent intent = new Intent();
        intent.setAction(I.ACTION_MUNIC_INFO);
        mCurrentMp3info.setPlaying(!isPause);
        intent.putExtra(I.info,mCurrentMp3info);
        sendBroadcast(intent);
    }
}
