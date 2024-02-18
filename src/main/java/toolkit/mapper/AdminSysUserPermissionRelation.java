package toolkit.mapper;

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

@Data
@Accessors(chain = true)//链式; 存取器。通过该注解可以控制getter和setter方法的形式。
@TableName("sys_user_permission_relation")
public class AdminSysUserPermissionRelation  {
  
  @TableId(value = "user_permission_relation_id", type = IdType.ID_WORKER)
  private Integer userPermissionRelationId;
  
  private Integer userId;
  
  private Integer permissionId;
}


