package service;

import common.ServerResponse;
import entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

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

}
