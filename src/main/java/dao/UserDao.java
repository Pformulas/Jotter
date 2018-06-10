package dao;

import entity.User;

/**
 * 用户模块相关 dao 层
 *
 * @author Fish
 * created by 2018-06-10 12:32
 */
public interface UserDao {

    /**
     * 保存一个用户对象
     *
     * @param user 要被保存的对象
     * @return 返回影响的行数，0 表示保存失败，1 表示成功
     */
    int saveUser(User user);
}
