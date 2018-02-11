package no.bjornakr.springboottest.common.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="respondents")
public class Respondent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Required by JPA

    @Embedded
    private Name name;

    @Embedded
    private ContactInformation contactInformation;

    protected Respondent() {
        // Required by JPA - DO NOT USE!!!
    }

    public Respondent(
            Name name,
            ContactInformation contactInformation
    ) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(contactInformation);
        this.name = name;
        this.contactInformation = contactInformation;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }
}
