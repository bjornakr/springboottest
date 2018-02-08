package no.bjornakr.springboottest.create_respondent;

import no.bjornakr.springboottest.common.error_handling.EntityConstructionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value="/respondents")
public class Controller {
    private final ApplicationService appService;

    @Autowired
    Controller(ApplicationService appService) {
        this.appService = appService;
    }

    @RequestMapping(method = POST)
    public ResponseEntity<?> postit(@RequestBody RequestDto requestDto) {
        try {
            Long id = appService.save(requestDto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();

            return ResponseEntity.created(location).build();
        }
        catch (EntityConstructionException e) {
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponseDto);
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            return ResponseEntity.badRequest().build();
        }
    }
//    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
//        this.validateUser(userId);
}
