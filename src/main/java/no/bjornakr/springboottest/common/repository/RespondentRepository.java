package no.bjornakr.springboottest.common.repository;


import no.bjornakr.springboottest.common.domain.Respondent;
import org.springframework.data.repository.CrudRepository;

public interface RespondentRepository extends CrudRepository<Respondent, Long> {
}
