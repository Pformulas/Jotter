package controller;

import common.Const;
import common.ServerResponse;
import common.response.FilesResponse;
import common.response.NoteBookResponse;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.FilesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 文件上传web层
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 14:57
 */

@Controller
public class FilesContorller {

    private final FilesService filesService;
    @Autowired
    public FilesContorller(FilesService filesService) {
        this.filesService = filesService;
    }

    @RequestMapping(value = "/upFile.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> upFile(@RequestParam("file")CommonsMultipartFile file,
            HttpServletRequest request, HttpSession session)  {

        String path = null;

        //从session从取出用户信息
        User user = (User) session.getAttribute(Const.USER_KEY);

        //得到文件保存路径
        path = request.getSession().getServletContext().getRealPath(Const.BASE_DIR);

        ServerResponse<String> serverResponse = filesService.saveFile(file, path, user);

        return  serverResponse;
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
    public Object multipleDownload(HttpServletRequest request, String[] fileNames, User user) throws IOException
    {
        if(fileNames == null){
            return ServerResponse.getServerResponse(FilesResponse.URL_IS_WRONG);
        }
        if(user == null){
            return ServerResponse.getServerResponse(NoteBookResponse.PARAMETER_NULL);
        }
        if(user.getUsername() == null || user.getUserId() == null){
            return ServerResponse.getServerResponse(NoteBookResponse.USER_ID_NULL);
        }
        return filesService.downloadMultipleFile(request, fileNames, user);
    }
}
