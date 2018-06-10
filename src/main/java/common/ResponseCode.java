package common;

/**
 * Created by rzh on 2018/06/03
 */
public enum ResponseCode {

    SUCCESS(0, "执行成功"),
    ERROR(1, "执行失败"),
    NEED_LOGIN(2, "需要登陆"),
    ILLEGAL_ARGUMENT(3, "参数不合法");

    private final int code;

    private final String desc;

    ResponseCode(int code, String desc) {
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
