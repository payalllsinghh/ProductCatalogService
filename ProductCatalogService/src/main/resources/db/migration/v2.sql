ALTER TABLE test_model
    ADD number_field INT NULL;

ALTER TABLE test_model
    ADD text_field VARCHAR(255) NULL;

ALTER TABLE test_model
    MODIFY number_field INT NOT NULL;
