package service;

import common.ServerResponse;
import entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户业务接口类
 *
 * @author Fish
 * created by 2018-06-10 15:25
 */
@Service
public interface UserService {

    /**
     * 注册用户，必须传入 username 和 password
     *
     * @param user 注册的新用户
     * @return 返回注册情况
     */
    ServerResponse register(User user);
}
