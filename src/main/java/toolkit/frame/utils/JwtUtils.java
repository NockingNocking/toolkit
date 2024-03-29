package toolkit.frame.utils;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.frame.utils
 **/


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtils {
  private static String secretKey;
  
  private static Integer timeoutSeconds;//jwt的过期周期/秒 默认30分钟
  
  @Value("${Jwt.secretKey}")
  public void secretKey(String secretKey) {
    JwtUtils.secretKey =  secretKey;
  }
  
  @Value("${Jwt.timeoutSeconds}")
  public void timeoutSeconds(Integer timeoutSeconds) {
    JwtUtils.timeoutSeconds =  timeoutSeconds;
  }
  
  
  /**
   * 创建token
   * @param payloadMap 存储的内容，自定义，一般是用户id
   * @return
   */
  public static String generateToken(Map<String, String> payloadMap) {
    
    HashMap headers = new HashMap();
    
    JWTCreator.Builder builder = JWT.create();
    
    //定义jwt过期时间
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.SECOND, timeoutSeconds);
    
    
    //payload
    payloadMap.forEach((k, v) ->{
      builder.withClaim(k, v);
    });
    
    
    // 生成token
    String token = builder.withHeader(headers)//header
        //.withClaim("second",timeoutSeconds)//jwt的过期周期/秒，可以用于jwt快过期的时候自动刷新
        .withExpiresAt(instance.getTime())//指定令牌的过期时间
        .sign(Algorithm.HMAC256(secretKey));//签名
    
    
    return token;
  }
  
  
  /**
   * 校验token是否合法
   * @param token
   * @return
   */
  public static DecodedJWT verifyToken(String token) {

        /*
        如果有任何验证异常，此处都会抛出异常
        SignatureVerificationException 签名不一致异常
        TokenExpiredException 令牌过期异常
        AlgorithmMismatchException 算法不匹配异常
        InvalidClaimException 失效的payload异常
        */
    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
    
    
    
    return decodedJWT;
  }
  
  /**
   * 获取token信息
   * @param token
   * @return
   */
  public static DecodedJWT getTokenInfo(String token) {
    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
    return decodedJWT;
  }
  
  /**
   * 获取token信息方法
   */
    /*public static Map<String, Claim> getTokenInfo(String token) {

        return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token).getClaims();
    }*/
}



