CREATE TABLE if not exists news (
    id BIGSERIAL PRIMARY KEY,
    author_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    image_name VARCHAR(255),
    image_type VARCHAR(255),
    image_date BYTEA,
    date TIMESTAMP NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);