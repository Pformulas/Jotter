package dao;

import entity.NoteBook;

/**
 * Created by rzh on 2018/06/08
 */
public interface NoteBookMapper
{
    public int checkNotebookName(String notebook_id);

    public int insertNotebook(NoteBook noteBook);
}
