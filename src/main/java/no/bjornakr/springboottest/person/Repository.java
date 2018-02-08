package no.bjornakr.springboottest.person;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Person, Long> {
    List<Person> findByLastName(String lastName);
}
