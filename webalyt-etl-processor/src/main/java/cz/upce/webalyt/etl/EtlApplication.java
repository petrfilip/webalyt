package cz.upce.webalyt.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableDiscoveryClient
public class EtlApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(EtlApplication.class, args);
    }

}
