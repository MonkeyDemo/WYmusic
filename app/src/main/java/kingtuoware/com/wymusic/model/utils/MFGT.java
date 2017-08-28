package kingtuoware.com.wymusic.model.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import kingtuoware.com.wymusic.I;
import kingtuoware.com.wymusic.controller.activity.LocalMusicActivity;
import kingtuoware.com.wymusic.controller.activity.MainActivity;
import kingtuoware.com.wymusic.controller.activity.PlayerActivity;
import kingtuoware.com.wymusic.controller.services.PlayerService;

/**
 * Created by xww on 2017/8/16.
 */

public class MFGT {


    public static void startActivity(Activity context,Class<?> clazz){
        Intent intent = new Intent(context,clazz);
        startActivity(context,intent);
    }

    public static void startActivity(Activity context,Intent intent){
        context.startActivity(intent);
        //添加跳转动画
        context.overridePendingTransition(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
    }

    public static void startService(Activity context,Class<?> clazz){
        Intent intent = new Intent(context,clazz);
        startService(context,intent);
    }

    public static void startService(Activity context,Intent intent){
        context.startService(intent);
    }
    public static void gotoMain(Activity context) {
        startActivity(context,MainActivity.class);
    }

    public static void gotoPlayer(Activity context) {
        startActivity(context,PlayerActivity.class);
    }

    public static void gotoPlayerService(Activity context,int type){
        Intent intent = new Intent(context, PlayerService.class);
        intent.putExtra(I.MSG_MUSIC_CONTROLL,type);
        startService(context,intent);
    }

    public static void gotoPlayerService(Activity context, int type, int param) {
        Intent intent = new Intent(context, PlayerService.class);
        if (type==I.MSG_MUSIC_CONTROLL_SEEKTO){
            intent.putExtra(I.MSG_MUSIC_CONTROLL,type);
            intent.putExtra(I.MSG_MUSIC_PROGRESS,param);
        }else if (type==I.MSG_MUSIC_CONTROLL_LOOP){
            intent.putExtra(I.MSG_MUSIC_CONTROLL,type);
            intent.putExtra(I.MSG_MUSIC_LOOP_TYPE,param);
        }
        startService(context,intent);
    }

    public static void gotoLocalMusic(Context context) {
        startActivity((Activity) context, LocalMusicActivity.class);
    }
}
