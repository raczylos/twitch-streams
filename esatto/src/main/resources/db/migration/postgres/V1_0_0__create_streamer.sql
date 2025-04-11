CREATE TABLE STREAMER
(
    id                          SERIAL PRIMARY KEY,
    twitch_user_id              VARCHAR(50) UNIQUE NOT NULL,
    login                       VARCHAR(50) UNIQUE NOT NULL,
    display_name                VARCHAR(50) NOT NULL,
    profile_image_url           TEXT
);