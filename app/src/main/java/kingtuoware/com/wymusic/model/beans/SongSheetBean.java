package kingtuoware.com.wymusic.model.beans;

import java.io.Serializable;

/**
 * Created by xww on 2017/8/22.
 */

public class SongSheetBean implements Serializable{
    private String title;    //歌单标题
    private String label;       //歌单标签
    private String describe;        //歌单描述
    private String picPath;         //歌单封面图片地址

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public String toString() {
        return "SongSheetBean{" +
                "title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", describe='" + describe + '\'' +
                ", picPath='" + picPath + '\'' +
                '}';
    }
}
