package cn.gemframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivityServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ActivityServerApplication.class, args);
		System.out.println("---ActivityServerApplication：启动成功---");
	}
}
