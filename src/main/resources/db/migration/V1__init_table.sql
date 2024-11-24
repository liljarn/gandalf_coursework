CREATE TABLE user_data(
    uuid        UUID            PRIMARY KEY NOT NULL,
    email       VARCHAR(256)    UNIQUE      NOT NULL,
    password    TEXT                        NOT NULL,
    salt        TEXT                        NOT NULL,
    first_name  VARCHAR(256)                NOT NULL,
    last_name   VARCHAR(256)                NOT NULL,
    birth_date  DATE                        NOT NULL,
    photo_url   TEXT                        NOT NULL
);