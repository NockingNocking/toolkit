package toolkit.admin.service.imp;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.admin.service.imp
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import toolkit.admin.entries.LoginUserParam;
import toolkit.admin.mapper.AdminSysUserMapper;
import toolkit.admin.service.SysUserService;
import toolkit.frame.api.ApiResult;
import toolkit.frame.utils.RedisUtils;
import toolkit.frame.security.LogUser;
import toolkit.frame.utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SysUserServiceImpl implements SysUserService {
  
  @Autowired
  private RedisUtils redisUtils;
  
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private AdminSysUserMapper adminSysUserMapper;
  
  
  @Override
  public ApiResult login(LoginUserParam param) {

    // 1 获取AuthenticationManager 对象 然后调用 authenticate() 方法
    // UsernamePasswordAuthenticationToken 实现了Authentication 接口
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(param.getUserName(), param.getPassword());
    System.out.println(authenticationToken);
    Authentication authenticate = authenticationManager.authenticate(authenticationToken);
  
    //2 认证没通过 提示认证失败
    if (Objects.isNull(authenticate)) {
      throw new RuntimeException("认证失败用户信息不存在");
    }
    
    
    //认证通过 使用userid 生成jwt token令牌
    LogUser loginUser = (LogUser) authenticate.getPrincipal();
    String userId = loginUser.getUser().getUserId().toString();
    

    Map<String, String> payloadMap = new HashMap<>();
    payloadMap.put("userId", userId);
    payloadMap.put("token", JwtUtils.generateToken(payloadMap));
    
    boolean resultRedis = redisUtils.set("login:" + userId, loginUser);
    
    if(!resultRedis){
      throw new RuntimeException("redis连接不上，登录失败");
    }
    
    
    return ApiResult.success(payloadMap);
  }
  
  @Override
  public ApiResult logOut() {
    // 1 获取 SecurityContextHolder 中的用户id
    UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    LogUser loginUser = (LogUser)authentication.getPrincipal();
    //2 删除redis 中的缓存信
    String key = "login:"+loginUser.getUser().getUserId().toString();
    redisUtils.del(key);
    return ApiResult.success("退出成功!");
    
  }
  
}


