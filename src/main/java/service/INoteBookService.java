package service;

import common.ServerResponse;
import entity.Note;
import entity.NoteBook;
import entity.User;
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
    public ServerResponse<String> insertNoteBook(NoteBook noteBook, User user);

    public ServerResponse<String> inserNote(Note note,User user,String notebookId);

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
    public ServerResponse selectNotesByNotebookId(Integer page, String notebookId);

    /**
     * 用户通过他的id获取他的全部笔记本
     * @param userId
     * @return
     */
    public ServerResponse selectNoteBooksByUserId(Integer page, String userId);

    /**
     * 更新笔记本
     * 需要userid, notebookName, notebookId
     * @param noteBook
     * @return
     */
    public ServerResponse updateNoteBook(NoteBook noteBook);

    /**
     * 更新笔记
     * @param note
     * @return
     */
    public ServerResponse updateNote(Note note);

    /**
     * 将一个笔记从一个分组移动到另外一个
     * @param note
     * @return
     */
    public ServerResponse moveNoteTo(Note note);

    /**
     * 删除一个笔记
     * @param note
     * @return
     */
    public ServerResponse deleteNote(Note note);

    /**
     * 删除笔记本和笔记本下的笔记
     * @param noteBook
     * @return
     */
    public ServerResponse deleteNotebook(NoteBook noteBook);
}
