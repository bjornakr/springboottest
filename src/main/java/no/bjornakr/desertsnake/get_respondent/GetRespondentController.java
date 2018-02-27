package no.bjornakr.desertsnake.get_respondent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/respondents")
public class GetRespondentController {
    private static final String ResourceName = "respondents";
    private static final Logger Logger = LoggerFactory.getLogger(GetRespondentController.class);

    private final GetRespondentApplicationService appService;

    @Autowired
    public GetRespondentController(final GetRespondentApplicationService appService) {
        this.appService = appService;
    }

    @RequestMapping(method = GET)
    public @ResponseBody ResponseEntity get() {
        Logger.debug("GET .../" + ResourceName);
        try {
            return getManyRespondents();
        }
        catch (Exception e) {
            Logger.error("FAILED: GET .../" + ResourceName);
            return handleError(e);
        }
    }


    @RequestMapping(value = "/{id}", method = GET)
    public @ResponseBody ResponseEntity get(@PathVariable Long id) { // (@RequestParam(value = "id", required = false) Long id) {
        Logger.debug("GET .../" + ResourceName + "/" + id);
        try {
            return getRespondent(id);
        }
        catch (Exception e) {
            Logger.error("FAILED: GET .../" + ResourceName +"/" + id);
            return handleError(e);
        }
    }

    private ResponseEntity getRespondent(Long id) {
        Optional<ResponseDto> response = appService.getOne(id);
        return response
                .map(this::foundResponse)
                .orElseGet(() -> notFoundResponse(id));
    }

    private ResponseEntity handleError(Exception e) {
        Logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private ResponseEntity foundResponse(ResponseDto response) {
        Logger.debug("FOUND OK: " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity notFoundResponse(Long id) {
        Logger.debug("NOT FOUND:  .../" + ResourceName + "/" + id);
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity getManyRespondents() {
        List<ResponseDto> respondents = appService.getAll();
        Logger.debug("FOUND OK: " + respondents.size() + " " + ResourceName);
        return new ResponseEntity<>(respondents, HttpStatus.OK);
    }
}
