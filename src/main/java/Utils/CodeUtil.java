package Utils;

import org.springframework.util.DigestUtils;

/**
 * 编码相关工具类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/06/12 00:16:37
 */
public final class CodeUtil {

    // 默认编码 ===> UTF8
    private static final String DEFAULT_CHARSET = "UTF8";

    /**
     * 得到 str 字符串的 MD5 编码
     *
     * @param str 原始字符串
     * @return 返回 MD5 编码
     */
    public static String getMD5(String str) {
        return new String(DigestUtils.md5Digest(str.getBytes()));
    }
}
