package no.bjornakr.desertsnake.common.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name="respondents")
public class Respondent {
    @Id
    @SequenceGenerator(name="s", sequenceName="respondents_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s")
    private Long id;

    @Column(updatable=false, insertable=false)
    private Timestamp created;

    @Column(updatable=false, insertable=false)
    private Timestamp updated;


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

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }
}
