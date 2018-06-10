package service;

import common.ServerResponse;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试用户业务类
 *
 * @author Fish
 * created by 2018-06-10 17:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class UserServiceTest {

    @Autowired
    private UserService userService = null;

    /**
     * 测试：专门检验用户是否合法，主要是用户名和密码
     */
    @Test
    public void testIsUserValidated() {
        User user = new User();
        user.setUsername("hhh");
        user.setPassword("4242");

        ServerResponse result = userService.isUserValidated(user);

        System.out.println(result);
    }

    /**
     * 测试：注册用户，必须传入 username 和 password
     */
    @Test
    public void testRegister() {
        User user = new User();
        user.setUsername("");
        user.setPassword("666");

        ServerResponse result = userService.register(user);

        System.out.println(result);
    }

    /**
     * 测试：用户登陆，用户名和密码必须传入
     */
    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("fish");
        user.setPassword("1997");

        ServerResponse result = userService.login(user);

        System.out.println(result);
    }
}
