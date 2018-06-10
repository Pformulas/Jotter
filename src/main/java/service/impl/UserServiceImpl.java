package service.impl;

import common.ResponseCode;
import common.ServerResponse;
import entity.User;
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

    /**
     * 注册用户，必须传入 username 和 password
     *
     * @param user 注册的新用户
     * @return 返回注册情况
     */
    @Override
    public ServerResponse register(User user) {

        return null;
    }

    /**
     * 专门检验用户是否合法，主要是用户名和密码
     *
     * @param user 要被检验的用户
     * @return true 用户合法，可以使用，false 用户不合法，不能使用
     */
    public ServerResponse isUserValidated(User user) {
        if (user == null) {
            // 用户对象不能为空
            return null;
        }

        boolean usernameIsNull = user.getUsername() == null || "".equals(user.getUsername());
        boolean passwordIsNull = user.getPassword() == null || "".equals(user.getPassword());
        if (usernameIsNull || passwordIsNull) {
            // 如果用户名或者密码任一个为空，注册失败
        }
        return null;
    }
}
