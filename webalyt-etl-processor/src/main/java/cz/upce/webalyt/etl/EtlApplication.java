package cz.upce.webalyt.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("cz.upce.webalyt")
public class EtlApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(EtlApplication.class, args);
    }

}
