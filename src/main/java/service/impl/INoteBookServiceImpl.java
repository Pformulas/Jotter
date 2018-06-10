package service.impl;

import common.ServerResponse;
import common.response.NoteBookResponse;
import dao.NoteBookDao;
import dao.NoteDao;
import entity.Note;
import entity.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.INoteBookService;

import java.util.List;

/**
 * Created by rzh on 2018/06/08
 */
@Service("iNoteBookService")
public class INoteBookServiceImpl implements INoteBookService
{
    @Autowired
    private NoteBookDao noteBookDao;

    @Autowired
    private NoteDao noteDao;
    /**
     * 创建新的notebook文件夹
     *
     * @param noteBook
     * @return
     */
    public ServerResponse<String> insertNoteBook(NoteBook noteBook)
    {
        //检查notebookname是否重复
        int resultCount = noteBookDao.checkNotebookName(noteBook.getNotebookName());
        if(resultCount > 0){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_IS_EXISTED);
        }
        //插入
        resultCount = noteBookDao.insertNotebook(noteBook);
        if(resultCount > 0){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_CREATE_SUCCESS);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_CREATE_FAIL);
    }

    /**
     * 新建笔记
     * @param note
     * @return
     */
    public ServerResponse<String> inserNote(Note note)
    {
        //检查笔记名
        int resultCount = noteDao.checkNoteName(note.getNoteTitle());
        if(resultCount > 0){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTE_IS_EXISTED);
        }
        resultCount = noteDao.insert(note);
        if(resultCount > 0){
          return ServerResponse.getServerResponse(NoteBookResponse.NOTE_CREATE_SUCCESS);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.NOTE_CREATE_FAIL);
    }

    /**
     * 通过笔记本名称删除笔记本同时删除里面的笔记
     *
     * @param notebookName
     * @return
     */
    public ServerResponse<String> deleteNotebook(String notebookName)
    {
        //判断这个Notebook是否属于这个用户
        //
        return null;
    }

    /**
     * 根据笔记id查询那个笔记
     * @param noteId
     * @return
     */
    @Override
    public ServerResponse<Note> showNote(String noteId) {
        if(noteId == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        Note note = noteDao.selectNoteByNoteId(noteId);
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS, note);
    }

    /**
     * 根据笔记本id查询那个笔记本
     * @param notebookId
     * @return
     */
    @Override
    public ServerResponse<NoteBook> showNotebook(String notebookId) {
        if(notebookId == null){
            return  ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        NoteBook noteBook = noteBookDao.selectNotebookByNotebookId(notebookId);
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS, noteBook);
    }

    /**
     * 根据笔记本的id获取所有属于它的笔记本信息
     * @param notebookId
     * @return
     */
    @Override
    public ServerResponse<List<Note>> selectNotesByNotebookId(String notebookId) {
        if(notebookId == null){
            return  ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        List<Note> notes = noteBookDao.selectNotesByNotebookId(notebookId);
        return  ServerResponse.getServerResponse(NoteBookResponse.SUCCESS, notes);
    }


}
