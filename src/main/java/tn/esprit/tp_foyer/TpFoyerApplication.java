package tn.esprit.tp_foyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class TpFoyerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpFoyerApplication.class, args);
	}

}