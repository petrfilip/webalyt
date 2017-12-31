package cz.upce.webalyt.plugin.scrollrecorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cz.upce.webalyt")
public class ScrollRecorderPluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrollRecorderPluginApplication.class, args);
	}
}
