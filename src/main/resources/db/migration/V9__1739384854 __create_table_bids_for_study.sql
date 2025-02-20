CREATE TABLE if not exists bids_for_study (
                                id BIGSERIAL PRIMARY KEY,
                                bid_parent VARCHAR(255),
                                bid_status VARCHAR(255),
                                child_birth_certificate VARCHAR(255),
                                place_of_residence VARCHAR(255),
                                passport_parent VARCHAR(255),
                                name_parent VARCHAR(255),
                                surname_parent VARCHAR(255),
                                patronymic_parent VARCHAR(255),
                                phone_number_parent VARCHAR(255),
                                email_parent VARCHAR(255),
                                child_medical_certificate_copy VARCHAR(255),
                                child_medical_record VARCHAR(255),
                                age_child VARCHAR(255),
                                surname_child VARCHAR(255),
                                patronymic_child VARCHAR(255),
                                child_class VARCHAR(255)
);
