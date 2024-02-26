package toolkit.frame.security;

/**
 * @author: Nocking
 * @date: 2024/2/19
 * @package: toolkit.frame.security
 **/
import com.sun.xml.internal.bind.v2.TODO;
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
    
//    TODO:根据角色获取权限
    List<String> roles = loginUser.getRolesLists();
    System.out.println(roles);
    
    //判断用户权限集合中是否存在authority
    return roles.contains(authority);
  }
}

