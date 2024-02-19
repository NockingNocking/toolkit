package toolkit.admin.service.imp;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import toolkit.admin.service.LogService;
import toolkit.admin.service.SystemService;
import toolkit.frame.api.ApiResult;
import toolkit.frame.security.LogUser;

/**
 * @author: Nocking
 * @date: 2024/2/19
 * @package: toolkit.admin.service.imp
 **/
public class SystemServiceImpl implements SystemService {
  @Override
  public ApiResult getMenus() {

    return ApiResult.success("退出成功!");
    
  }
}
