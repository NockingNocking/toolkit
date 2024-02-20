package toolkit.admin.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import toolkit.frame.utils.MinioUtil;


import java.util.List;

/**
 * @author: Nocking
 * @date: 2024/2/20
 * @package: toolkit.admin.controller
 **/
@RestController
@RequestMapping("/system/admin")
public class FileUploadOrDownloadController {
  private MinioUtil minioUtil;
  
  public FileUploadOrDownloadController(MinioUtil minioUtil) {
    this.minioUtil = minioUtil;
  }
  
  @PostMapping("/upload")
  public List<String> upload(@RequestParam(name = "multipartFile") MultipartFile[] multipartFile) {
    return minioUtil.upload(multipartFile);
  }
  
}
