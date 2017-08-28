package kingtuoware.com.wymusic.controller.activity;

import android.os.Bundle;
import android.view.Window;

import java.util.List;

import kingtuoware.com.wymusic.R;
import kingtuoware.com.wymusic.model.beans.Mp3Info;
import kingtuoware.com.wymusic.model.db.DBservice;
import kingtuoware.com.wymusic.model.utils.MFGT;
import kingtuoware.com.wymusic.model.utils.MusicUtils;

public class WelcomeActivity extends BaseActivity {

    private static final int welcomeSleep = 2*1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(){
            @Override
            public void run() {
                try {
                    long startTime= System.currentTimeMillis();
                    init();
                    long endTime = System.currentTimeMillis();
                    if (endTime-startTime<welcomeSleep){
                        Thread.sleep(welcomeSleep-(endTime-startTime));
                    }
                    MFGT.gotoMain(WelcomeActivity.this);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void init() {
        List<Mp3Info> mp3List = MusicUtils.getInstanse(this).getMp3List();
        DBservice.getInstance(this).mp3InfoMultInsert(mp3List);
    }

}
