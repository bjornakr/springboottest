package no.bjornakr.springboottest;

import no.bjornakr.springboottest.common.repository.RespondentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
// =>
@SpringBootApplication //(scanBasePackages={"no.nubu.springtest02.createrespondent"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(RespondentRepository createRespondentRepository) {
        return (evt) -> {
//            createRespondentRepository.save(new Respondent(
//                    new Name("Testguy", "Johnson"),
//                    "eee@mail.com",
//                    "+47 95 16 61 36",
//                    new PostalAddress(
//                            "Street 1",
//                            "Street 2",
//                            "0192",
//                            "Oslo",
//                            "Norway"
//                    )
//            ));
//            System.out.println("Hi?");
        };
    }
}
