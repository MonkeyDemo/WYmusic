package kingtuoware.com.wymusic.controller.activity;

import android.os.Bundle;
import android.view.Window;

import kingtuoware.com.wymusic.R;
import kingtuoware.com.wymusic.model.utils.MFGT;

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
                    Thread.sleep(welcomeSleep);
                    MFGT.gotoMain(WelcomeActivity.this);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
