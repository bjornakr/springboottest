package no.bjornakr.springboottest.get_respondent;

import no.bjornakr.springboottest.common.domain.Respondent;
import no.bjornakr.springboottest.common.domain.PostalAddress;

class RespondentToDtoMapper {
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
                postalAddress.getCountry()
        );
    }
}