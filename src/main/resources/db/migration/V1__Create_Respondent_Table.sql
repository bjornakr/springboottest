CREATE TABLE respondents (
    id BIGSERIAL PRIMARY KEY,
    first_name varchar(128),
    last_name varchar(128),
    telephone_number varchar(128),
    e_mail varchar(128),
    street1 varchar(128),
    street2 varchar(128),
    city varchar(128),
    postal_code varchar(4),
    country varchar(128)
);

