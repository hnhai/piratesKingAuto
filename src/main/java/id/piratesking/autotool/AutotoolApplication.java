package id.piratesking.autotool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AutotoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutotoolApplication.class, args);
	}

}
