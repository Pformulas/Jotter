package dao;

import entity.Files;

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

}
