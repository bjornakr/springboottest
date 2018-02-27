package no.bjornakr.desertsnake;

// For some reason, this did not capture an error when retrieving respondents
// (a hibernate serialization error caused by LocalDateTime instead of java.sql.Timestamp).
// Using REST-assured fixed that, see EndToEndTest.

// TODO: Delete this class/file. I am only including it in source control sor later reference.

import io.restassured.RestAssured;
import no.bjornakr.desertsnake.create_respondent.RequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@Transactional
public class IntegrationTest {
    private MediaType jsonContentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @LocalServerPort
    private int port;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

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
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        RestAssured.port = port;
    }


    @Test
    public void createAndGetRespondents() throws Exception {
        RequestDto r1 = new RequestDto(
                "John",
                "Smith",
                "john.smith@geemail.com",
                "+47 12 34 56 78",
                "First street",
                "Booty lake",
                "0001",
                "Spring city",
                "Gorgonzola"
        );
        RequestDto r2 = new RequestDto(
                "Mary",
                "Jones",
                "m_jones76@testmail.com",
                "+01 987 654 321",
                "1st street",
                "2nd corner",
                "0002",
                "Farville",
                "Daisylandia"
        );

        MockHttpServletRequestBuilder post = post("/respondents").contentType(jsonContentType);

        ResultActions r1post = mockMvc.perform(post
                .content(json(r1))
                .contentType(jsonContentType)
        );

        ResultActions r2post = mockMvc.perform(post
                .content(json(r2))
                .contentType(jsonContentType)
        );

        r1post.andDo(re1 -> {
            r2post.andDo(re2 -> {
                String r1location = re1.getResponse().getHeader("location");
                String r2location = re2.getResponse().getHeader("location");
                MockHttpServletRequestBuilder grb1 = get(r1location).accept(MediaType.APPLICATION_JSON);
                MockHttpServletRequestBuilder grb2 = get(r2location).accept(MediaType.APPLICATION_JSON);
                MockHttpServletRequestBuilder grbAll = get("http://localhost/respondents").accept(MediaType.APPLICATION_JSON);
                mockMvc.perform(grb1).andDo(gre1 -> {
                    mockMvc.perform(grb2).andDo(gre2 -> {
                        mockMvc.perform(grbAll).andDo(greAll -> {
                            String json1 = gre1.getResponse().getContentAsString();
                            String json2 = gre2.getResponse().getContentAsString();
                            String jsonAll = greAll.getResponse().getContentAsString();

                            int i = 0;
                        });
                    });
                });
            });
        });


    }

    private String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
