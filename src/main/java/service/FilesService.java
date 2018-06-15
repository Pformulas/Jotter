package service;

import common.ServerResponse;
import entity.Files;
import entity.NoteBook;
import entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 文件操作业务层
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 14:30
 */

@Service
public interface FilesService {

    /**
     * 上传文件
     * @param file 文件
     * @return 上传结果
     */
    public ServerResponse<String> saveFile(CommonsMultipartFile file, String path, User user);


    /**
     * 查询这个用户的某个类型的文件
     * @param files
     * @return
     */
    public ServerResponse<List<Files>> listFile(User user, String type);

    /**
     * 删除指定url的文件
     * @param url
     * @return
     */
    public ServerResponse deleteFile(HttpServletRequest request, String[] urls, User user);


    /**
     * 根据uri修改文件名
     * @param uri 存储文件跟路径
     * @param fileName 新的文件名
     * @return 修改结果
     */
    public ServerResponse updateFilename(String uri, String newUri, String fileName);

    /**
     * 下载单个文件的方法
     * @return
     */
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, String url) throws IOException;

    /**
     * 下载多个文件
     * @param request
     * @param urls 多个文件的url数组
     * @return 一个zip压缩包
     * @throws IOException
     */
    public ResponseEntity<byte[]> downloadMultipleFile(HttpServletRequest request, String[] urls, User user) throws IOException;

    /**
     * 展示文件列表，这里不是分类的
     * @param currentPath 当前访问到的路径
     * @return 文件列表和获取结果
     */
    public ServerResponse getFileList(String currentPath);

    /**
     * 新建文件夹
     * @param currentPath 当前访问路径
     * @return 创建结果
     */
    public ServerResponse newFolder(String currentPath);
}
