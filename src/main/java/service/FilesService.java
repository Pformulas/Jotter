package service;

import common.ServerResponse;
import entity.Files;
import entity.NoteBook;
import entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
    public ServerResponse<String> saveFile(CommonsMultipartFile file, String path, User user) throws IOException;


    /**
     * 查询这个用户的某个类型的文件
     * @param files
     * @return
     */
    public ServerResponse<List<Files>> listFile(Files files);

    /**
     * 删除指定url的文件
     * @param url
     * @return
     */
    public ServerResponse deleteFile(String url);

    /**
     * 根据url修改fileName
     * 参数 url file
     * @param files
     * @return
     */
    public ServerResponse updateFilename(Files files);
}
