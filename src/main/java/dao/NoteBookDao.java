package dao;

import entity.NoteBook;

/**
 * Created by rzh on 2018/06/08
 */
public interface NoteBookDao
{
    /**
     * 检查notebookname是否存在
     * @param notebook_id
     * @return
     */
    int checkNotebookName(String notebook_id);

    /**
     * 插入notebook
     * @param noteBook
     * @return
     */
    int insertNotebook(NoteBook noteBook);

    /**
     * 用bookname删除notebook
     * @param notebookName
     * @return
     */
    int deleteNotebookByName(String notebookName);
}
