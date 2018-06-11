package service.impl;

import common.Const;
import common.ServerResponse;
import common.response.UserResponse;
import dao.UserDao;
import entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

/**
 * 用户业务实现类
 *
 * @author Fish
 * created by 2018-06-10 15:29
 */
@Service
public class UserServiceImpl implements UserService {

    // 记录日志
    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    private UserDao userDao = null;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 注册用户，必须传入 username 和 password
     *
     * @param user 注册的新用户
     * @return 返回注册情况
     */
    public ServerResponse register(User user) {
        ServerResponse userValidateStatus = isUserValidated(user);

        // 判断 user 是否合法，如果不合法，就返回错误信息
        if (!userValidateStatus.isSuccess()) {
            return userValidateStatus;
        }

        // 用户合法，可以注册
        int affect = 0;
        try {
            affect = userDao.saveUser(user);
        } catch (Exception e) {
            log.error("用户注册时发生异常：" + user.toString(), e);
        }

        // 判断注册是否成功，如果小于 0，说明注册不成功
        if (affect <= 0) {
            return ServerResponse.getServerResponse(UserResponse.REGISTER_FAILED);
        }

        // 验证注册通过
        return ServerResponse.getServerResponse(UserResponse.REGISTER_SUCCESS);
    }

    /**
     * 专门检验用户是否合法，主要是用户名和密码
     *
     * @param user 要被检验的用户
     * @return true 用户合法，可以使用，false 用户不合法，不能使用
     */
    public ServerResponse isUserValidated(User user) {
        // 用户对象不能为空
        if (user == null) {
            return ServerResponse.getServerResponse(UserResponse.ILLEGAL_ARGUMENT);
        }

        // 如果用户名或者密码任一个为空，注册失败
        boolean usernameIsNull = user.getUsername() == null || "".equals(user.getUsername());
        boolean passwordIsNull = user.getPassword() == null || "".equals(user.getPassword());
        if (usernameIsNull || passwordIsNull) {
            return ServerResponse.getServerResponse(UserResponse.USERNAME_OR_PASSWORD_IS_NULL);
        }

        // 判断用户名和密码长度是否合法
        boolean usernameIsTooLong = user.getUsername().length() >= Const.MAX_LENGTH_OF_USERNAME;
        boolean passwordIsTooLong = user.getPassword().length() >= Const.MAX_LENGTH_OF_PASSWORD;
        if (usernameIsTooLong || passwordIsTooLong) {
            return ServerResponse.getServerResponse(UserResponse.USERNAME_OR_PASSWORD_IS_TOO_LONG);
        }

        // 判断用户名是否存在
        if (userDao.countUsername(user.getUsername()) > 0) {
            return ServerResponse.getServerResponse(UserResponse.USERNAME_IS_EXISTED);
        }

        // 所有验证都通过了，返回成功
        return ServerResponse.getServerResponse(UserResponse.CHECK_SUCCESS);
    }

    /**
     * 用户登陆，用户名和密码必须传入
     *
     * @param user 要登陆的用户
     * @return 返回登陆信息
     */
    public ServerResponse login(User user) {
        // 判断 user 是否存在，不存在就返回用户提示
        if (!(isUserValidated(user).getStatus() == UserResponse.USERNAME_IS_EXISTED.getCode())) {
            return ServerResponse.getServerResponse(UserResponse.LOGIN_ERROR);
        }

        // 进数据库查询，看看这个用户名和密码有没有对应的记录
        user = userDao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (user == null) {
            // 没有记录，说明用户名或密码不对
            return ServerResponse.getServerResponse(UserResponse.LOGIN_ERROR);
        }

        // 如果查询到了记录，就说明用户名和密码都对了，登陆成功
        return ServerResponse.getServerResponse(UserResponse.LOGIN_SUCCESS, user);
    }

    /**
     * 获取用户信息
     *
     * @param user 要被获取的用户
     * @return 返回用户信息
     */
    public ServerResponse getInfo(User user) {
        // 判断 user 是否为空
        if (user == null) {
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }

        user = userDao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        // 如果得到的是 null，说明找不到相应用户，不过这通常不会发生，除非他的登陆是非法的
        if (user == null) {
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }

        // 得到想要的数据了，返回出去
        return ServerResponse.getServerResponse(UserResponse.SUCCESS, user);
    }

    /**
     * 根据 user 对象更新信息
     *
     * @param user 用户信息
     * @return 返回更新情况
     */
    public ServerResponse updateUser(User user) {
        // 判断 user 是否为空，如果得到的是 null，说明找不到相应用户，不过这通常不会发生，除非他的登陆是非法的
        if (user == null) {
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }

        // 判断 user 是否合法
        boolean usernameIsIllegal = user.getUsername() == null || "".equals(user.getUsername());
        if (usernameIsIllegal) {
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }

        // 判断执行成功没有
        int affect = userDao.updateUser(user);
        if (affect <= 0) {
            return ServerResponse.getServerResponse(UserResponse.UPDATE_INFO_FAILED);
        }

        // 更新成功
        return ServerResponse.getServerResponse(UserResponse.SUCCESS);
    }
}
