DROP TABLE IF EXISTS message;

CREATE TABLE message
(
    id            BIGSERIAL,
    text          TEXT                     NOT NULL,
    creation_date timestamp with time zone NOT NULL DEFAULT now(),
    user_id       text,

    PRIMARY KEY (id),
    foreign key (user_id) REFERENCES usr (id) ON DELETE CASCADE
);

CREATE INDEX message_text_idx ON message (text);