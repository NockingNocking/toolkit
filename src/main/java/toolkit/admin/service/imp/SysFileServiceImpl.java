package toolkit.admin.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import toolkit.admin.service.SysFileService;
import toolkit.frame.api.ApiResult;
import toolkit.frame.utils.MinioUtils;

import java.util.List;
import java.util.Map;

/**
 * @author: Nocking
 * @date: 2024/2/28
 * @package: toolkit.admin.service.imp
 **/
@Service
public class SysFileServiceImpl implements SysFileService {
  
  @Autowired
  private MinioUtils minioUtils;
  

  
  @Override
  public ApiResult upload(MultipartFile[] multipartFile, String bucketName){
    
    List<String> uploadUrls = minioUtils.upload(multipartFile,bucketName);
    
    
    return ApiResult.success(uploadUrls);
  }
}
