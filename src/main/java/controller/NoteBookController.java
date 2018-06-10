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
     * @param noteBook
     * @return
     */
    @RequestMapping(value = "notebook",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> insertNoteBook(NoteBook noteBook){
        return iNoteBookService.insertNoteBook(noteBook);
    }

    /**
     * 新建笔记
     * @param note
     * @return
     */
    @RequestMapping(value = "note",method = RequestMethod.GET)
    public ServerResponse<String> inserNote(Note note){
        return iNoteBookService.inserNote(note);
    }

    /**
     * 通过笔记本名称删除笔记本同时删除里面的笔记
     * @param notebookName
     * @return
     */
    public ServerResponse<String> deleteNotebook(String notebookName){
        return null;
    }
}
