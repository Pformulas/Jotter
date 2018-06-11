package dao;

import entity.Note;
import entity.NoteBook;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Not;

import java.util.List;

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

    /**
     * 用notebookId查询笔记本
     * @param notebookId
     * @return
     */
    NoteBook selectNotebookByNotebookId(String notebookId);

    /**
     * 用notebookId查询所有属于它的笔记
     * @param notebookId
     * @return
     */
    List<Note> selectNotesByNotebookId(String notebookId);

    /**
     * 用userId查询所有他自己的笔记本
     * @param userId
     * @return
     */
    List<NoteBook> selectNoteBooksByUserId(String userId);

    /**
     * 更新笔记本
     * @param noteBook
     * @return
     */
    int updateNoteBook(NoteBook noteBook);

    int checkNoteBookByUserId(@Param("userId") String userId, @Param("notebookId") String notebookId);
}
