package no.bjornakr.springboottest.common.repository;


import no.bjornakr.springboottest.common.domain.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespondentRepository extends JpaRepository<Respondent, Long> {
}
