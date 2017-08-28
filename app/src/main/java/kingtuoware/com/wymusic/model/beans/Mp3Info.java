package kingtuoware.com.wymusic.model.beans;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 *  音乐实体类
 * =====================================
 * 作    者: 许登乔
 * 创建日期：2017-8-23
 * 描    述：
 * =====================================
 */
@Entity
public class Mp3Info implements Serializable {
    @Id(autoincrement = true)
    private Long id;
    private String title;  //歌曲名
    private String album;  //专辑名
    private String artist;   //歌手名
    private String path;   //歌曲路径
    private int duration;  //时长
    private int size;   //大小
    private String label;  //标签（用于分组）
    @Transient
    private boolean isPlaying ;   //状态


    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    @Generated(hash = 2005568083)
    public Mp3Info(Long id, String title, String album, String artist, String path,
            int duration, int size, String label) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.path = path;
        this.duration = duration;
        this.size = size;
        this.label = label;
    }

    @Generated(hash = 441769635)
    public Mp3Info() {
    }


    @Override
    public String toString() {
        return "Mp3Info{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", isPlaying=" + isPlaying +
                '}';
    }


    public int getSize() {
        return this.size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public int getDuration() {
        return this.duration;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }


    public String getPath() {
        return this.path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public String getArtist() {
        return this.artist;
    }


    public void setArtist(String artist) {
        this.artist = artist;
    }


    public String getAlbum() {
        return this.album;
    }


    public void setAlbum(String album) {
        this.album = album;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public long getId() {
        return this.id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
