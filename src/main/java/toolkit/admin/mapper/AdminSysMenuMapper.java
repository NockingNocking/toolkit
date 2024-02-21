package toolkit.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import toolkit.admin.entries.AdminSysMenus;

import java.util.List;


/**
 * @author: Nocking
 * @date: 2024/2/21
 * @package: toolkit.admin.mapper
 **/
public interface AdminSysMenuMapper extends BaseMapper<AdminSysMenus> {
  
  /**
   * 查询菜单
   * @return
   */
  @Select({
      "<script>"+
      " SELECT * FROM"+
      " sys_menus"+
      "</script>"
  })
  List<AdminSysMenus> getAdminSysMenus();
}
