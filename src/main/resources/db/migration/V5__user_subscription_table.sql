CREATE TABLE user_subscriptions
(

    subscriber_id TEXT,
    channel_id TEXT,

    FOREIGN KEY (subscriber_id) REFERENCES usr(id) ON DELETE CASCADE,
    FOREIGN KEY (channel_id) REFERENCES usr(id) ON DELETE CASCADE
);