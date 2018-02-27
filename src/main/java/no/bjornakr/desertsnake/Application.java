package no.bjornakr.desertsnake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
// =>
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

//    @Bean
//    CommandLineRunner init(RespondentRepository createRespondentRepository) {
//        return (evt) -> {
//            createRespondentRepository.save(new Respondent(
//                    new Name("Testguy", "Johnson"),
//                    new ContactInformation.Builder(new PostalAddress(
//                            "Street 1",
//                            "Street 2",
//                            "0192",
//                            "Oslo",
//                            "Norway"
//                    ), "eee@mail.com")
//                    .telephoneNumber("+47 95 16 61 36")
//                    .build()
//            ));
////            System.out.println("Hi?");
//        };
//    }
}
