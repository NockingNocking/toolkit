package toolkit.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import toolkit.admin.service.SysFileService;
import toolkit.frame.api.ApiResult;

/**
 * @author: Nocking
 * @date: 2024/2/20
 * @package: toolkit.admin.controller
 **/
@RestController
@RequestMapping("/system/admin")
public class SysFileController {
  
  @Autowired
  private SysFileService sysFileService;
  
  @PostMapping("/upload")
  public ApiResult upload(@RequestParam MultipartFile[] multipartFile, String bucketName) {
    return sysFileService.upload(multipartFile,bucketName);
  }
  
}
