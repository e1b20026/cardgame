CREATE TABLE member (
    id IDENTITY,
    userName VARCHAR NOT NULL,
    exist boolean NOT NULL
);

CREATE TABLE trump (
    id IDENTITY,
    suit VARCHAR NOT NULL,
    number VARCHAR NOT NULL,
    place boolean NOT NULL
);

CREATE TABLE randtrump (
  id INT,
  suit VARCHAR,
  number VARCHAR,
  primary key(id)
);
