package kingtuoware.com.wymusic.model.beans;

import java.io.Serializable;


/**
 * 歌单实体类
 * =====================================
 * 作    者: 许登乔
 * 创建日期：2017-8-23
 * 描    述：
 * =====================================
 */
public class SongSheetBean implements Serializable{
    private String title;    //歌单标题
    private String label;       //歌单标签
    private String describe;        //歌单描述
    private String picPath;         //歌单封面图片地址
    private int contentNum;       //歌单包含的歌曲数

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

    public int getContentNum() {
        return contentNum;
    }

    public void setContentNum(int contentNum) {
        this.contentNum = contentNum;
    }

    @Override
    public String toString() {
        return "SongSheetBean{" +
                "title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", describe='" + describe + '\'' +
                ", picPath='" + picPath + '\'' +
                ", contentNum='" + contentNum + '\'' +
                '}';
    }

    public SongSheetBean(String title, String label, String describe, String picPath, int contentNum) {
        this.title = title;
        this.label = label;
        this.describe = describe;
        this.picPath = picPath;
        this.contentNum = contentNum;
    }
}
