package common.response;

import common.ResponseStatus;

/**
 * 用户模块响应情况枚举类
 *
 * @author Fish
 * created by 2018-06-10 17:06
 */
public enum UserResponse implements ResponseStatus {

    SUCCESS(ResponseStatus.SUCCESS_CODE, "注册成功"),
    ILLEGAL_ARGUMENT(-1, "参数不合法"),
    USERNAME_OR_PASSWORD_IS_NULL(-2, "用户名或密码为空"),
    USERNAME_OR_PASSWORD_IS_TOO_LONG(-3, "用户名或密码太长"),
    USERNAME_IS_EXISTED(-4, "用户名已经存在"),
    REGISTER_FAILED(-5, "注册失败"),
    LOGIN_ERROR(-6, "用户名或密码错误"),
    LOGIN_SUCCESS(ResponseStatus.SUCCESS_CODE, "登陆成功");

    private final int code;

    private final String desc;

    UserResponse(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
