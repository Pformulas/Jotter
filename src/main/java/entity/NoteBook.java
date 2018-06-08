package entity;

import java.util.Date;
import java.util.UUID;

/**
 * 笔记本实体类，id 使用 UUID 生成
 *
 * @author Fish
 * created by 2018-06-08 17:23
 */
public class NoteBook {
    private String notebookId = null;

    private String userId = null;

    private String notebookName = null;

    private Date notebookCreateTime = null;

    private Date notebookUpdateTime = null;

    public NoteBook() {
        this.notebookId = UUID.randomUUID().toString();
    }

    public String getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(String notebookId) {
        this.notebookId = notebookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotebookName() {
        return notebookName;
    }

    public void setNotebookName(String notebookName) {
        this.notebookName = notebookName;
    }

    public Date getNotebookCreateTime() {
        return notebookCreateTime;
    }

    public void setNotebookCreateTime(Date notebookCreateTime) {
        this.notebookCreateTime = notebookCreateTime;
    }

    public Date getNotebookUpdateTime() {
        return notebookUpdateTime;
    }

    public void setNotebookUpdateTime(Date notebookUpdateTime) {
        this.notebookUpdateTime = notebookUpdateTime;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "notebookId='" + notebookId + '\'' +
                ", userId='" + userId + '\'' +
                ", notebookName='" + notebookName + '\'' +
                ", notebookCreateTime=" + notebookCreateTime +
                ", notebookUpdateTime=" + notebookUpdateTime +
                '}';
    }
}
