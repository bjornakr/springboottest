package no.bjornakr.springboottest.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    protected Person() {
        // Required by JPA
    }

    public Person(
            String firstName,
            String lastName
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        String template = "Person[id=%d, firstName='%s', lastName='%s'";
        return String.format(template, id, firstName, lastName);
    }
}
