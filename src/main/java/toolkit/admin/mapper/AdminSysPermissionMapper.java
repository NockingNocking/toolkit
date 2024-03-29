package toolkit.admin.mapper;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.mapper
 **/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import toolkit.admin.entries.AdminSysPermission;

import java.util.List;

public interface AdminSysPermissionMapper extends BaseMapper<AdminSysPermission> {
  
  
  /**
   * 通过用户id查询用户的权限数据
   * @param userId
   * @return
   */
  @Select({"<script>"+
      " SELECT p.* FROM"+
      " sys_user u"+
      " LEFT JOIN sys_role_permission_relation r ON u.role_id = r.role_id"+
      " LEFT JOIN sys_permission p on r.permission_id = p.permission_id"+
      " WHERE u.user_id = #{userId}"+
      "</script>"
  })
  List<AdminSysPermission> selectPermissionList(@Param("userId") Integer userId);
}


