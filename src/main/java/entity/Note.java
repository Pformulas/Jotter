package entity;

import java.util.Date;
import java.util.UUID;

/**
 * 笔记实体类，id 值使用 UUID 生成
 *
 * @author Fish
 * created by 2018-06-08 16:47
 */
public class Note {
    private String noteId = null;

    private String notebookId = null;

    private String userId = null;

    private String noteTitle = null;

    private String noteDetail = null;

    private Date noteCreateTime = null;

    private Date noteUpdateTime = null;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
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

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDetail() {
        return noteDetail;
    }

    public void setNoteDetail(String noteDetail) {
        this.noteDetail = noteDetail;
    }

    public Date getNoteCreateTime() {
        return noteCreateTime;
    }

    public void setNoteCreateTime(Date noteCreateTime) {
        this.noteCreateTime = noteCreateTime;
    }

    public Date getNoteUpdateTime() {
        return noteUpdateTime;
    }

    public void setNoteUpdateTime(Date noteUpdateTime) {
        this.noteUpdateTime = noteUpdateTime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId='" + noteId + '\'' +
                ", notebookId='" + notebookId + '\'' +
                ", userId='" + userId + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDetail='" + noteDetail + '\'' +
                ", noteCreateTime=" + noteCreateTime +
                ", noteUpdateTime=" + noteUpdateTime +
                '}';
    }
}
