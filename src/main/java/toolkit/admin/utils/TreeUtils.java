package toolkit.admin.utils;

import org.springframework.stereotype.Component;
import toolkit.admin.entries.AdminSysMenus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Nocking
 * @date: 2024/2/21
 * @package: toolkit.admin.utils
 **/
@Component
public class TreeUtils {
  /**
   * 获取子节点
   * @param id      父节点id
   * @param menus 所有菜单列表
   * @return 每个根节点下，所有子菜单列表
   */
  public List<AdminSysMenus> getMenuChildren(String id,List<AdminSysMenus> menus) {
    
    List<AdminSysMenus> childList = new ArrayList<>();
    
    for (AdminSysMenus entity : menus) {
      // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
      //相等说明：为该根节点的子节点。
      if (entity.getParentId().toString().equals(id)) {
        childList.add(entity);
      }
    }
    
    for (AdminSysMenus entity : childList) {
      entity.setChildren(getMenuChildren(entity.getId().toString(), menus));
    }
    
    if (childList.size() == 0) {
      return new ArrayList<>();
    }
    return childList;
  }
  
}
