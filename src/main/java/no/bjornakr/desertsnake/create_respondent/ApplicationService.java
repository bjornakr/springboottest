package no.bjornakr.desertsnake.create_respondent;


import no.bjornakr.desertsnake.common.domain.Respondent;
import no.bjornakr.desertsnake.common.repository.RespondentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ApplicationService {

    private final RespondentRepository repository;

    @Autowired // ← Could be omitted, as there is only one constructor.
    ApplicationService(RespondentRepository repository)
    {
        this.repository = repository;
    }


    Long save(RequestDto requestDto) {
            RequestDtoToRespondentMapper mapper = new RequestDtoToRespondentMapper();
            Respondent respondent = mapper.apply(requestDto);
            Respondent result = repository.save(respondent);
            return result.getId();
    }
}
