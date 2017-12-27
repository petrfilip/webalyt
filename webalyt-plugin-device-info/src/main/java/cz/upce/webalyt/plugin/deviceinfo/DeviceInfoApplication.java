package cz.upce.webalyt.plugin.deviceinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cz.upce.webalyt")
public class DeviceInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceInfoApplication.class, args);
	}
}
