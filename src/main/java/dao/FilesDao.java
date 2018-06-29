package dao;

import entity.Files;
import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件操作相关方法
 *
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/6/11 14:13
 */
public interface FilesDao {

    /**
     * 保存文件
     * @param files 文件类
     * @return 保存结果
     */
    public Integer saveFile(Files files);

    /**
     * 获取这个userId和type的文件列表
     * 参数： userId  type
     * @param userId type
     * @return 文件列表
     */
    public List<Files> listFile(@Param(("userId"))String userId, @Param(("type"))String type);

    /**
     * 根据url删除指定的文件
     * @param url
     * @return
     */
    public Integer deleteFile(String url);

    /**
     * 根据url修改fileName
     * @param files 文件对象
     * @param uri 旧的uri 用来找到该更新哪条记录
     * @return 影响的行数
     */
    public Integer updateFilename(@Param(("files")) Files files, @Param("uri") String uri);



}
