package no.bjornakr.springboottest.get_respondent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody ResponseDto get(@PathVariable Long id) { // (@RequestParam(value = "id", required = false) Long id) {
        ResponseDto response = appService.getOne(id);
//        ResponseDto response = new ResponseDto();
//        response.setFirstName("Erkebiskop");
        return response;
    }
}
