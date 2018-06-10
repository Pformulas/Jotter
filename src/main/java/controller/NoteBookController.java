package controller;

import common.ServerResponse;
import entity.Note;
import entity.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ServerResponse<String> insertNoteBook(NoteBook noteBook){
        return iNoteBookService.insertNoteBook(noteBook);
    }

    /**
     * 新建笔记
     * @param note
     * @return
     */
    public ServerResponse<String> inserNote(Note note){
        return iNoteBookService.inserNote(note);
    }
}
