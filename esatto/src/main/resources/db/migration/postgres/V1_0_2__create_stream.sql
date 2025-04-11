CREATE TABLE STREAM
(
    id                      SERIAL PRIMARY KEY,
    twitch_stream_id        VARCHAR(50) UNIQUE NOT NULL,
    streamer_id             INTEGER NOT NULL REFERENCES streamer(id) ON DELETE CASCADE,
    game_id                 VARCHAR(50) REFERENCES game(twitch_game_id),
    title                   TEXT NOT NULL,
    viewer_count            INTEGER,
    started_at              TIMESTAMP NOT NULL,
    thumbnail_url           TEXT,
    language                TEXT
)

