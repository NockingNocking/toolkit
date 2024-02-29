package toolkit.admin.controller;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.admin.controller
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
  
  /**
   * 获取用户信息
   * @return ApiResult
   */
  @GetMapping("/userInfos")
  public ApiResult userInfos(@RequestParam Integer userid) {
    return userService.userInfos(userid);
  }
  
  /**
   * 上传用户头像
   * @return ApiResult
   */
  @PostMapping("/uploadAvatar")
  public ApiResult uploadAvatar(@RequestParam(name = "avatar") MultipartFile[] multipartFile) {
    return userService.uploadAvatar(multipartFile);
  }
}


