DROP TABLE IF EXISTS comment;

CREATE TABLE comment
(
    id         BIGSERIAL,
    text       TEXT,
    message_id BIGINT,
    user_id    TEXT,

    PRIMARY KEY (id),
    FOREIGN KEY (message_id) REFERENCES message (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES usr (id) ON DELETE CASCADE
);