CREATE TABLE IF NOT EXISTS todo (
    id BIGSERIAL PRIMARY KEY,
    content TEXT
);

INSERT INTO todo (content)
VALUES ('밥 먹기'),
       ('공부하기'),
       ('Play the piano');
