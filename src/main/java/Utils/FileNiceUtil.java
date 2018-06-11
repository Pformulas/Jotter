package Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件模块工具类
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 19:55
 */
public class FileNiceUtil {

    private static final Log log = LogFactory.getLog(FileNiceUtil.class);

    /**
     * 初始化文件目录并返回目录名
     * @param userName 用户名
     * @param path 文件根目录
     * @return 返回目录名，如果是null，则目录创建失败
     */
    public static String getDirFile(String userName, String path){
        //拼接好的目录
        String dir =  path + "/" + userName + "/";

        File dirFile = new File(dir);
        //如果File文件夹不存在，创建文件夹

        if(!(dirFile.exists())){

            //创建文件夹失败，上传文件失败
            if (!dirFile.mkdirs()) {
                return null;
            }
        }
        return dir;
    }

    /**
     *
     * @param file 上传的文件
     * @param newFile  指向uri的文件   例如（File/"张三”/xx文件）
     * @return true:上传文件成功  false:上传文件失败
     */
    public static boolean upFileUtils(CommonsMultipartFile file, File newFile){

        InputStream inputStream = null;

        try {

            //获得文件流
            inputStream = file.getInputStream();

            //把上传的文件内容复制到创建的文件中
            FileUtils.copyInputStreamToFile(inputStream, newFile);

        } catch (IOException e){
            log.error("发生异常，上传失败！", e);

            //发生异常，上传失败！
            return false;
        } finally {
            //如果流不空，关闭它
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("流关闭失败",e);
                }
            }
        }
        return true;
    }

    /**
     * 判断文件是否存在
     * @param fileName 文件名
     * @return true:存在  false:不存在
     */
    public static boolean fileIsExits(String fileName){
        File file = new File(fileName);
        return file.exists();
    }

}