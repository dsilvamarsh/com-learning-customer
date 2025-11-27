DROP TABLE app.account;
CREATE TABLE app.account
(
	id serial,
	account_type character varying NOT NULL,
	account_number character varying not null,
	balance bigint,
	customer int,
	primary key(id),
	CONSTRAINT fx_customer FOREIGN KEY (customer) REFERENCES app.customer(id) ON DELETE CASCADE

);
ALTER TABLE IF EXISTS app.account
    OWNER to keycloak_db_user;