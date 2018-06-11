package common.response;

import common.ResponseStatus;

/**
 * 文件操作返回结果
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 14:40
 */
public enum FilesResponse implements ResponseStatus {

    SUCCESS(ResponseStatus.SUCCESS_CODE, "执行成功"),
    UPFILE_SUCCESS(ResponseStatus.SUCCESS_CODE,"文件上传成功"),
    UPFILE_FAILURE(-1,"文件上传失败"),
    UP_ISNULL(-2,"未上传文件");

    private final int code;

    private final String desc;

    FilesResponse(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}