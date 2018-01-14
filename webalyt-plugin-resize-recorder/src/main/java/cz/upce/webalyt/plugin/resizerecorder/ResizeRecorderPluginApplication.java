package cz.upce.webalyt.plugin.resizerecorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cz.upce.webalyt")
@EntityScan("cz.upce.webalyt")
@EnableJpaRepositories("cz.upce.webalyt")
public class ResizeRecorderPluginApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(ResizeRecorderPluginApplication.class, args);
    }
}
