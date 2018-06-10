package dao;

import entity.Note;

/**
 * Created by rzh on 2018/06/08
 */
public interface NoteDao
{
    int deleteByPrimaryKey(String noteId);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(String noteId);

    int updateByPrimaryKey(Note record);

    int checkNoteName(String noteTitle);
}
