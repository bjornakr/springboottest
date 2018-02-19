package no.bjornakr.springboottest.create_respondent;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.bjornakr.springboottest.Application;
import no.bjornakr.springboottest.common.error_handling.EntityConstructionException;
import no.bjornakr.springboottest.common.error_handling.ErrorMessages;
import no.bjornakr.springboottest.common.repository.RespondentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ControllerTest {
    private MediaType jsonContentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private RespondentRepository repository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
//        repository.deleteAll();
    }

    private RequestDto validRequestContent() {
        return new RequestDto(
                "John",
                "Tester",
                "eee@mail.com",
                "+47 12 34 56",
                "street 1",
                "street 2",
                "1234",
                "Oslo",
                "Norway"
        );
    }

    private String createJsonEntityConstructionException(Class entityClass, String errorMessage) throws IOException {
        Exception e = new EntityConstructionException(entityClass, errorMessage);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage());
        return json(errorResponseDto);
    }

    @Test
    public void createRespondent_AllFieldsValid_Created() throws Exception {
        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);

        ResultActions resultActions = mockMvc.perform(post
                .content(json(validRequestContent()))
                .contentType(jsonContentType)
        );

        resultActions.andExpect(status().isCreated())
//        .andExpect(content().contentType(jsonContentType))
                .andExpect(header().string("location", containsString("http://localhost/respondents/")));
    }

    @Test
    public void createRespondent_AllFieldsValid_OptionalFieldsOmitted_Created() throws Exception {
        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);

        RequestDto request = validRequestContent();
        request.setTelephoneNumber(null);
        request.setStreetAddress1(null);
        request.setStreetAddress2(null);
        request.setPostalCode(null);
        request.setCity(null);
        request.setCountry(null);

        ResultActions resultActions = mockMvc.perform(post
                .content(json(validRequestContent()))
                .contentType(jsonContentType)
        );

        resultActions.andExpect(status().isCreated())
//        .andExpect(content().contentType(jsonContentType))
                .andExpect(header().string("location", containsString("http://localhost/respondents/")));
    }

    @Test
    public void createRespondent_FirstNameIsEmpty_BadRequest() throws Exception {
        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);

        RequestDto request = validRequestContent();
        request.setFirstName("   ");

        ResultActions a = mockMvc.perform(post
                .content(json(request))
                .contentType(jsonContentType)
        );

        a.andExpect(status().isBadRequest());
        a.andExpect(content().contentType(jsonContentType));
        a.andDo(mvcResult -> assertThatErrorContains(ErrorMessages.FirstNameCannotBeEmpty.toString(), mvcResult));
    }



    @Test
    public void createRespondent_LastNameIsEmpty_BadRequest() throws Exception {
        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);

        RequestDto request = validRequestContent();
        request.setLastName("   ");

        ResultActions a = mockMvc.perform(post
                .content(json(request))
                .contentType(jsonContentType)
        );

        a.andExpect(status().isBadRequest());
        a.andExpect(content().contentType(jsonContentType));
        a.andDo(mvcResult -> assertThatErrorContains(ErrorMessages.LastNameCannotBeEmpty.toString(), mvcResult));
    }

    @Test
    public void createRespondent_EmailIsEmpty_BadRequest() throws Exception {
        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);

        RequestDto request = validRequestContent();
        request.setEmail("   ");

        ResultActions a = mockMvc.perform(post
                .content(json(request))
                .contentType(jsonContentType)
        );

        a.andExpect(status().isBadRequest());
        a.andExpect(content().contentType(jsonContentType));
        a.andDo(mvcResult -> assertThatErrorContains(ErrorMessages.EmailCannotBeEmpty.toString(), mvcResult));
    }


    @Test
    public void createRespondent_InvalidEmail_BadRequest() throws Exception {
        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);

        final String invalidEmail = "not_an_email";

        RequestDto request = validRequestContent();
        request.setEmail(invalidEmail);

        ResultActions a = mockMvc.perform(post
                .content(json(request))
                .contentType(jsonContentType)
        );

        a.andExpect(status().isBadRequest());
        a.andExpect(content().contentType(jsonContentType));
        a.andDo(mvcResult -> assertThatErrorContains(ErrorMessages.InvalidEmail.toString(), mvcResult));
    }

    @Test
    public void createRespondent_InvalidPostalCode_BadRequest() throws Exception {
        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);

        final String invalidPostalCode = "not_a_postal_code";

        RequestDto requestContent = validRequestContent();
        requestContent.setPostalCode(invalidPostalCode);

        ResultActions a = mockMvc.perform(post
                .content(json(requestContent))
                .contentType(jsonContentType)
        );

        a.andExpect(status().isBadRequest());
        a.andExpect(content().contentType(jsonContentType));
        a.andDo(mvcResult -> assertThatErrorContains(ErrorMessages.InvalidPostalCode.toString(), mvcResult));
    }

//    @Test
//    public void createRespondent_validFields_willTrimAllFields() throws Exception {
//        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);
//        RequestDto request = new RequestDto(
//                "  John  ",
//                "  Tester  ",
//                "  eee@mail.com  ",
//                "  +47 12 34 56  ",
//                "  Street 1  ",
//                "  Street 2  ",
//                "  1234  ",
//                "  Testico City  ",
//                "  Testlandia  "
//        );
//
//        ResultActions resultActions = mockMvc.perform(post
//                .content(json(validRequestContent()))
//                .contentType(jsonContentType)
//        );
//
//        resultActions.andExpect(status().isCreated())
////        .andExpect(content().contentType(jsonContentType))
//                .andExpect(header().string("location", containsString("http://localhost/respondents/")));
//
//
//
//    }


    private void assertThatErrorContains(String errorMessage, MvcResult result) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = result.getResponse().getContentAsString();
        ErrorResponseDto error = mapper.readValue(json, ErrorResponseDto.class);
        assertThat(error.getErrorMessage(), containsString(errorMessage));
    }

    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
