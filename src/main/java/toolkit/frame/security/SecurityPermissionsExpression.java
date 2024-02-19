package toolkit.frame.security;

/**
 * @author: Nocking
 * @date: 2024/2/19
 * @package: toolkit.frame.security
 **/
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import toolkit.admin.enums.SysUserRolesEnum;
import toolkit.frame.security.LogUser;

import java.util.List;

//自定义security权限校验方法
@Component("permissions")
public class SecurityPermissionsExpression {
  public boolean hasAuthority(String authority){
    //获取当前用户的权限
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    LogUser loginUser = (LogUser) authentication.getPrincipal();
    List<String> permissions = loginUser.getPermissions();
    System.out.println(permissions);
    
    //判断用户权限集合中是否存在authority
    return permissions.contains(authority);
  }
}

