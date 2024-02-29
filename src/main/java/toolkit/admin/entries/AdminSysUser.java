package toolkit.admin.entries;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.mapper
 **/

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import toolkit.frame.utils.transforms.StringTypeTransform;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)//链式; 存取器。通过该注解可以控制getter和setter方法的形式。
@TableName("sys_user")
@JsonIgnoreProperties(value = {"password"})
public class AdminSysUser implements Serializable {
  
  private static final long serialVersionUID = 915478504870211231L;
  
  @TableId(value = "user_id", type = IdType.AUTO)
  private Integer userId;
  
  //账号
  private String account;
  
  //用户名
  private String userName;
  
  //用户密码
  private String password;
  
  //用户角色
  @TableField(typeHandler = StringTypeTransform.class,value = "`roles`")
  private List<String> roles;
  
  //用户岗位
  private String position;
  
  //上一次登录时间
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
  private Date lastLoginTime;
  
  //账号是否可用。默认为1（可用）
  private Boolean enabled;
  
  //是否过期。默认为1（没有过期）
  private Boolean accountNotExpired;
  
  //账号是否锁定。默认为1（没有锁定）
  private Boolean accountNotLocked;
  
  //证书（密码）是否过期。默认为1（没有过期）
  private Boolean credentialsNotExpired;
  
  //创建时间
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
  private Date createTime;
  
  //修改时间
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
  private Date updateTime;
  
}

