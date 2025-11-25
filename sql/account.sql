
CREATE TABLE app.account
(
	id serial,
	account_type character varying NOT NULL,
	balance bigint,
	customer_id integer,
	primary key(id),
	foreign key (customer_id) references app.customer(id)

)
ALTER TABLE IF EXISTS app.account
    OWNER to keycloak_db_user;
