package service.impl;

import Utils.FileNiceUtil;
import common.Const;
import common.ServerResponse;
import common.response.FilesResponse;
import common.response.UserResponse;
import dao.FilesDao;
import entity.Files;
import entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.FilesService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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


        int affect = 0;

        if( file == null || file.getSize() == 0){
            return ServerResponse.getServerResponse(FilesResponse.UPFILE_IS_NULL);

        }

        //判断用户是否登录
        if( user == null){
            return ServerResponse.getServerResponse(UserResponse.NEED_LOGIN);
        }

        //得到文件名
        String fileName = file.getOriginalFilename();

        //创建保存的目录，并得到目录名
        String dir = FileNiceUtil.getDirFile(user.getUsername(),path);

        //如果得到为空，则创建失败
        if(dir == null){
            return ServerResponse.getServerResponse(FilesResponse.UPFILE_FAILURE);
        }

        //得到保存文件的uri
        String uri = dir + fileName;

        //如果该文件存在，重命名
        if(FileNiceUtil.fileIsExits(uri)){
            //得到新的uri,避免重复;
            return ServerResponse.getServerResponse(FilesResponse.FILE_IS_EXIST);
//            uri = dir +  fileName.substring(0,fileName.lastIndexOf(".")) +
//                    UUID.randomUUID().toString().substring(0,2) + fileName.substring(fileName.lastIndexOf("."),fileName.length());
        }

        //如果文件上传失败，返回失败结果
        if(!FileNiceUtil.upFileUtils(file, new File(uri))){
            return ServerResponse.getServerResponse(FilesResponse.UPFILE_FAILURE);
        }

        //得到File后面的路径
        uri = uri.substring(uri.indexOf("File"),uri.length());

        //文件上传成功，将信息插入数据库
        Files files = new Files(user.getUserId(), uri , FileNiceUtil.getFileType(fileName),fileName);
        affect = filesDao.saveFile(files);

        //如果返回小于等于0，则插入数据失败
        if(affect <=0){
            return ServerResponse.getServerResponse(FilesResponse.UPFILE_FAILURE);
        }

        return ServerResponse.getServerResponse(FilesResponse.UPFILE_SUCCESS);
    }

    /**
     * 查询这个用户的某个类型的文件
     * @param files
     * @return
     */
    @Override
    public ServerResponse<List<Files>> listFile(Files files) {
        return null;
    }

    /**
     * 删除指定url的文件
     * @param url
     * @return
     */
    @Override
    public ServerResponse deleteFile(String url) {
        return null;
    }

    /**
     * 根据url修改fileName
     * 参数 url file
     * @param files
     * @return
     */
    @Override
    public ServerResponse updateFilename(Files files) {
        return null;
    }

    /**
     * 下载单个文件
     * @return 需要下载的文件
     */
    @Override
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, String url) throws IOException {
        String fileName = url;
        File file = new File("D:/" + fileName);
        HttpHeaders headers = new HttpHeaders();
        String realFileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", realFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }

    /**
     * 下载多个文件
     * @param request
     * @param urls 多个文件的url数组
     * @return 一个zip压缩包，里面包含指定的多个文件
     * @throws IOException
     */
    @Override
    public ResponseEntity<byte[]> downloadMultipleFile(HttpServletRequest request, String[] urls, User user) throws IOException {
        List<String> urlList = Arrays.asList(urls);

        //File路径
        String path = request.getSession().getServletContext().getRealPath(Const.BASE_DIR);

        //下载的压缩包的名字
        String zipName = "JotterFile.zip";

        //用户目录路径
        String userFolder = FileNiceUtil.getDirFile(user.getUsername(), path);

        ZipOutputStream zipOutputStream = new ZipOutputStream(
                new FileOutputStream(FileNiceUtil.getDirFile("Temp", path) + zipName)
        );
        InputStream inputStream = null;

        //压缩包装包
        for (String fileName: urlList) {
            String realFileName = userFolder + fileName;
            System.out.println(realFileName);
            inputStream = new FileInputStream(new File(realFileName));
            zipOutputStream.putNextEntry(new ZipEntry(fileName));
            int index = 0;
            while ((index = inputStream.read()) != -1){
                zipOutputStream.write(index);
            }
            inputStream.close();
        }
        zipOutputStream.close();

        //封装完毕，开始传输
        File file = new File(FileNiceUtil.getDirFile("Temp", path) + zipName);
        HttpHeaders headers = new HttpHeaders();
        String realZipName = new String(zipName.getBytes("utf-8"),"iso-8859-1");
        headers.setContentDispositionFormData("attachment", realZipName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> responseEntity =  new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        file.delete();
        return responseEntity;
    }

}
