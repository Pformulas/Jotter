package dao;

import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Fish
 * created by 2018-06-10 14:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml"
})
public class UserDaoTest {

    @Autowired
    UserDao userDao = null;

    @Test
    public void testSaveUser() {
        User user = new User("fish", "1997");

        int affect = userDao.saveUser(user);

        System.out.println(affect);
    }
}
