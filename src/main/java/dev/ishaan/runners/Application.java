package dev.ishaan.runners;

import dev.ishaan.runners.run.Location;
import dev.ishaan.runners.run.Run;
import dev.ishaan.runners.user.User;
import dev.ishaan.runners.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@SpringBootApplication
public class Application {
	private static final Logger log= LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);



    }
    @Bean
	CommandLineRunner runner(UserRestClient client){
		return  args -> {
			List<User> users= client.findAll();
			System.out.println(users);


		};
	}


//	@Bean
//	CommandLineRunner runner(){
//		return args -> {
//			Run run = new Run(1, "First run", LocalDateTime.now(),LocalDateTime.now().plus(1, ChronoUnit.HOURS),5, Location.OUTDOOR);
//			log.info("Run"+ run);
//		};
//	}


}
