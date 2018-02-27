package no.bjornakr.desertsnake;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import no.bjornakr.desertsnake.common.repository.RespondentRepository;
import no.bjornakr.desertsnake.create_respondent.RequestDto;
import no.bjornakr.desertsnake.get_respondent.ResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndToEndTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RespondentRepository repository;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Before
    public void purgeDatabase() {
        repository.deleteAll();
    }

    @Test
    public void v3() {
        // 1) Create two Respondents.
        // 2) Get the respondents by id.
        // 3) Get all responents.
        // 4) Check status codes.
        // 5) Check that results (ResponseDtos) are correct.

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

        URI uri1 = post(r1);
        URI uri2 = post(r2);

        Response get1 = RestAssured.get(uri1);
        get1.then().statusCode(HttpStatus.OK.value());
        ResponseDto getResp1 = get1.as(ResponseDto.class);

        Response get2 = RestAssured.get(uri2);
        get1.then().statusCode(HttpStatus.OK.value());
        ResponseDto getResp2 = get2.as(ResponseDto.class);

        ResponseDto[] responses = RestAssured
                .get(String.format("http://localhost:%s/respondents", this.port))
                .as(ResponseDto[].class);

        assertThat(requestMatchesResponse(r1, getResp1), is(true));
        assertThat(requestMatchesResponse(r2, getResp2), is(true));
        assertThat(responses, is(arrayWithSize(2)));
        assertThat(responses, is(arrayContainingInAnyOrder(getResp1, getResp2)));
    }

    private boolean requestMatchesResponse(RequestDto req, ResponseDto res) {
        return req.getFirstName().equals(res.getFirstName())
                && req.getLastName().equals(res.getLastName())
                && req.getCity().equals(res.getCity())
                && req.getCountry().equals(res.getCountry())
                && req.geteMail().equals(res.getEmail())
                && req.getPostalCode().equals(res.getPostalCode())
                && req.getStreetAddress1().equals(res.getStreet1())
                && req.getStreetAddress2().equals(res.getStreet2())
                && req.getTelephoneNumber().equals(res.getTelephoneNumber());
    }

    private URI post(RequestDto requestDto) {
        Response post = RestAssured
                .given()
                .contentType("application/json")
                .body(requestDto)

                .when()
                .post(String.format("http://localhost:%s/respondents", this.port))
                ;

        post
                .then()
                .statusCode(HttpStatus.CREATED.value())
        ;

        try {
            return new URI(post.header(HttpHeaders.LOCATION));
        }
        catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
