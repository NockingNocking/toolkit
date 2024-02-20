package toolkit.frame.minio;

/**
 * description: minio配置类
 * @author: Nocking
 * @date: 2024/2/20
 * @package: toolkit.frame.minio
 **/
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioPropertiesConfig {
  
  /**
   * 端点
   */
  private String endpoint;
  /**
   * 用户名
   */
  private String accessKey;
  /**
   * 密码
   */
  private String secretKey;
  
  /**
   * 桶名称
   */
  private String bucketName;
}