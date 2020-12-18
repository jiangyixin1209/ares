package top.jiangyixin.ares.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ares Bootstrap
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午4:55
 */
@SpringBootApplication
@MapperScan("top.jiangyixin.ares.admin.mapper")
public class AresAdminApplication {

  public static void main(String[] args) {
  	 SpringApplication.run(AresAdminApplication.class, args);
  }
}
