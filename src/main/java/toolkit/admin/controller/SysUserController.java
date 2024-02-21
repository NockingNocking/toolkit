package toolkit.admin.controller;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.admin.controller
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toolkit.admin.entries.LoginUserParam;
import toolkit.admin.service.SysUserService;
import toolkit.frame.api.ApiResult;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {
  
  @Autowired
  private SysUserService userService;
  
  
  /**
   * 自定义登录
   * @param param 登录传参
   * @return ApiResult
   */
  @PostMapping("/login")
  public ApiResult login(@RequestBody LoginUserParam param) {
    
    return userService.login(param);
  }
  
  /**
   * 自定义登出
   * @return ApiResult
   */
  @PostMapping("/logOut")
  public ApiResult logOut() {
    
    return userService.logOut();
  }
}


