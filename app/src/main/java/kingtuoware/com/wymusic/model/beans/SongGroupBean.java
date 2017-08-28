package kingtuoware.com.wymusic.model.beans;

import com.ping.greendao.gen.Mp3InfoDao;

import java.io.Serializable;

/**
 *  音乐组实体类
 * =====================================
 * 作    者: 许登乔
 * 创建日期：2017-8-23
 * 描    述：
 * =====================================
 */

public class SongGroupBean implements Serializable {
    private String groupName;  //音乐组名
    private int groupNum;     //组内音乐数
    private boolean isPlaying;    //组内音乐是否播放

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public SongGroupBean(String groupName, int groupNum, boolean isPlaying) {
        this.groupName = groupName;
        this.groupNum = groupNum;
        this.isPlaying = isPlaying;
    }
}
