package entity;

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
    private int type;

    //文件名
    private String fileName;

    public Files(){}

    public Files(String userId, String url, int type, String fileName) {
        this.userId = userId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "File{" +
                "userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
