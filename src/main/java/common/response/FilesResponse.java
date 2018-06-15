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
    RENAME_FILE_SUCCESS(ResponseStatus.SUCCESS_CODE,"文件重命名成功"),
    UPFILE_FAILURE(-1,"文件上传失败"),
    UPFILE_IS_NULL(-2,"未上传文件,或者上传的文件内容是空，请重新上传"),
    FILE_IS_EXIST(-3,"文件名重复"),
    URL_IS_WRONG(-4, "URL地址错误"),
    RENAME_FILE_FAILURE(-5, "文件重命名失败"),
    FILE_DELETE_FAIL(-6, "文件删除失败"),
    FILE_DELETE_SUCCES(-7, "文件删除成功"),
    GET_FILE_LIST_FAILURE(-8,"获取文件列表失败，请重试"),
    GET_FILE_LIST_SUCCESS(ResponseStatus.SUCCESS_CODE,"获取文件列表成功"),
    NEW_FILE_FOLDER_SUCCESS(ResponseStatus.SUCCESS_CODE,"创建文件夹成功"),
    NEW_FILE_FOLDER_EXIST(-9,"创建文件夹失败，名称重复"),
    NEW_FILE_FOLDER_FAILURE(-10,"创建文件夹失败，请重试");


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
