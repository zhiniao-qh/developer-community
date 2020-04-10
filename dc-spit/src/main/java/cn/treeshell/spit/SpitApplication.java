package cn.treeshell.spit;

import cn.treeshell.common.util.JwtUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/*
    这里不加载 MyBatis Plus，因为只用到了 ID 生成器
    @AutoConfigureAfter({DataSourceAutoConfiguration.class, MybatisPlusLanguageDriverAutoConfiguration.class})
    因此，不加载 DataSourceAutoConfiguration 即可
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class, args);
    }

    /**
     * 利用 MyBatis Plus 的主键 ID 生成器生成全局唯一 ID
     * @return
     */
    @Bean
    public IdWorker idWorker() {

        return new IdWorker();
    }

    @Bean
    @ConfigurationProperties("jwt.config")
    public JwtUtil jwtUtil() {

        return new JwtUtil();
    }
}
