DROP TABLE IF EXISTS usr;

CREATE TABLE usr
(
    id        TEXT,
    name      TEXT,
    user_pic  TEXT,
    email     TEXT,
    gender    TEXT,
    locale    TEXT,
    last_visit timestamp with time zone NOT NULL DEFAULT now(),

    PRIMARY KEY (id)
);