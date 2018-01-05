package cz.upce.webalyt.plugin.urlrecorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cz.upce.webalyt")
public class ResizeRecorderPluginApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(ResizeRecorderPluginApplication.class, args);
    }
}
