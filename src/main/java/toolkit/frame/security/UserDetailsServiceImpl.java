package toolkit.frame.security;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.frame.security
 **/




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import toolkit.admin.entries.AdminSysPermission;
import toolkit.admin.entries.AdminSysRoles;
import toolkit.admin.mapper.AdminSysPermissionMapper;
import toolkit.admin.entries.AdminSysUser;
import toolkit.admin.mapper.AdminSysRolesMapper;
import toolkit.admin.mapper.AdminSysUserMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
  
  @Autowired
  private AdminSysRolesMapper adminSysRolesMapper;
  
  @Autowired
  private AdminSysUserMapper userMapper;
  
  
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    //需要构造出 org.springframework.security.core.userdetails.User 对象并返回
    
    if (username == null || "".equals(username)) {
      throw new RuntimeException("用户不能为空");
    }
    
    //根据用户名查询用户
    AdminSysUser user = userMapper.selectOne(new QueryWrapper<AdminSysUser>().eq("account", username));
    if (user == null) {
      throw new RuntimeException("用户不存在");
    }
    
    List<String> rolesList = new ArrayList<>();
    
    if (user != null) {
      //获取该用户的角色
      List<AdminSysRoles> AdminSysRoles = adminSysRolesMapper.selectRoleLists(user.getUserId());
      
      // 声明用户授权
      AdminSysRoles.forEach(sysPermission -> {
        rolesList.add(sysPermission.getRoleCode());
      });
    }
    
    
  
    //返回用户信息
    return new LogUser(user,rolesList);
    
  }
  
  
  //这是加密的算法，把加密后的密码update你用户表的数据库用户的密码上
  public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String encode = bCryptPasswordEncoder.encode("123456");
    System.out.println(encode);
  }
  
  
}


