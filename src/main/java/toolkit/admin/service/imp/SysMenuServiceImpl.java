package toolkit.admin.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import toolkit.admin.entries.AdminSysMenus;
import toolkit.admin.mapper.AdminSysMenuMapper;

import toolkit.admin.service.SysMenuService;
import toolkit.admin.utils.TreeUtils;
import toolkit.frame.api.ApiResult;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: Nocking
 * @date: 2024/2/19
 * @package: toolkit.admin.service.imp
 **/
@Service
public class SysMenuServiceImpl implements SysMenuService {
  
  @Autowired
  private AdminSysMenuMapper menuMapper;
  
  @Autowired
  private TreeUtils treeUtils;
  
  @Override
  public ApiResult getAdminSysMenus() {
    try {
      List<AdminSysMenus> allAdminSysMenus  = menuMapper.getAdminSysMenus();
      List<AdminSysMenus> rootMenu = new ArrayList<>();
      
      
      //添加根节点
      for (AdminSysMenus entity : allAdminSysMenus) {
        if (entity.getParentId() == 0) {
          rootMenu.add(entity);
        }
      }
      //为根菜单设置子菜单，getMenuChildren是递归调用的
      for (AdminSysMenus entity : rootMenu) {
        List<AdminSysMenus> childList = treeUtils.getMenuChildren(entity.getId().toString(), allAdminSysMenus);
        entity.setChildren(childList);
      }
      
      
      return ApiResult.success(rootMenu);
    }catch (Exception e) {
    
      return ApiResult.error(201, e.getMessage());
    }
  }
}
