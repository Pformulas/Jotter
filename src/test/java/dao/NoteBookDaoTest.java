package dao;

import entity.Note;
import entity.NoteBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by rzh on 2018/06/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class NoteBookDaoTest
{
    @Autowired
    private NoteBookDao noteBookDao;
    @Test
    public void checkNotebookName()
    {
        String name="杏花弦外雨";
        int result = noteBookDao.checkNotebookName(name);
        System.out.println("==========================");
        System.out.println("result:"+result);
        System.out.println("==========================");
    }

    @Test
    public void insertNotebook()
    {
        NoteBook noteBook = new NoteBook();
        noteBook.setUserId("1");
        noteBook.setNotebookName("杏花弦外雨");
        System.out.println("====================================");
        System.out.println(noteBook.getNotebookId());
        System.out.println(noteBook.getUserId());
        System.out.println(noteBook.getNotebookName());
        System.out.println("====================================");
        noteBookDao.insertNotebook(noteBook);
    }
}