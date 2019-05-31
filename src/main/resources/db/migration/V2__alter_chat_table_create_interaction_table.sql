CREATE TABLE interaction(
    id INTEGER NOT NULL,
    category INTEGER,
    target_audience INTEGER,
    closed BOOLEAN DEFAULT FALSE,
    chat INTEGER NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(chat) REFERENCES chat(id)
);

create sequence interaction_id_seq start with 1 increment by 1 no minvalue no maxvalue cache 1;

DELETE FROM chat;

ALTER TABLE chat DROP COLUMN last_intent;
ALTER TABLE chat DROP COLUMN last_interaction;

