package service;

import common.ServerResponse;
import entity.Note;
import entity.NoteBook;
import org.springframework.stereotype.Service;

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
    public ServerResponse<String> insertNoteBook(NoteBook noteBook);

    public ServerResponse<String> inserNote(Note note);

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
}
