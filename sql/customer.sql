CREATE TABLE app.customer
(
    id serial,
    name character varying NOT NULL,
    age smallint NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.customer
    OWNER to keycloak_db_user;