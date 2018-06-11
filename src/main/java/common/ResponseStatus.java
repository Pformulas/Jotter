package common;

/**
 * 响应情况枚举接口
 *
 * @author Fish
 * created by 2018-06-10 16:56
 */
public interface ResponseStatus {

    int SUCCESS_CODE = 0;

    int getCode(); // 得到响应码

    String getDesc(); // 得到响应信息
}
