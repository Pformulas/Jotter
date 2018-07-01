package controller;

import Utils.FileNiceUtil;
import common.Const;
import common.ServerResponse;
import common.response.FilesResponse;
import common.response.NoteBookResponse;
import entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.FilesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 文件上传web层
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 14:57
 */

@Controller
public class FilesContorller {

    private static final Log log = LogFactory.getLog(FilesContorller.class);

    private final FilesService filesService;
    @Autowired
    public FilesContorller(FilesService filesService) {
        this.filesService = filesService;
    }

    /**
     *
     * @param file 上传的文件
     * @param session 会话
     * @return 跳转到云盘界面
     */
    @RequestMapping(value = "/upFile.do")
    //@ResponseBody
    public String upFile(@RequestParam("file")CommonsMultipartFile file, HttpSession session)  {

        String currentPath = null;

        //从session从取出用户信息
        User user = (User) session.getAttribute(Const.USER_KEY);

        //得到当前访问的路径
         currentPath = (String)session.getAttribute(Const.CURRENT_PATH);

        ServerResponse serverResponse = filesService.saveFile(file, currentPath, user);
        //将文件上传状态放入session中
        session.setAttribute(Const.UP_FILE_STATUS,serverResponse);
        return  "redirect:yun.html";
    }

    /**
     *  获得文件上传信息
     * @param session 会话
     * @return 文件上传结果
     */
    @ResponseBody
    @RequestMapping(value =  "/getUpStatus.do")
    public ServerResponse getUpStatus(HttpSession session){
        return (ServerResponse) session.getAttribute(Const.UP_FILE_STATUS);
    }

    /**
     * 重命名文件
     * @param partUri 保存在数据库中的路径
     * @param fileName 文件名
     * @param session 会话
     * @return 重命名结果
     */
    @ResponseBody
    @RequestMapping(value =  "/renameFile.do")
    public ServerResponse reNameFile(String partUri, String fileName, HttpSession session){

        //未收到前台传过来的部分uri
        if( partUri == null){
            return ServerResponse.getServerResponse(FilesResponse.RENAME_FILE_FAILURE);
        }

        //从session从得到保存文件根路径
        String dirPath = (String)session.getAttribute(Const.FILE_PATH);

        //根路径+数据库存储的部分uri = 被修改文件完整的uri
        String uri = dirPath + partUri;

        //重命名后的uri
        String newUri = dirPath + partUri.substring(0,partUri.lastIndexOf(File.separator)+1) + fileName;

        return filesService.updateFilename(uri, newUri,fileName);
    }

    /**
     * 得到全部文件，包括文件夹
     * @param session 会话
     * @param fileName 文件名
     * @param back 点击回退
     * @return 文件列表
     */
    @ResponseBody
    @RequestMapping(value = "/getFileList.do")
    public ServerResponse getFileList(HttpSession session, String fileName, Integer back){


        //得到当前访问到的路径
        String currentPath = (String) session.getAttribute(Const.CURRENT_PATH);

        System.out.println(currentPath);
        System.out.println(fileName+"++");

        User user = (User)session.getAttribute(Const.USER_KEY);

        //如果点击了回退按钮,退回到主目录就不可回退
        if(back != null && back == Const.BACK
                && FileNiceUtil.getAfterFileUri(currentPath).length() > (File.separator + user.getUsername()) .length()){
            currentPath = currentPath.substring(0,currentPath.lastIndexOf(File.separator));
        }

        if(!StringUtils.isEmpty(fileName)){
            currentPath = currentPath + File.separator + fileName;
        }


        //保存当前访问到的路径
        session.setAttribute(Const.CURRENT_PATH,currentPath);
        return filesService.getFileList(currentPath);
    }

    @ResponseBody
    @RequestMapping(value = "/newFolder.do")
    public ServerResponse newFolder(String folderName, HttpSession session){

        //得到当前访问路径
        String currentPath = (String)session.getAttribute(Const.CURRENT_PATH);

        if(folderName != null){
            currentPath = currentPath + File.separator + folderName;
        }

        return filesService.newFolder(currentPath);
    }


    @ResponseBody
    @RequestMapping(path = "/singleDownload.do", produces = {"application/json;charset=UTF8"})
    public Object singleDownload(HttpServletRequest request, String url) throws IOException
    {
        if(url == null){
            return ServerResponse.getServerResponse(FilesResponse.URL_IS_WRONG);
        }
        return filesService.downloadFile(request, url);
    }

    /**
     * 多个文件批量下载
     * @param request
     * @param fileNames  其实是file
     * @param user   需要userId 和 username
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(path = "/multipleDownload.do", produces = {"application/json;charset=UTF8"})
    public Object multipleDownload(HttpServletRequest request, String[] fileNames, HttpSession session)
    {
        User user = (User) session.getAttribute(Const.USER_KEY);
        if(fileNames == null){
            return ServerResponse.getServerResponse(FilesResponse.URL_IS_WRONG);
        }
        if(user == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        if(user.getUserId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.USER_ID_NULL);
        }
        if(user.getUsername() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.USER_NAME_NULL);
        }
        ResponseEntity responseEntity = null;
        try {
            responseEntity = filesService.downloadMultipleFile(request, fileNames, user);
        } catch (IOException e) {
            log.error("文件下载失败");
        }
        return responseEntity;
    }

    /**
     * 删除文件
     * 参数：一个url数组， user需要username, userId
     * @param request
     * @param urls
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/deleteFile.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse deleteFile(HttpServletRequest request, String[] urls, HttpSession session){
        User user = (User) session.getAttribute(Const.USER_KEY);
        return filesService.deleteFile(request, urls, user);
    }

    /**
     * 根据文件的类型来列表
     * 参数 user的userId   type是字符串，表示类别
     * @param session
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/listFilesByType.do", produces = {"application/json;charset=UTF8"})
    public ServerResponse listFilesByType(HttpSession session, String type){
        User user = (User) session.getAttribute(Const.USER_KEY);
        return filesService.listFile(user, type);
    }
}
