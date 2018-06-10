package dao;

import entity.NoteBook;

/**
 * Created by rzh on 2018/06/08
 */
public interface NoteBookDao
{
    int checkNotebookName(String notebook_id);

    int insertNotebook(NoteBook noteBook);
}
