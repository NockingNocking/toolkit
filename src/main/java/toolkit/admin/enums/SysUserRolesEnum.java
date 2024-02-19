package toolkit.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author: Nocking
 * @date: 2024/2/19
 * @package: toolkit.admin.enums
 **/
@Getter
public enum SysUserRolesEnum {
  SUPER_ADMIN((short) 1,"超级管理员"),
  SYS_ADMIN((short) 2,"系统管理员"),
  ADMIN((short) 3,"普通管理员"),
  NORMAL((short) 4,"普通账号");
  
  @EnumValue //标记存储到数据库的值
  private final short role;
  
  
  @JsonValue //标记json返回的值
  private final String remark;
  
  SysUserRolesEnum(short code, String remark) {
    this.role = code;
    this.remark = remark;
  }
}
