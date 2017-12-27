package cz.upce.webalyt.plugin.loadingperformance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cz.upce.webalyt")
public class LoadingPerformancePluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadingPerformancePluginApplication.class, args);
	}
}
