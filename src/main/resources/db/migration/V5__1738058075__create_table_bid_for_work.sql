CREATE TABLE if not exists bids_for_work (
                               id SERIAL PRIMARY KEY,
                               resume TEXT,
                               passport TEXT,
                               bid_status VARCHAR(50),
                               name VARCHAR(255),
                               surname VARCHAR(255),
                               patronymic VARCHAR(255),
                               age INTEGER,
                               phone_number VARCHAR(20),
                               email VARCHAR(255)
);
