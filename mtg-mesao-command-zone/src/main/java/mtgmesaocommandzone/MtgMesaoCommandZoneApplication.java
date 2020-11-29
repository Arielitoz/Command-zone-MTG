package mtgmesaocommandzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MtgMesaoCommandZoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtgMesaoCommandZoneApplication.class, args);
	}

}
