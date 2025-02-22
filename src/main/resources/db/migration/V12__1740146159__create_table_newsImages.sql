CREATE TABLE IF NOT EXISTS news_images (
                                           news_id BIGINT NOT NULL,
                                           image VARCHAR(255) NOT NULL,
    CONSTRAINT fk_news_images FOREIGN KEY (news_id)
    REFERENCES news (id) ON DELETE CASCADE ON UPDATE CASCADE
);
