CREATE TABLE member (
    id IDENTITY,
    userName VARCHAR NOT NULL,
    exist boolean NOT NULL
);

CREATE TABLE trump (
    id IDENTITY,
    suit VARCHAR NOT NULL ,
    number INT NOT NULL,
    place boolean NOT NULL,
    PRIMARY KEY (suit, number)
);
