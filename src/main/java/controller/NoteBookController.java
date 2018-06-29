package controller;

import common.Const;
import common.ServerResponse;
import common.response.UserResponse;
import entity.Note;
import entity.NoteBook;
import entity.User;
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
@RequestMapping("/notebook")
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
    @RequestMapping(value = "/insert_notebook.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertNoteBook(NoteBook noteBook, HttpSession session)
    {
        User user = (User) session.getAttribute(Const.USER_KEY);
        return iNoteBookService.insertNoteBook(noteBook, user);
    }

    /**
     * 新建笔记
     *
     * @param note
     * @return
     */
    @RequestMapping(value = "/insert_note.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> inserNote(Note note, HttpSession session,String notebookId)
    {
        User user = (User) session.getAttribute(Const.USER_KEY);
        return iNoteBookService.inserNote(note, user,notebookId);
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
    @RequestMapping(path = "/show_note.do", produces = {"application/json;charset=UTF8"})
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
    @RequestMapping(path = "/show_notebook.do", produces = {"application/json;charset=UTF8"})
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
    @RequestMapping(path = "/show_notes_of_notebook.do", produces = {"application/json;charset=UTF8"})
    public Object showNotesOfNotebook(Integer page, String notebookId)
    {
        return iNoteBookService.selectNotesByNotebookId(page, notebookId);
    }

    /**
     * 获取这个用户的所有笔记本
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/show_notebook_of_userId.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse showNoteBookOfUserId(Integer page, HttpSession session)
    {
        User user = (User) session.getAttribute(Const.USER_KEY);
        if (user == null) {
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }

        return iNoteBookService.selectNoteBooksByUserId(page, user.getUserId());
    }

    /**
     * 更新笔记本 需要参数  userId notebookId notebookname
     * @param noteBook
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/update_notebook.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse updateNotebook(NoteBook noteBook)
    {
        return iNoteBookService.updateNoteBook(noteBook);
    }

    /**
     * 更新笔记 需要参数 userId notebookid noteid notetitle notedetail
     * @param note
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/update_note.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse updateNote(Note note)
    {
        return iNoteBookService.updateNote(note);
    }


    /**
     * 将一个笔记从一个笔记本移动到另外一个笔记本
     * 参数：  userId,notebookId,noteId
     * @param note
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/move_note_to.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse moveNoteTo(Note note)
    {
        return iNoteBookService.moveNoteTo(note);
    }

    /**
     * 删除一个笔记
     * 参数： userId  noteId
     * @param note
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/delete_note.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse deleteNote(Note note)
    {
        return iNoteBookService.deleteNote(note);
    }

    /**
     * 删除笔记本，并且删除笔记本下的所有笔记
     * 参数 ： userId notebookId
     * @param noteBook
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/delete_notebook.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse deleteNotebook(NoteBook noteBook)
    {
        return iNoteBookService.deleteNotebook(noteBook);
    }


}
