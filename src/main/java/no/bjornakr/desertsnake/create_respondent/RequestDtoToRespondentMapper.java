package no.bjornakr.desertsnake.create_respondent;

import no.bjornakr.desertsnake.common.domain.ContactInformation;
import no.bjornakr.desertsnake.common.domain.Name;
import no.bjornakr.desertsnake.common.domain.PostalAddress;
import no.bjornakr.desertsnake.common.domain.Respondent;

public class RequestDtoToRespondentMapper {
    public Respondent apply(RequestDto requestDto) {
        Name name = new Name(
                requestDto.getFirstName(),
                requestDto.getLastName()
        );

        PostalAddress postalAddress = new PostalAddress(
                requestDto.getStreetAddress1(),
                requestDto.getStreetAddress2(),
                requestDto.getPostalCode(),
                requestDto.getCity(),
                requestDto.getCountry()
        );

        ContactInformation contactInformation = new ContactInformation
                .Builder(postalAddress, requestDto.geteMail())
                .telephoneNumber(requestDto.getTelephoneNumber())
                .build();

        return new Respondent(name, contactInformation);
    }
}
