package common.response;

import common.ResponseStatus;

/**
 * @author Fish
 * created by 2018-06-10 16:59
 */
public enum NoteBookResponse implements ResponseStatus {

    SUCCESS(ResponseStatus.SUCCESS_CODE, "执行成功"),
    ERROR(1, "执行失败"),
    ILLEGAL_ARGUMENT(2, "参数不合法"),
    NOTEBOOK_IS_EXISTED(3, "笔记本名字已存在"),
    NOTEBOOK_CREATE_SUCCESS(4, "笔记本创建成功"),
    NOTEBOOK_CREATE_FAIL(5, "笔记本创建失败"),
    NOTE_IS_EXISTED(6, "笔记名已存在"),
    NOTE_CREATE_SUCCESS(7, "笔记新建成功"),
    NOTE_CREATE_FAIL(8, "笔记新建失败");

    private final int code;

    private final String desc;

    NoteBookResponse(int code, String desc) {
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
