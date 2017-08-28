package kingtuoware.com.wymusic.controller.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.ping.greendao.gen.DaoMaster;
import com.ping.greendao.gen.DaoSession;

import kingtuoware.com.wymusic.I;
import kingtuoware.com.wymusic.controller.services.PlayerService;

/**
 * Created by xww on 2017/8/18.
 */

public class WYmusicApplication extends Application {
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
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

    public static DaoMaster getDaoMaster(Context context){
        if (daoMaster==null){
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,I.DBNAME,null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession(Context context){
        if (daoSession==null){
            if (daoMaster==null){
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
