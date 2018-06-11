package service;

import common.ServerResponse;
import entity.Note;
import entity.NoteBook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by rzh on 2018/06/08
 */
@Service
public interface INoteBookService
{
    /**
     * 插入新的notebook文件夹
     * @param noteBook
     * @return
     */
    public ServerResponse<String> insertNoteBook(NoteBook noteBook, HttpSession session);

    public ServerResponse<String> inserNote(Note note,HttpSession session);

    /**
     * 通过笔记本名称删除笔记本同时删除里面的笔记
     * @param notebookName
     * @return
     */
    public ServerResponse<String> deleteNotebook(String notebookName);

    /**
     * 通过noteId查找一个笔记
     * @param noteId
     * @return
     */
    public ServerResponse<Note> showNote(String noteId);

    /**
     * 通过notebookId查找一个笔记本
     * @param notebookId
     * @return
     */
    public ServerResponse<NoteBook> showNotebook(String notebookId);

    /**
     * 通过笔记本id查找它所有的笔记
     * @param notebookId
     * @return
     */
    public ServerResponse<List<Note>> selectNotesByNotebookId(String notebookId);

    /**
     * 用户通过他的id获取他的全部笔记本
     * @param userId
     * @return
     */
    public ServerResponse<List<NoteBook>> selectNoteBooksByUserId(String userId);

    /**
     * 更新笔记本
     * 需要userid, notebookName, notebookId
     * @param noteBook
     * @return
     */
    public ServerResponse updateNoteBook(NoteBook noteBook);
}
