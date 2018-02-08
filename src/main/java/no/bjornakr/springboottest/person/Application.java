package no.bjornakr.springboottest.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(Repository repository) {
        return (args) -> {
            // save a couple of Persons
            repository.save(new Person("Jack", "Bauer"));
            repository.save(new Person("Chloe", "O'Brian"));
            repository.save(new Person("Kim", "Bauer"));
            repository.save(new Person("David", "Palmer"));
            repository.save(new Person("Michelle", "Dessler"));

            // fetch all Persons
            System.out.println("Persons found with findAll():");
            System.out.println("-------------------------------");
            for (Person person : repository.findAll()) {
                System.out.println(person.toString());
            }
            System.out.println("");

            // fetch an individual Person by ID
            Person Person = repository.findOne(1L);
            System.out.println("Person found with findOne(1L):");
            System.out.println("--------------------------------");
            System.out.println(Person.toString());
            System.out.println("");

            // fetch Persons by last name
            System.out.println("Person found with findByLastName('Bauer'):");
            System.out.println("--------------------------------------------");
            for (Person bauer : repository.findByLastName("Bauer")) {
                System.out.println(bauer.toString());
            }

            System.out.println("");
        };
    }
}
