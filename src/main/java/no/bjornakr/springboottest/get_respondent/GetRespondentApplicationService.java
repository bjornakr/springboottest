package no.bjornakr.springboottest.get_respondent;

import no.bjornakr.springboottest.common.repository.RespondentRepository;
import no.bjornakr.springboottest.common.domain.Respondent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetRespondentApplicationService {

    private final RespondentRepository respondentRepository;

    @Autowired
    public GetRespondentApplicationService(RespondentRepository respondentRepository) {
        this.respondentRepository = respondentRepository;
    }

    ResponseDto getOne(Long id) {
        Respondent respondent = respondentRepository.getOne(id);
        RespondentToDtoMapper mapper = new RespondentToDtoMapper();
        return mapper.apply(respondent);
    }
}
