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
    NOTEBOOK_CREATE_SUCCESS(ResponseStatus.SUCCESS_CODE, "笔记本创建成功"),
    NOTEBOOK_CREATE_FAIL(5, "笔记本创建失败"),
    NOTE_IS_EXISTED(6, "笔记名已存在"),
    NOTE_CREATE_SUCCESS(ResponseStatus.SUCCESS_CODE, "笔记新建成功"),
    NOTE_CREATE_FAIL(8, "笔记新建失败"),
    PARAMETER_NULL(9, "参数为空"),
    RESULT_IS_NULL(10, "没有查询到需要的记录"),
    UPDATE_ID_NULL(11, "更新失败"),
    NOTEBOOK_ID_NULL(12, "笔记本id为空"),
    NOTEBOOK_NAME_NULL(13, "笔记本名字为空"),
    USER_ID_NULL(14, "用户信息不完整");

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
