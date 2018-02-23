package no.bjornakr.desertsnake.get_respondent;

import no.bjornakr.desertsnake.common.domain.Respondent;
import no.bjornakr.desertsnake.common.domain.PostalAddress;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class RespondentToDtoMapper {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(RespondentToDtoMapper.class);

    ResponseDto apply(Respondent respondent) {
        PostalAddress postalAddress = respondent.getContactInformation().getPostalAddress();
        return new ResponseDto(
                respondent.getId(),
                respondent.getName().getFirstName(),
                respondent.getName().getLastName(),
                respondent.getContactInformation().getEmail(),
                respondent.getContactInformation().getTelephoneNumber(),
                postalAddress.getStreet1(),
                postalAddress.getStreet2(),
                postalAddress.getPostalCode(),
                postalAddress.getCity(),
                postalAddress.getCountry(),
                formatDate(respondent.getCreated()),
                formatDate(respondent.getUpdated())
        );
    }

    private String formatDate(LocalDateTime timestamp) {
        return DateTimeFormatter.ISO_DATE_TIME.format(timestamp);
    }
}
