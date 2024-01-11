package top.codx.todotask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@Slf4j
//@MapperScan("top.codx.todotask.model.mapper")
//@EnableTransactionManagement
//@EnableScheduling
public class TodotaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodotaskApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  平台启动成功   ლ(´ڡ`ლ)ﾞ ");
    }

}
