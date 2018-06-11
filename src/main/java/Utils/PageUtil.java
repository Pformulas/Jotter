package Utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/6/11 23:14
 */
public final class PageUtil {

    //一页显示几个
    public static final Integer PAGE_SIZE = 8;

    //分页条显示几个
    public static final Integer SHOW_PAGE = 5;

    public static void toPage(Integer witchPage){
        PageHelper.startPage(witchPage, PageUtil.PAGE_SIZE);
    }

    public static <T> PageInfo<T> pageInfo(List<T> objs){
        return new PageInfo<T>(objs, PageUtil.SHOW_PAGE);
    }
}
