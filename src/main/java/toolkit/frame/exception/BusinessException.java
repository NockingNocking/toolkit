package toolkit.frame.exception;

/**
 * @author: Nocking
 * @date: 2024/2/18
 * @package: toolkit.frame.exception
 **/




import lombok.Data;
import toolkit.frame.api.ApiCode;


/**
 * 自定义异常类
 */
@Data
public class BusinessException extends RuntimeException{
  
  private int code;
  
  private String msg;
  
  
  public BusinessException(ApiCode apiCode) {
    super(apiCode.getMsg());
    this.code = apiCode.getCode();
    this.msg = apiCode.getMsg();
  }
  
  
}



