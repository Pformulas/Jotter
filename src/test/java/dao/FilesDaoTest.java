package dao;

import entity.Files;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 17:06
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class FilesDaoTest {

    @Autowired
    private FilesDao filesDao;

    @Test
    public void testSaveFile(){

    }
}
