package kingtuoware.com.wymusic.controller.application;

import android.app.Application;
import android.content.Intent;

import kingtuoware.com.wymusic.controller.services.PlayerService;

/**
 * Created by xww on 2017/8/18.
 */

public class WYmusicApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        //开启服务
        initService();
    }

    private void initService() {
        Intent playerIntent = new Intent(getApplicationContext(), PlayerService.class);
        startService(playerIntent);
    }
    
}
