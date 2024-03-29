package toolkit.admin.entries;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.mapper
 **/
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)//链式; 存取器。通过该注解可以控制getter和setter方法的形式。
@TableName("sys_permission")
public class AdminSysPermission implements Serializable {
  
  @TableId(value = "permission_id", type = IdType.ID_WORKER)
  private Integer permissionId;
  
  private String permissionCode;
  
  private String permissionName;
  
  private String url;
}

