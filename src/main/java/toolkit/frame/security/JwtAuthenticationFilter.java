package toolkit.frame.security;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.frame.security
 **/

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import toolkit.frame.exception.BusinessException;
import toolkit.frame.utils.RedisUtils;
import toolkit.frame.utils.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private RedisUtils redisUtils;
  
  //每次请求都会执行这个方法
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws BusinessException, ServletException, IOException {
    
    // 获取Headers上的token，我命名为token
    String token = request.getHeader("token");
    
    System.out.println("doFilterInternal:"+token);
    
    
    if (StringUtils.isEmpty(token)) {
      // token不存在 放行 并且直接return 返回
      filterChain.doFilter(request, response);
      return;
    }
    
    
    // 解析token
    String userId = null;
    
    try {
      DecodedJWT tokenInfo = JwtUtils.verifyToken(token);
      
      //token过期时间
      Date expiresAt = tokenInfo.getExpiresAt();
      SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
      System.out.println("token过期时间："+ymdhms.format(expiresAt));
      
      //其实这里后端可以做token是否快过期的处理，然后返回新的token给前端
      //或者新写一个刷新tokena接口给前端，让前端自己刷新
      
      
      
      userId = tokenInfo.getClaim("userId").asString();
      
    } catch (Exception e) {
      
      if(e instanceof TokenExpiredException){
        throw new RuntimeException("登录已过期！");
      }else {
        throw new RuntimeException("token非法");
      }
      
      
    }
    
    // 获取userid 从redis中获取用户信息
    String redisKey = "login:" + userId;
    LogUser loginUser = (LogUser) redisUtils.get(redisKey);
    if (Objects.isNull(loginUser)) {
      throw new RuntimeException("用户未登录");
    }
    
    //将用户信息存入到SecurityContextHolder
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    // 放行
    filterChain.doFilter(request, response);
  }
}


