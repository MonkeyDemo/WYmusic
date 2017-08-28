package kingtuoware.com.wymusic.model.db;

import android.content.Context;

import com.ping.greendao.gen.DaoMaster;
import com.ping.greendao.gen.DaoSession;
import com.ping.greendao.gen.Mp3InfoDao;

import java.util.List;

import kingtuoware.com.wymusic.controller.application.WYmusicApplication;
import kingtuoware.com.wymusic.model.beans.Mp3Info;

/**
 *
 * =====================================
 * 作    者: 许登乔
 * 创建日期：2018-8-25
 * 描    述：数据库操作接口
 * =====================================
 */

public class DBservice {
    public static DBservice instance = null;
    private DaoSession mDaoSession;

    private Mp3InfoDao mMp3InfoDao;

    public static DBservice getInstance(Context context){
        if (instance==null){
            instance = new DBservice();
            instance.mDaoSession = WYmusicApplication.getDaoSession(context);
            instance.mMp3InfoDao = instance.mDaoSession.getMp3InfoDao();
        }
        return instance;
    }

    /**
     * 插入单个音乐数据
     * @param mp3Info  插入的音乐实体
     * @return 返回成功与否
     */
    public boolean mp3InfoInsert(Mp3Info mp3Info){
        boolean flag = false;
        flag = mMp3InfoDao.insert(mp3Info)==-1?false:true;
        return flag;
    }

    /**
     * 插入多条音乐数据
     * @param mp3List
     * @return  返回成功与否
     */
    public boolean mp3InfoMultInsert(final List<Mp3Info> mp3List){
        boolean flag = false;
        try{
            mDaoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Mp3Info info:mp3List){
                        mMp3InfoDao.insertOrReplace(info);
                    }
                }
            });
            flag = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获得所有的音乐
     * @return
     */
    public List<Mp3Info> getAllMp3Info(){
        return mMp3InfoDao.loadAll();
    }
}
