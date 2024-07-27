package az.edu.turing.profileapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProfileAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileAppApplication.class, args);
    }

}
