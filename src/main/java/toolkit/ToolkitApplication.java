package toolkit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("toolkit.mapper")//这要修改为你自己mapper放的路径
public class ToolkitApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(ToolkitApplication.class, args);
  }
  
}
