package common;

/**
 * Created by rzh on 2018/06/03
 */
public enum ResponseCode {

    SUCCESS(0, "执行成功"),
    ERROR(1, "执行失败"),
    NEED_LOGIN(2, "需要登陆"),
    ILLEGAL_ARGUMENT(3, "参数不合法"),
    NOTEBOOK_IS_EXISTED(4,"笔记本名字已存在"),
    NOTEBOOK_CREATE_SUCCESS(5,"笔记本创建成功"),
    NOTEBOOK_CREATE_FAIL(6,"笔记本创建失败"),
    NOTE_IS_EXISTED(7,"笔记名已存在"),
    NOTE_CREATE_SUCCESS(8,"笔记新建成功"),
    NOTE_CREATE_FAIL(9,"笔记新建失败");

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
