CREATE TABLE IF NOT EXISTS app.customer
(
    id serial PRIMARY KEY,
    name character varying NOT NULL,
    age smallint NOT NULL
);

CREATE TABLE IF NOT EXISTS app.account
(
	id serial PRIMARY KEY,
	account_type character varying NOT NULL,
	account_number uuid NOT NULL,
	balance bigint,
	customer int NOT NULL REFERENCES customer(id)


);