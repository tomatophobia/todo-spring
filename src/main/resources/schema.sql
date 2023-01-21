CREATE TABLE IF NOT EXISTS todo (
    id BIGSERIAL PRIMARY KEY,
    content TEXT
);

INSERT INTO todo
VALUES (1, '밥 먹기'),
       (2, '공부하기'),
       (3, 'Play the piano');
