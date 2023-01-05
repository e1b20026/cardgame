CREATE TABLE member (
    id IDENTITY,
    userName VARCHAR NOT NULL,
    gameexist boolean NOT NULL,
    exist1 boolean NOT NULL,
    exist2 boolean NOT NULL,
    exist3 boolean NOT NULL,
    exist4 boolean NOT NULL,
    result boolean NOT NULL
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
  rank INT,
  roleName VARCHAR,
  primary key(id)
);

CREATE TABLE postrecord (
  id INT,
  roleName VARCHAR,
  count INT,
  primary key(id)
);
