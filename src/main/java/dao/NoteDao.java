package dao;

import entity.Note;

/**
 * Created by rzh on 2018/06/08
 */
public interface NoteDao
{
    /**
     * 用主键删除
     * @param noteId
     * @return
     */
    int deleteByPrimaryKey(String noteId);

    /**
     * 插入note
     * @param record
     * @return
     */
    int insert(Note record);

    /**
     * 动态插入note
     * @param record
     * @return
     */
    int insertSelective(Note record);

    /**
     * 用主键查询
     * @param noteId
     * @return
     */
    Note selectByPrimaryKey(String noteId);

    /**
     * 用主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Note record);

    /**
     * 检查notename
     * @param noteTitle
     * @return
     */
    int checkNoteName(String noteTitle);

    /**
     * 用notetitle删除
     * @param noteTitle
     * @return
     */
    int deleteByNoteTitle(String noteTitle);

}
