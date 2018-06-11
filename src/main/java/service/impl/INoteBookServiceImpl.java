package service.impl;

import Utils.PageUtil;
import common.ServerResponse;
import common.response.NoteBookResponse;
import common.response.UserResponse;
import dao.NoteBookDao;
import dao.NoteDao;
import entity.Note;
import entity.NoteBook;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.INoteBookService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

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
    public ServerResponse<String> insertNoteBook(NoteBook noteBook, User user)
    {
        //判断用户是否登录
        if(user == null){
            //用户未登录
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }
        //检查notebookname是否重复
        int resultCount = noteBookDao.checkNotebookName(noteBook.getNotebookName());
        if(resultCount > 0){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_IS_EXISTED);
        }
        //得到用户的UserID
        noteBook.setUserId(user.getUserId());
        noteBook.setNotebookId(UUID.randomUUID().toString());
        //判断是否新建成功
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
    public ServerResponse<String> inserNote(Note note,User user,String notebookId)
    {
        //判断用户是否登录
        if (user == null)
        {
            //用户未登录
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }
        if(notebookId == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        //检查这个user是否有这个文件夹
        int resultCount = noteBookDao.checkNoteBookByUserId(user.getUserId(),notebookId);
        if(resultCount <= 0){
            //说明恶意登录添加其他人的笔记
            return ServerResponse.getServerResponse(UserResponse.ILLEGAL_ARGUMENT);
        }
        //检查笔记名
        resultCount = noteDao.checkNoteName(note.getNoteTitle());
        if(resultCount > 0){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTE_IS_EXISTED);
        }
        note.setUserId(user.getUserId());
        note.setNotebookId(notebookId);
        note.setNoteId(UUID.randomUUID().toString());
        resultCount = noteDao.insertNote(note);
        //判断是否新建成功
        if(resultCount > 0){
          return ServerResponse.getServerResponse(NoteBookResponse.NOTE_CREATE_SUCCESS);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.NOTE_CREATE_FAIL);
    }

    /**
     * 根据笔记id查询那个笔记
     * @param noteId
     * @return
     */
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
    public ServerResponse selectNotesByNotebookId(Integer page, String notebookId) {
        if(notebookId == null){
            return  ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        PageUtil.toPage(page);
        List<Note> notes = noteBookDao.selectNotesByNotebookId(notebookId);
        if(notes.size() <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.RESULT_IS_NULL);
        }
        return  ServerResponse.getServerResponse(NoteBookResponse.SUCCESS, PageUtil.pageInfo(notes));
    }

    /**
     * 用户用userId查询他自己的全部笔记本
     * @param userId
     * @return
     */
    public ServerResponse selectNoteBooksByUserId(Integer page, String userId) {
        if(userId == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        PageUtil.toPage(page);
        List<NoteBook> noteBooks = noteBookDao.selectNoteBooksByUserId(userId);
        if(noteBooks.size() <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.RESULT_IS_NULL);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS, PageUtil.pageInfo(noteBooks));
    }

    /**
     * 更新笔记本，需要userId, notebookName, notebookId
     * @param noteBook
     * @return
     */
    public ServerResponse updateNoteBook(NoteBook noteBook) {
        if(noteBook == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
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

    public ServerResponse updateNote(Note note) {
        if(note == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        if(note.getNoteId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTE_ID_NULL);
        }
        if(note.getUserId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.USER_ID_NULL);
        }
        if(note.getNotebookId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_ID_NULL);
        }
        if(note.getNoteTitle() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTE_TITLE_NULL);
        }
        if(note.getNoteDetail() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTE_DETAIL_NULL);
        }
        Integer result = noteDao.updateNote(note);
        if (result <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.UPDATE_ID_NULL);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS);
    }

    /**
     * 将一个笔记从一个笔记本移动到另外一个笔记本
     * 参数：  userId,notebookId,noteId
     * @param note
     * @return
     */
    public ServerResponse moveNoteTo(Note note) {
        if(note == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        if(note.getNoteId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTE_ID_NULL);
        }
        if(note.getNotebookId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_ID_NULL);
        }
        if(note.getUserId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.USER_ID_NULL);
        }
        //判定这个笔记本是不是这个用户的
        Integer check =  noteBookDao.checkNoteBookByUserId(note.getUserId(), note.getNotebookId());
        if(check <= 0){
            return ServerResponse.getServerResponse(UserResponse.ILLEGAL_ARGUMENT);
        }
        Integer result = noteDao.moveNoteTo(note);
        if (result <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.UPDATE_ID_NULL);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS);
    }

    /**
     * 删除一个笔记 需要userId  noteId
     * @param note
     * @return
     */
    public ServerResponse deleteNote(Note note) {
        if(note == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        if(note.getUserId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.USER_ID_NULL);
        }
        if(note.getNoteId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTE_ID_NULL);
        }
        
        Integer check = noteDao.checkNoteByUserId(note);
        if(check <= 0){
            return ServerResponse.getServerResponse(UserResponse.ILLEGAL_ARGUMENT);
        }
        Integer result = noteDao.deleteNoteByNoteId(note.getNoteId());
        if (result <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.UPDATE_ID_NULL);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS);
    }

    public ServerResponse<Integer> deleteNotebook(NoteBook noteBook) {
        if(noteBook == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        if(noteBook.getUserId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.USER_ID_NULL);
        }
        if(noteBook.getNotebookId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.NOTEBOOK_ID_NULL);
        }
        Integer check = noteBookDao.checkNoteBookByUserId(noteBook.getUserId(), noteBook.getNotebookId());
        if(check <= 0){
            return ServerResponse.getServerResponse(UserResponse.ILLEGAL_ARGUMENT);
        }
        Integer deleteNoteNum = noteDao.deleteAllNoteByNotebookId(noteBook.getNotebookId());
        Integer result = noteBookDao.deleteNotebookByNotebookId(noteBook.getNotebookId());
        if (result <= 0){
            return ServerResponse.getServerResponse(NoteBookResponse.UPDATE_ID_NULL);
        }
        return ServerResponse.getServerResponse(NoteBookResponse.SUCCESS, deleteNoteNum);
    }

}
