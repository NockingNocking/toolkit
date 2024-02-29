package toolkit.frame.utils;

/**
 * @description： minio工具类
 * @author: Nocking
 * @date: 2024/2/20
 * @package: toolkit.frame.minio
 **/
import com.baomidou.mybatisplus.core.toolkit.Constants;

import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class MinioUtils {
  
  @Autowired
  private MinioClient minioClient;
  
  
  /**
   * description: 判断bucket是否存在，不存在则创建
   *
   * @return: void
   * @author: Nocking
   */
  public boolean existBucket(String name) {
    boolean exists;
    try {
      exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
      if (exists) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return false;
  }
  
  /**
   * description: 上传文件
   *
   * @param multipartFile
   * @return: java.lang.String
   * @author: Nocking
   */
  public List<String> upload(MultipartFile[] multipartFile, String bucketName) {
    List<String> names = new ArrayList<>(multipartFile.length);
    
    for (MultipartFile file : multipartFile) {
      String fileName = file.getOriginalFilename();
      String[] split = fileName.split("\\.");
      if (split.length > 1) {
        fileName = split[0] + "_" + System.currentTimeMillis() + "." + split[1];
      } else {
        fileName = fileName + System.currentTimeMillis();
      }
      InputStream in = null;
      try {
        in = file.getInputStream();
        minioClient.putObject(PutObjectArgs.builder()
            .bucket(bucketName)
            .object(fileName)
            .stream(in, in.available(), -1)
            .contentType(file.getContentType())
            .build()
        );
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (in != null) {
          try {
            in.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      
      names.add(fileName);
    }
    return names;
  }
  
  /**
   * description: 下载文件
   *
   * @param fileName
   * @return: ResponseEntity<byte [ ]>
   * @author: Nocking
   */
  public ResponseEntity<byte[]> download(String fileName, String bucketName) throws IOException {
    ResponseEntity<byte[]> responseEntity = null;
    InputStream in = null;
    ByteArrayOutputStream out = null;
    try {
      in = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
      out = new ByteArrayOutputStream();
      IOUtils.copy(in, out);
      //封装返回值
      byte[] bytes = out.toByteArray();
      HttpHeaders headers = new HttpHeaders();
      try {
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, Constants.UTF_8));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      headers.setContentLength(bytes.length);
      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
      headers.setAccessControlExposeHeaders(Arrays.asList("*"));
      responseEntity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (in != null) {
          try {
            in.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return responseEntity;
  }
  
  /**
   * 根据文件路径得到预览文件绝对地址
   *
   * @param bucketName
   * @param fileName
   * @return String
   */
  public String getPreviewFileUrl(String bucketName, String fileName, Integer expiry) {
    try {
      return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(fileName).method(Method.GET).expiry(expiry, TimeUnit.SECONDS).build());
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
    
  }
  
  /**
   * 获取文件流
   *
   * @param fileName   文件名
   * @param bucketName 桶名（文件夹）
   * @return InputStream
   */
  public InputStream getFileInputStream(String fileName, String bucketName) {
    try {
      return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage());
    }
    return null;
  }
  
  /**
   * @param bucketName:
   * @description: 删除桶下面所有文件
   */
  public void deleteBucketFile(String bucketName) {
    try {
      if (StringUtils.isBlank(bucketName)) {
        return;
      }
      boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
      if (isExist) {
        minioClient.deleteBucketEncryption(DeleteBucketEncryptionArgs.builder().bucket(bucketName).build());
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage());
    }
  }
  
  
  /**
   * @param bucketName:
   * @description: 删除桶
   * @date 2022/8/16 14:36
   */
  public boolean deleteBucketName(String bucketName) {
    try {
      if (StringUtils.isBlank(bucketName)) {
        return false;
      }
      boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
      if (isExist) {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
      }
      return existBucket(bucketName);
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage());
      return false;
    }
  }
}