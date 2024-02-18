package toolkit.frame.security;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.frame.security
 **/

import com.alibaba.fastjson.JSON;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import toolkit.frame.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//自定义授权失败异常处理类
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest httpServletRequest,
                     HttpServletResponse httpServletResponse,
                     AccessDeniedException accessDeniedException) throws IOException, ServletException {
    
    System.out.println("AccessDeniedHandler:暂无权限");
    WebUtils.rednerString(httpServletResponse, JSON.toJSONString(AuthExceptionUtil.getErrMsgByExceptionType(accessDeniedException)));
    
  }
}


