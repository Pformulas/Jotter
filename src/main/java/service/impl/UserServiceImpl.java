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
    @Override
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
        return ServerResponse.getServerResponse(UserResponse.SUCCESS);
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
        return ServerResponse.getServerResponse(UserResponse.SUCCESS);
    }
}
