package no.bjornakr.springboottest.get_respondent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value="/respondents")
public class GetRespondentController {
    private final GetRespondentApplicationService appService;

    @Autowired
    public GetRespondentController(GetRespondentApplicationService appService) {
        this.appService = appService;
    }



    @RequestMapping(value="/{id}", method = GET)
    public @ResponseBody ResponseEntity<ResponseDto> get(@PathVariable Long id) { // (@RequestParam(value = "id", required = false) Long id) {
        try {
            ResponseDto response = appService.getOne(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
//        ResponseDto response = new ResponseDto();
//        response.setFirstName("Erkebiskop");
    }
}
