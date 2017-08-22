package kingtuoware.com.wymusic.model.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import kingtuoware.com.wymusic.model.beans.Mp3Info;

/**
 * Created by xww on 2017/8/21.
 */

public class MusicUtils {
    static Context mContext;
    private static MusicUtils instanse;

    public MusicUtils(Context context){
        mContext = context;
    }
    public static MusicUtils getInstanse(Context context){
        if (instanse==null){
            synchronized (MusicUtils.class){
                if (instanse==null){
                    instanse = new MusicUtils(context);
                }
            }
        }
        return instanse;
    }
    public List<Mp3Info> getMp3List() {
        ArrayList<Mp3Info> list = new ArrayList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        while (cursor.moveToNext()){
            Mp3Info temp = new Mp3Info();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID) );
            String  name= cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            String author = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
            int size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
            int isMusic = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.IS_MUSIC));
            if (isMusic==0){
                continue;
            }
            temp.setId(id);
            temp.setAlbum(album);
            temp.setPath(url);
            temp.setArtist(author);
            temp.setTitle(name);
            temp.setDuration(duration);
            temp.setSize(size);
            temp.setPlaying(false);
            list.add(temp);
        }
        return list;
    }

    /**
     * @Description 获取专辑封面
     * @param filePath 文件路径，like XXX/XXX/XX.mp3
     * @return 专辑封面bitmap
     */
    public static  Bitmap createAlbumThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
//            retriever.setMode(MediaMetadataRetriever.MODE_GET_METADATA_ONLY);
            retriever.setDataSource(filePath);
            byte[] art = retriever.getEmbeddedPicture();
            bitmap = BitmapFactory.decodeByteArray(art,0, art.length);
        } catch(IllegalArgumentException ex) {
        } catch (RuntimeException ex) {
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        return bitmap;
    }

    /**
     * 将毫秒格式化"00:00"的形式
     * @param ms
     * @return
     */
    public static String msFormat(int ms){
        int ss = 1000;
        int mi = ss*60;
        int hh = mi*60;
        int dd = hh*24;
        int day = ms/dd;
        int hour = ms%dd/hh;
        int minute = ms%hh/mi;
        int second = ms%mi/ss;
        String strDay = day==0?"00":(day<10?"0"+day:""+day);
        String strHour = hour==0?"00":(hour<10?"0"+hour:""+hour);
        String strMinute = minute==0?"00":(minute<10?"0"+minute:""+hour);
        String strSecond = second==0?"00":(second<10?"0"+second:""+second);
        return strMinute+":"+strSecond;
    }
}
