package common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by rzh on 2018/06/03
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保证序列化json对象的时候，如果是null的对象，key也会消失
public class ServerResponse<T> implements Serializable {

    private int status;

    private String msg;

    private T data;

    // 推荐直接用这个构造器！
    private ServerResponse(ResponseCode responseCode, T data) {
        this.status = responseCode.getCode();
        this.msg = responseCode.getDesc();
        this.data = data;
    }

    /**
     * 判断是否成功，默认状态码都是 ResponseCode 中的 SUCCESS
     * @return true 成功，false 失败
     */
    @JsonIgnore // 使之不在json序列化当中
    public boolean isSuccess() {

        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> ServerResponse<T> getServerResponse(ResponseCode responseCode) {
        return getServerResponse(responseCode, null);
    }

    public static <T> ServerResponse<T> getServerResponse(ResponseCode responseCode, T data) {
        return new ServerResponse<T>(responseCode, data);
    }
}
