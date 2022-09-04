package by.savitsky.englishlearn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class })
public class EnglishLearnApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EnglishLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
