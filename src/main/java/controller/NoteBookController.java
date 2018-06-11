package controller;

import common.ServerResponse;
import entity.Note;
import entity.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.INoteBookService;

import javax.servlet.http.HttpSession;

/**
 * Created by rzh on 2018/06/08
 */
@Controller
@RequestMapping("/notebook/")
public class NoteBookController
{
    @Autowired
    private INoteBookService iNoteBookService;

    /**
     * 新建笔记本
     *
     * @param noteBook
     * @return
     */
    @RequestMapping(value = "insert_notebook.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> insertNoteBook(NoteBook noteBook, HttpSession session)
    {
        return iNoteBookService.insertNoteBook(noteBook, session);
    }

    /**
     * 新建笔记
     *
     * @param note
     * @return
     */
    @RequestMapping(value = "insert_note.do", method = RequestMethod.GET)
    public ServerResponse<String> inserNote(Note note, HttpSession session)
    {
        return iNoteBookService.inserNote(note, session);
    }

    /**
     * 通过笔记本名称删除笔记本同时删除里面的笔记
     *
     * @param notebookName
     * @return
     */
    public ServerResponse<String> deleteNotebook(String notebookName)
    {
        return null;
    }

    /**
     * 根据笔记id查找一个笔记
     *
     * @param noteId 笔记id
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "show_note.do", produces = {"application/json;charset=UTF8"})
    public Object showNote(String noteId)
    {
        return iNoteBookService.showNote(noteId);
    }

    /**
     * 根据笔记本id查找一个笔记本
     *
     * @param notebookId 笔记本id
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "show_notebook.do", produces = {"application/json;charset=UTF8"})
    public Object showNotebook(String notebookId)
    {
        return iNoteBookService.showNotebook(notebookId);
    }

    /**
     * 根据笔记本id获取所有他的笔记
     *
     * @param notebookId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "show_notes_of_notebook.do", produces = {"application/json;charset=UTF8"})
    public Object showNotesOfNotebook(String notebookId)
    {
        return iNoteBookService.selectNotesByNotebookId(notebookId);
    }

    /**
     * 获取这个用户的所有笔记本
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "show_notebook_of_userId.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse showNoteBookOfUserId(String userId)
    {
        return iNoteBookService.selectNoteBooksByUserId(userId);
    }

    @ResponseBody
    @RequestMapping(path = "update_notebook", produces = {"application/json;charset=UTF8"})
    public ServerResponse updateNotebook(NoteBook noteBook)
    {
        return iNoteBookService.updateNoteBook(noteBook);
    }
}
