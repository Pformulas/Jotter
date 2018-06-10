package service.impl;

import common.ResponseCode;
import common.ServerResponse;
import dao.NoteBookDao;
import dao.NoteDao;
import entity.Note;
import entity.NoteBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.INoteBookService;

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
            return ServerResponse.getServerResponse(ResponseCode.NOTEBOOK_IS_EXISTED);
        }
        //插入
        resultCount = noteBookDao.insertNotebook(noteBook);
        if(resultCount > 0){
            return ServerResponse.getServerResponse(ResponseCode.NOTEBOOK_CREATE_SUCCESS);
        }
        return ServerResponse.getServerResponse(ResponseCode.NOTEBOOK_CREATE_FAIL);
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
            return ServerResponse.getServerResponse(ResponseCode.NOTE_IS_EXISTED);
        }
        resultCount = noteDao.insert(note);
        if(resultCount > 0){
          return ServerResponse.getServerResponse(ResponseCode.NOTE_CREATE_SUCCESS);
        }
        return ServerResponse.getServerResponse(ResponseCode.NOTE_CREATE_FAIL);
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
}
