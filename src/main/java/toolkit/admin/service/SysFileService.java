package toolkit.admin.service;

import org.springframework.web.multipart.MultipartFile;
import toolkit.frame.api.ApiResult;

/**
 * @author: Nocking
 * @date: 2024/2/28
 * @package: toolkit.admin.service
 **/
public interface SysFileService {
  ApiResult upload(MultipartFile[] multipartFile, String bucketName);
}
