package cn.treeshell.qa;

import cn.treeshell.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaApplication.class, args);
	}

	@Bean
	@ConfigurationProperties("jwt.config")
	public JwtUtil jwtUtil() {

		return new JwtUtil();
	}
}
