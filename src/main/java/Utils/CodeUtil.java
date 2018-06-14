package Utils;

import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // 邮箱正则表达式
    private static final String EMAIL_REGEX = "^\\w+@\\w+\\.\\w{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * 得到 str 字符串的 MD5 编码
     *
     * @param str 原始字符串
     * @return 返回 MD5 编码
     */
    public static String getMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    /**
     * 判断是否是邮箱格式的字符串
     *
     * @param str 要被判断的字符串
     * @return true 是邮箱格式，false 不是邮箱格式
     */
    public static boolean isEmail(String str) {
        // 空字符串不算邮箱
        if (str == null || "".equals(str.trim())) {
            return false;
        }

        return EMAIL_PATTERN.matcher(str.trim()).matches();
    }
}
