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

    /**
     * 测试：保存一个用户对象
     */
    @Test
    public void testSaveUser() {
        User user = new User("fish", "1997");

        int affect = userDao.saveUser(user);

        System.out.println(affect);
    }

    /**
     * 测试：统计用户名个数
     */
    @Test
    public void testCountUsername() {
        String username = "fish";

        int affect = userDao.countUsername(username);

        System.out.println(affect);
    }

    /**
     * 测试：通过用户名和密码获取相关信息
     */
    @Test
    public void testGetUserByUsernameAndPassword() {
        String username = "fish";
        String password = "1997";

        User user = userDao.getUserByUsernameAndPassword(username, password);

        System.out.println(user);
    }

    /**
     * 测试：更新用户信息
     */
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("madao");
        user.setIntro("我是哈哈哈");
        user.setQq("99999999");

        int affect = userDao.updateUser(user);

        System.out.println(affect);
    }
}
