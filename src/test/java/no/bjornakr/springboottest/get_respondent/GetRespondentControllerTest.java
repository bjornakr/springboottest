package no.bjornakr.springboottest.get_respondent;

import no.bjornakr.springboottest.common.domain.ContactInformationTest;
import no.bjornakr.springboottest.common.domain.NameTest;
import no.bjornakr.springboottest.common.domain.Respondent;
import no.bjornakr.springboottest.common.repository.RespondentRepository;
import no.bjornakr.springboottest.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class GetRespondentControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RespondentRepository respondentRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void addRespondent() {
        respondentRepository.save(new Respondent(
                NameTest.ValidName,
                ContactInformationTest.ValidContactInformation
        ));
    }

    @Test
    public void getRespondent_validId_returnsRespondent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/respondents/1").accept(MediaType.APPLICATION_JSON);
        ResultActions resultActions = this.mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isOk());
    }
}
