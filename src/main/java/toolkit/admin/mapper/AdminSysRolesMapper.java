package toolkit.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import toolkit.admin.entries.AdminSysPermission;
import toolkit.admin.entries.AdminSysRoles;
import toolkit.admin.entries.AdminSysUser;

import java.util.List;

/**
 * @author: Nocking
 * @date: 2024/2/26
 * @package: toolkit.admin.mapper
 **/
public interface AdminSysRolesMapper extends BaseMapper<AdminSysUser> {
  
  
  /**
   * 通过用户id查询用户的角色
   * @param userId
   * @return
   */
  @Select({"<script>"+
      " SELECT p.* FROM"+
      " sys_user u"+
      " LEFT JOIN sys_user_role_relation r ON u.user_id = r.user_id"+
      " LEFT JOIN sys_roles p on r.role_id = p.role_id"+
      " WHERE u.user_id = #{userId}"+
      "</script>"
  })
  List<AdminSysRoles> selectRoleLists(@Param("userId") Integer userId);
}

