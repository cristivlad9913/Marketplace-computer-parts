package user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.modelmapper.ModelMapper;
@SpringBootApplication(scanBasePackages = {"org.modelmapper.ModelMapper"})
@EnableDiscoveryClient
@EnableFeignClients
public class OwnerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwnerServiceApplication.class, args);
	}

}
