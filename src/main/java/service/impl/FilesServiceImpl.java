package service.impl;

import Utils.FileNiceUtil;
import common.ServerResponse;
import common.response.FilesResponse;
import common.response.UserResponse;
import dao.FilesDao;
import entity.Files;
import entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.FilesService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件操作业务实现
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 14:33
 */

@Service
public class FilesServiceImpl implements FilesService {

    private final FilesDao filesDao;

    @Autowired
    public FilesServiceImpl(FilesDao filesDao) {
        this.filesDao = filesDao;
    }

    /**
     * 文件上传
     * @param file 文件
     * @return 上传结果
     */
    @Override
    public ServerResponse<String> saveFile(CommonsMultipartFile file, String path, User user) {

        if(file.getSize() == 0){
            return ServerResponse.getServerResponse(FilesResponse.UP_ISNULL);
        }

        //判断用户是否登录
        if( user == null){
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }

        //得到文件名
        String fileName = file.getOriginalFilename();

        //创建保存的目录，并得到目录名
        String dir = FileNiceUtil.getDirFile("张三",path);

        //如果得到为空，则创建失败
        if(dir == null){
            ServerResponse.getServerResponse(FilesResponse.UPFILE_FAILURE);
        }

        //得到保存文件的uri
        String uri = dir + fileName;

        //如果该文件存在，重命名
        if(FileNiceUtil.fileIsExits(uri)){
            //得到新的uri,避免重复;
            uri = dir +  fileName.substring(0,fileName.lastIndexOf(".")) +
                    UUID.randomUUID().toString().substring(0,2) + fileName.substring(fileName.lastIndexOf("."),fileName.length());
        }

        //如果文件上传失败，返回失败结果
        if(!FileNiceUtil.upFileUtils(file, new File(uri))){
            ServerResponse.getServerResponse(FilesResponse.UPFILE_FAILURE);
        }

        uri = uri.substring(uri.indexOf("File"),uri.length());

        return ServerResponse.getServerResponse(FilesResponse.UPFILE_SUCCESS);
    }
}
