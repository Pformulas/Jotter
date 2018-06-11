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

import java.util.Date;
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
        if(note == null){
            return ServerResponse.getServerResponse(NoteBookResponse.RESULT_IS_NULL);
        }
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
        if(noteBook == null){
            return ServerResponse.getServerResponse(NoteBookResponse.RESULT_IS_NULL);
        }
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
        if(notes.size() <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.RESULT_IS_NULL);
        }
        return  ServerResponse.getServerResponse(NoteBookResponse.SUCCESS, notes);
    }

    /**
     * 用户用userId查询他自己的全部笔记本
     * @param userId
     * @return
     */
    @Override
    public ServerResponse<List<NoteBook>> selectNoteBooksByUserId(String userId) {
        if(userId == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        List<NoteBook> noteBooks = noteBookDao.selectNoteBooksByUserId(userId);
        if(noteBooks.size() <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.RESULT_IS_NULL);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS, noteBooks);
    }

    /**
     * 更新笔记本，需要userId, notebookName, notebookId
     * @param noteBook
     * @return
     */
    @Override
    public ServerResponse updateNoteBook(NoteBook noteBook) {
        if(noteBook == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        System.out.println(noteBook);
        if(noteBook.getNotebookId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_ID_NULL);
        }
        if(noteBook.getNotebookName() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_NAME_NULL);
        }
        if(noteBook.getUserId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.USER_ID_NULL);
        }
        Integer result = noteBookDao.updateNoteBook(noteBook);
        if (result <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.UPDATE_ID_NULL);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS);
    }

}
