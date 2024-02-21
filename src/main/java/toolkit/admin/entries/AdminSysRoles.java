package toolkit.admin.entries;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Nocking
 * @date: 2024/2/21
 * @package: toolkit.admin.entries
 **/

@Data
@Accessors(chain = true)//链式; 存取器。通过该注解可以控制getter和setter方法的形式。
@TableName("sys_roles")
public class AdminSysRoles {
  
  @TableId(value = "role_id", type = IdType.ID_WORKER)
  private Integer roleId;
  
  private String roleName; // 角色名称
  
  private String roleCode; // 角色代码
  
  private String createdBy; // 创建人
  
  private Integer createdTime; // 创建时间

}
