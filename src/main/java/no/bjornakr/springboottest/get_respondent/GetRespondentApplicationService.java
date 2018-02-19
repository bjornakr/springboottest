package no.bjornakr.springboottest.get_respondent;

import no.bjornakr.springboottest.common.domain.Respondent;
import no.bjornakr.springboottest.common.repository.RespondentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetRespondentApplicationService {

    private final RespondentRepository respondentRepository;

    @Autowired
    public GetRespondentApplicationService(RespondentRepository respondentRepository) {
        this.respondentRepository = respondentRepository;
    }

    Optional<ResponseDto> getOne(Long id) {
        Respondent respondent = respondentRepository.findOne(id);
        return Optional.ofNullable(respondent)
                .map(new RespondentToDtoMapper()::apply);
    }
}
