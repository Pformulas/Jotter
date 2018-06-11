package dao;

import entity.Note;
import entity.NoteBook;
import org.omg.CORBA.INTERNAL;

/**
 * Created by rzh on 2018/06/08
 */
public interface NoteDao
{
    /**
     * noteid删除记录
     * @param noteId
     * @return
     */
    int deleteNoteByNoteId(String noteId);

    /**
     * 插入note
     * @param record
     * @return
     */
    int insertNote(Note record);

    /**
     * 用noteId查询笔记
     * @param noteId
     * @return
     */
    Note selectNoteByNoteId(String noteId);

    /**
     * 用noteid更新
     * @param record
     * @return
     */
    int updateNoteByNoteId(Note record);

    /**
     * 检查notename
     * @param noteTitle
     * @return
     */
    int checkNoteName(String noteTitle);

    /**
     * 更新note
     * @param note
     * @return
     */
    Integer updateNote(Note note);

    /**
     * 移动一个笔记，从一个分组到另外一个分组
     * @param note
     * @return
     */
    Integer moveNoteTo(Note note);

    /**
     * 查看这个用户是否拥有这个笔记
     * @param note
     * @return
     */
    Integer checkNoteByUserId(Note note);

    /**
     * 删除这个笔记本下的所有笔记
     * @param notebookId
     * @return
     */
    Integer deleteAllNoteByNotebookId(String notebookId);
}
