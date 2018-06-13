package dao;

import entity.Files;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

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

//        File file = new File("E:/修改前.txt");
//        System.out.println(file.getName());
//        File rename = new File("E:/修改后.txt");
//        System.out.println(file.renameTo(rename));



    }
}
