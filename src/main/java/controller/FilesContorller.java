package controller;

import common.Const;
import common.ServerResponse;
import common.response.FilesResponse;
import entity.User;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.FilesService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
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


    @RequestMapping(value = "upFile.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> upFile(@RequestParam("file")CommonsMultipartFile file,
            HttpServletRequest request, HttpSession session) throws IOException {

        String path = null;
        User user = new User();

        //得到文件保存路径
        path = request.getSession().getServletContext().getRealPath(Const.baseDir);
        ServerResponse<String> serverResponse = filesService.saveFile(file, path, user);

        return  serverResponse;
    }

}
