package toolkit.configs;

/**
 * description: 获取配置文件信息
 * @author: Nocking
 * @date: 2024/2/19
 * @package: toolkit.configs
 **/


import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toolkit.frame.minio.MinioPropertiesConfig;

import javax.annotation.Resource;



@Configuration
@EnableConfigurationProperties(MinioPropertiesConfig.class)
public class MinioConfig {
  
  @Resource
  private MinioPropertiesConfig minioPropertiesConfig;
  
  
  /**
   * 初始化 MinIO 客户端
   */
  @Bean
  public MinioClient minioClient() {
    
    MinioClient minioClient = MinioClient.builder()
        .endpoint(minioPropertiesConfig.getEndpoint())
        .credentials(minioPropertiesConfig.getAccessKey(), minioPropertiesConfig.getSecretKey())
        .build();
    return minioClient;
  }
}

