package no.bjornakr.springboottest.get_respondent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value="/respondents")
public class GetRespondentController {
    private static final Logger Logger = LoggerFactory.getLogger(GetRespondentController.class);


    private final GetRespondentApplicationService appService;

    @Autowired
    public GetRespondentController(GetRespondentApplicationService appService) {
        this.appService = appService;
    }



    @RequestMapping(value="/{id}", method = GET)
    public @ResponseBody ResponseEntity<ResponseDto> get(@PathVariable Long id) { // (@RequestParam(value = "id", required = false) Long id) {
        Logger.debug("GET .../respondents/" + id);
        try {
            ResponseDto response = appService.getOne(id);
            Logger.debug("Found Respondent: " + response.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            Logger.debug("Respondent with id " + id + " not found.");
            return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            Logger.error("FAILED: GET .../respondents/" + id);
            Logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
