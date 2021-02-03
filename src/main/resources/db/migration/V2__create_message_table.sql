DROP TABLE IF EXISTS message;

CREATE TABLE message
(
    id            BIGSERIAL,
    text          TEXT                     NOT NULL,
    creation_date timestamp with time zone NOT NULL DEFAULT now(),

    PRIMARY KEY (id)
);

CREATE INDEX message_text_idx ON message (text);