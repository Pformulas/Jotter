package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 统计用户名个数
     *
     * @param username 用户名
     * @return 返回个数，由于这里不允许用户名重复，所以最多返回 1 条
     */
    int countUsername(String username);

    /**
     * 通过用户名和密码获取相关信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回 User 对象
     */
    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 更新用户信息
     *
     * @param user 要被更新的用户
     * @return 返回影响的行数
     */
    int updateUser(User user);
}
