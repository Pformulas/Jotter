package dao;

import entity.Note;
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
public class NoteDaoTest
{
    @Autowired
    private NoteDao noteDao;
    @Test
    public void deleteByPrimaryKey()
    {
    }

    @Test
    public void insert()
    {
        Note note = new Note();
        note.setNotebookId("8eef6498-53a9-4dd1-94ed-924a4bb31f3e");
        note.setUserId("2");
        note.setNoteTitle("方圆几里");
        note.setNoteDetail("感觉会压抑的样子，我不算很自私，也越来越懂事");
        noteDao.insert(note);
    }

    @Test
    public void insertSelective()
    {
    }

    @Test
    public void selectByPrimaryKey()
    {
    }

    @Test
    public void updateByPrimaryKey()
    {
    }

    @Test
    public void checkNoteName()
    {
        String name="方圆几里";
        int result = noteDao.checkNoteName(name);
        System.out.println("==========================");
        System.out.println("result:" + result);
        System.out.println("==========================");
    }
}