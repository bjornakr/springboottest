package no.bjornakr.springboottest.get_respondent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/respondents")
public class GetRespondentController {
    private static final Logger Logger = LoggerFactory.getLogger(GetRespondentController.class);

    private final GetRespondentApplicationService appService;

    @Autowired
    public GetRespondentController(final GetRespondentApplicationService appService) {
        this.appService = appService;
    }


    @RequestMapping(value = "/{id}", method = GET)
    public @ResponseBody ResponseEntity get(@PathVariable Long id) { // (@RequestParam(value = "id", required = false) Long id) {
        Logger.debug("GET .../respondents/" + id);
        try {
            return getRespondent(id);
        }
        catch (Exception e) {
            return handleError(id, e);
        }
    }

    private ResponseEntity getRespondent(Long id) {
        Optional<ResponseDto> response = appService.getOne(id);
        return response
                .map(this::foundResponse)
                .orElseGet(() -> notFoundResponse(id));
    }

    private ResponseEntity handleError(Long id, Exception e) {
        Logger.error("FAILED: GET .../respondents/" + id);
        Logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private ResponseEntity foundResponse(ResponseDto response) {
        Logger.debug("FOUND OK: " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity notFoundResponse(Long id) {
        Logger.debug("NOT FOUND:  .../respondents/" + id);
        return ResponseEntity.notFound().build();
    }
}
