package service.impl;

import common.ServerResponse;
import dao.NoteBookMapper;
import dao.NoteMapper;
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
    private NoteBookMapper noteBookMapper;

    @Autowired
    private NoteMapper noteMapper;
    /**
     * 创建新的notebook文件夹
     *
     * @param noteBook
     * @return
     */
    public ServerResponse<String> insertNoteBook(NoteBook noteBook)
    {
        //检查notebookname是否重复
        int resultCount = noteBookMapper.checkNotebookName(noteBook.getNotebookName());
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage("笔记本名字已存在");
        }
        //插入
        resultCount = noteBookMapper.insertNotebook(noteBook);
        if(resultCount > 0){
            return ServerResponse.createBySuccessMessage("笔记本创建成功");
        }
        return ServerResponse.createByErrorMessage("笔记本创建失败");
    }

    /**
     * 新建笔记
     * @param note
     * @return
     */
    public ServerResponse<String> inserNote(Note note)
    {
        //检查笔记名
        int resultCount = noteMapper.checkNoteName(note.getNoteTitle());
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage("笔记名已存在");
        }
        resultCount = noteMapper.insert(note);
        if(resultCount > 0){
          return ServerResponse.createBySuccessMessage("笔记新建成功");
        }
        return ServerResponse.createByErrorMessage("笔记新建失败");
    }
}
