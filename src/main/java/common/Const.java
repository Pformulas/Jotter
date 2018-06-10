package common;

/**
 * 常用常量类
 *
 * @author Fish
 * created by 2018-06-10 16:25
 */
public final class Const {

    // 用户名最大长度
    public static final int MAX_LENGTH_OF_USERNAME = 64;

    // 密码最大长度
    public static final int MAX_LENGTH_OF_PASSWORD = 64;

    // 当用户对象作为 key 值时使用
    public static final String USER_KEY = "userKey";

    // 验证用户名是否存在时用的假密码，或者说临时密码
    public static final String TEMP_PASSWORD = "tempPassword";
}
