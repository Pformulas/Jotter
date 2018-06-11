package controller;

import common.Const;
import common.ServerResponse;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户业务控制器
 *
 * @author Fish
 * created by 2018-06-10 19:49
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService = null;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册 API
     *
     * @param user 要注册的用户
     * @return 返回注册信息
     */
    @RequestMapping(
            path = "/register.do",
            method = {RequestMethod.POST},
            produces = {"application/json; charset=UTF8"}
    )
    @ResponseBody
    public ServerResponse register(User user) {
        return userService.register(user);
    }

    /**
     * 验证用户名 API
     *
     * @param username 要验证的用户名
     * @return 返回验证信息
     */
    @RequestMapping(
            path = "/check_username.do",
            method = {RequestMethod.POST},
            produces = {"application/json; charset=UTF8"}
    )
    @ResponseBody
    public ServerResponse checkUsername(String username) {
        return userService.isUserValidated(new User(username, Const.TEMP_PASSWORD));
    }

    /**
     * 登陆 API
     *
     * @param user    登陆的用户
     * @param session 存放登陆成功之后的信息
     * @return 返回登陆信息
     */
    @RequestMapping(
            value = "/login.do",
            method = {RequestMethod.POST},
            produces = {"application/json; charset=UTF8"}
    )
    @ResponseBody
    public ServerResponse login(User user, HttpSession session) {
        ServerResponse loginStatus = userService.login(user);

        // 如果登陆成功，就把登陆信息存在 session 中
        if (loginStatus.isSuccess()) {
            session.setAttribute(Const.USER_KEY, loginStatus.getData());
        }

        return loginStatus;
    }

    /**
     * 获取用户信息 API
     *
     * @param session 用户信息来源
     * @return 返回登陆信息
     */
    @RequestMapping(
            value = "/get_info.do",
            method = {RequestMethod.GET},
            produces = {"application/json; charset=UTF8"}
    )
    @ResponseBody
    public ServerResponse getInfo(HttpSession session) {
        // 得到目前登陆信息，然后获取全部信息
        return userService.getInfo((User) session.getAttribute(Const.USER_KEY));
    }

    /**
     * 更新用户信息 API
     *
     * @param session 用户账号信息来源
     * @return 返回登陆信息
     */
    @RequestMapping(
            value = "/update_info.do",
            method = {RequestMethod.POST},
            produces = {"application/json; charset=UTF8"}
    )
    @ResponseBody
    public ServerResponse updateInfo(User user, HttpSession session) {
        // 获取到现在的用户名
        String username = ((User) session.getAttribute(Const.USER_KEY)).getUsername();

        user.setUsername(username); // 设置用户名

        // 得到目前登陆信息，然后获取全部信息
        return userService.updateUser(user);
    }
}
