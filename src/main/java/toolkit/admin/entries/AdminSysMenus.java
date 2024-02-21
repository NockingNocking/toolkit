package toolkit.admin.entries;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author: Nocking
 * @date: 2024/2/19
 * @package: toolkit.entries
 **/
@Data
@TableName("sys_menus")
public class AdminSysMenus implements Serializable {
  
  @TableId(value = "id", type = IdType.ID_WORKER)
  private Integer id;
  
  //父级路由id
  private Integer parentId;
  
  //路径
  private String path;
  
  //组件名称
  private String component;
  
  //重定向路径
  private String redirect;
  
  //类型
  private Integer type;
  
  //标题
  private String title;
  
  //svg图标
  private String svgIcon;
  
  //图标
  private String icon;
  
  //是否需要keepalive
  private Boolean keepAlive;
  
  //是否需要隐藏
  private Boolean hidden;
  
  //是否需要在面包屑展示
  private Boolean breadcrumb;
  
  //排序
  private Boolean sort;
  
  //激活时的菜单
  private String activeMenu;
  
  //状态
  private Boolean status;
  
  //权限角色
  private String roles;
  
  //权限
  private String permission;
  
  //是否需要展示tab
  private Boolean showInTabs;
  
  //是否需要一直展示
  private Boolean alwaysShow;
  
  //是否需要前置
  private Boolean affix;
  
  // 创建时间
  private String createdAt;
  
  // 子菜单集合
  private List<AdminSysMenus> children;
  
 
}
