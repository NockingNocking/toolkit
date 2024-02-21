package toolkit.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import toolkit.admin.service.SysMenuService;
import toolkit.frame.api.ApiResult;

/**
 * @author: Nocking
 * @date: 2024/2/19
 * @package: toolkit.admin.controller
 **/
@RestController
@RequestMapping("/system/admin")
public class SysMenuController {
  
  @Autowired
  private SysMenuService systemService;
  
  /**
   * 获取菜单列表
   * @return ApiResult
   */
  @GetMapping("/menus")
  public ApiResult menus() {
    
    return systemService.getAdminSysMenus();
  }
}
