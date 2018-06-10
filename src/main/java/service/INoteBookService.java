package service;

import common.ServerResponse;
import entity.Note;
import entity.NoteBook;
import org.springframework.stereotype.Service;

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
}
