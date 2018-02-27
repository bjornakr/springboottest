package no.bjornakr.desertsnake.get_respondent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.bjornakr.desertsnake.Application;
import no.bjornakr.desertsnake.common.domain.Respondent;
import no.bjornakr.desertsnake.common.domain.RespondentTest;
import no.bjornakr.desertsnake.common.repository.RespondentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GetRespondentControllerApiTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private RespondentRepository respondentRepository;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getRespondents_RespondentsExist_ReturnsAllRespondents() throws Exception {
        List<Respondent> respondents = Arrays.asList(
                RespondentTest.ValidRespondent,
                RespondentTest.ValidRespondent,
                RespondentTest.ValidRespondent
        );

        given(respondentRepository.findAll()).willReturn(respondents);

        MockHttpServletRequestBuilder requestBuilder = get("/respondents").accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();
                    List<ResponseDto> dtos = new ObjectMapper().readValue(json,
                            new TypeReference<List<ResponseDto>>(){}); // Will fail if result is not a RespondentDto.
                    assertThat(dtos, hasSize(3));
                })
        ;
    }

    @Test
    public void getRespondents_NoRespondents_Ok() throws Exception {
        List<Respondent> respondents = Collections.emptyList();

        given(respondentRepository.findAll()).willReturn(respondents);

        MockHttpServletRequestBuilder requestBuilder = get("/respondents").accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();
                    List<ResponseDto> dtos = new ObjectMapper().readValue(json,
                            new TypeReference<List<ResponseDto>>(){}); // Will fail if result is not a RespondentDto.
                    assertThat(dtos, is(empty()));
                })
        ;
    }

    @Test
    public void getRespondent_ValidId_ReturnsRespondent() throws Exception {
        Long testId = 999L;

        Respondent r = RespondentTest.ValidRespondent;
        given(respondentRepository.findOne(testId)).willReturn(r);

        MockHttpServletRequestBuilder requestBuilder = get("/respondents/" + testId).accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String json = mvcResult.getResponse().getContentAsString();
                    new ObjectMapper().readValue(json, ResponseDto.class); // Will fail if result is not a RespondentDto.
                })
        ;
    }

    @Test
    public void getRespondent_InvalidId_NotFound() throws Exception {
        given(respondentRepository.findOne(anyLong())).willReturn(null);

        MockHttpServletRequestBuilder requestBuilder = get("/respondents/999").accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void getRespondent_RepositoryFails_InternalServerError() throws Exception {
        given(respondentRepository.findOne(anyLong())).willThrow(new RuntimeException("Some kind of error."));

        MockHttpServletRequestBuilder requestBuilder = get("/respondents/999").accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isInternalServerError())
        ;
    }
}
