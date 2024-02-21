package toolkit.admin.service;

import toolkit.admin.entries.LoginUserParam;
import toolkit.frame.api.ApiResult;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.admin.service
 **/
public interface SysUserService {
  
  ApiResult login(LoginUserParam param);
  
  ApiResult logOut();
}
