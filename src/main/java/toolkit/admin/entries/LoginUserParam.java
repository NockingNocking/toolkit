package toolkit.admin.entries;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.admin.entries
 **/


import lombok.Data;

@Data
public class LoginUserParam {
  
  //用户名
  private String userName;
  
  //用户密码
  private String password;
}
