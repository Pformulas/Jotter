package common;

import java.io.File;

/**
 * 常用常量类
 *
 * @author Fish
 * created by 2018-06-10 16:25
 */
public final class Const {

    // 用户名最大长度
    public static final int MAX_LENGTH_OF_USERNAME = 64;

    // 密码最大长度
    public static final int MAX_LENGTH_OF_PASSWORD = 64;

    // 当用户对象作为 key 值时使用
    public static final String USER_KEY = "userKey";

    //当前访问到的路径
    public static final String CURRENT_PATH = "currentPath";

    // 验证用户名是否存在时用的假密码，或者说临时密码
    public static final String TEMP_PASSWORD = "tempPassword";

    //视频类型码
    public static final String VIDEO_TYPE = "vidio";

    //音乐类型码
    public static final String MUSIC_TYPE = "music";

    //图片类型码
    public static final String PICTURE_TYPE = "img";

    //文本类型码
    public static final String TEXT_TYPE = "file";

    public static final String Folder_Type = "folder";

    //其他类型码
    public static final String OTHER_TYPE = "questions";

   // 文件保存路径
    public static final String FILE_PATH = "File";

    //文件保存目录
    public static final String BASE_DIR = File.separator + FILE_PATH;

    public static final String FILE = "File";

    public static final int BACK = 1;

    //图片类型后缀
    public static final String IMG[] = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
            "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};

    //文档类型后缀
    public static final String DOCUMENT[] =
            {"txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};

    //视频类型后缀
    public static final String VIDEO[] =
            {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};

    //音乐类型后缀
    public static final String MUSIC[] =
            {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
                    "m4a", "vqf"};


}
