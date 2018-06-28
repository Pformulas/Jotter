package entity;

import java.util.Date;

/**
 * author: Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 13:00
 */
public class Files {

    //用户id从session中取
    private  String userId;

    //存储url为  File+用户名+文件名
    private String url;

    //1是视频  2是图片  3是文本 0是其他
    private String type;

    //文件名
    private String fileName;

    //文件大小
    private String size;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;


   public Files(){}

    public Files(String userId, String url, String type, String fileName, String size) {
        this.userId = userId;
        this.url = url;
        this.type = type;
        this.fileName = fileName;
        this.size = size;
    }

    public Files(String url, String type, String fileName) {
        this.url = url;
        this.type = type;
        this.fileName = fileName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Files{" +
                "userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", fileName='" + fileName + '\'' +
                ", size='" + size + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
