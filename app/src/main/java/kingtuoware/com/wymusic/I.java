package kingtuoware.com.wymusic;

/**
 * Created by xww on 2017/8/18.
 */

public interface I {

    static final  String MSG_MUSIC_CONTROLL = "msg";   /** 播放控制消息传递字段**/

    static final int MSG_MUSIC_CONTROLL_PLAY =20;      /** 播放 **/

    static final int MSG_MUSIC_CONTROLL_PAUSE=21;       /** 暂停 **/

    static final  int MSG_MUSIC_CONTROLL_STOP=22;       /** 停止 **/

    static final int MSG_MUSIC_CONTROLL_PREVIOUS = 23;      /** 上一首 **/

    static final int MSG_MUSIC_CONTROLL_NEXT = 24;      /** 下一首 **/

    static final int MSG_MUSIC_CONTROLL_SEEKTO = 25;        /** 进度至 **/

    static final int MSG_MUSIC_CONTROLL_LOOP = 26;      /** 循环播放 **/

    static final int MSG_MUSIC_CONTROLL_SYNOC = 27;     /** 同步信号 **/

    static final String MSG_MUSIC_PROGRESS = "progress";        /** 进度值字段 **/

    static final String MSG_MUSIC_LOOP_TYPE = "loopType";       /** 循环播放类型字段 **/

    static final int MSG_MUSIC_LIST_LOOP = 260;     /** 列表循环播放 **/
    static final int MSG_MUSIC_SINGRE_LOOP = 261;       /** 单曲循环播放 **/
    static final int MSG_MUSIC_RANDOM_LOOP = 262;       /** 随机循环播放 **/


    static final String ACTION_MUSIC_CURRENT="kingtopware.com.wymusic.MUSIC_CURRENT";
    static final String Current_time = "currentTime";
    static final String ACTION_MUSIC_DURATION="kingtopware.com.wymusic.MUSIC_DURATION";
    static final String Duration_time = "durationTime";
    static final String ACTION_MUNIC_INFO = "kingtopware.com.wymusic.MUSIC_INFO";
    static final String info = "musicInfo";

    //数据库
    static final String DBNAME = "wy_music_db";     /** 数据库名 **/

}
