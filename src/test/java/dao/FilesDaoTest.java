package dao;

import Utils.FileNiceUtil;
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

        String currentPath="D:\\apache-tomcat-8.5.29\\webapps\\Jotter\\File\\imp\\文件操作.txt" ;
        String newPath = "D:\\apache-tomcat-8.5.29\\webapps\\Jotter\\File\\imp\\哈哈.txt";
        File oldFile = new File(currentPath);
        File newFile = new File(newPath);
        oldFile.renameTo(newFile);
        System.out.println(newFile.getName());


    }
}
