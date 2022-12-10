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

CREATE TABLE userresult (
  id INT,
  username VARCHAR UNIQUE,
  tn1 VARCHAR NOT NULL,
  ts1 VARCHAR NOT NULL,
  tn2 VARCHAR NOT NULL,
  ts2 VARCHAR NOT NULL,
  tn3 VARCHAR NOT NULL,
  ts3 VARCHAR NOT NULL,
  tn4 VARCHAR NOT NULL,
  ts4 VARCHAR NOT NULL,
  tn5 VARCHAR NOT NULL,
  ts5 VARCHAR NOT NULL,
  primary key(id)
);
