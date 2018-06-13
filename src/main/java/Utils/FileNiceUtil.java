package Utils;


import common.Const;
import common.response.FilesResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        String dir =  path + File.separator + userName + File.separator;

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

    /**
     * 得到uri中 File 后面的uri
     * @param uri
     * @return File 之后的uri
     */
    public static String getAfterFileUri(String uri){
        return uri.substring(uri.indexOf(Const.FILE) + Const.FILE.length(), uri.length());
    }

    /**
     * 重命名方法
     * @param oldFile 旧文件
     * @param newFile 新文件
     * @return true：重命名成功 false:重命名失败
     */
    public static boolean fileRename(File oldFile, File newFile){
        try{
            //重命名结果
            return oldFile.renameTo(newFile);
        }catch (SecurityException e){
            log.error("用户权限不够，无法重命名文件",e);
        }catch (Exception ex){
            log.error("未知错误",ex);
        }
        return false;
    }
    /**
     * 判断文件类型,默认返回其他类型
     * @param fileName 文件名
     * @return 返回类型名
     */
    public static String getFileType(String fileName) {

        //获得扩展名
        String extension = StringUtils.getFilenameExtension(fileName);

        //如果是空，表示没有扩展名，则归为其他类型
        if( extension == null ){
            return Const.OTHER_TYPE;
        }

        //如果后缀名在图片类型中，则归类为图片
        for (int i = 0; i < Const.IMG.length; i++) {
            if (Const.IMG[i].equals(extension)) {
                return Const.PICTURE_TYPE;
            }
        }

        //如果后缀名在音乐类型中，则归类为音乐
        for (int i = 0; i < Const.MUSIC.length; i++) {
            if (Const.MUSIC[i].equals(extension)) {
                return Const.MUSIC_TYPE;
            }
        }

        //如果后缀名在视频类型中，则归类为视频
        for (int i = 0; i < Const.VIDEO.length; i++) {
            if (Const.VIDEO[i].equals(extension)) {
                return Const.VIDEO_TYPE;
            }
        }

        //如果后缀名在图片类型中，则归类为图片
        for (int i = 0; i < Const.DOCUMENT.length; i++) {
            if (Const.DOCUMENT[i].equals(extension)) {
                return Const.TEXT_TYPE;
            }
        }

        //默认返回其他类型
        return Const.OTHER_TYPE;
    }

    public static void zipFile(List<String> urlList, String userFolder, ZipOutputStream zipOutputStream, InputStream inputStream){
        //压缩包装包
        try {
            for (String fileName: urlList) {
                String realFileName = userFolder + fileName;

                inputStream = new FileInputStream(new File(realFileName));
                zipOutputStream.putNextEntry(new ZipEntry(fileName));
                int index = 0;
                while ((index = inputStream.read()) != -1){
                    zipOutputStream.write(index);
                }
                inputStream.close();
            }
            zipOutputStream.close();
        } catch (FileNotFoundException e) {
            log.error(e.getCause());
        } catch (IOException e) {
            log.error(e.getCause());
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("输入流关闭失败",e);
                }
            }
            if(zipOutputStream != null){
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    log.error("压缩流关闭失败",e);
                }
            }
        }
    }
}