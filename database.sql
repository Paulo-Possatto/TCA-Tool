CREATE SCHEMA tca_tool;

USE tca_tool;

CREATE TABLE transformer_data(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    transformer_id VARCHAR(20) NOT NULL,
    location VARCHAR(150) NOT NULL,
    is_operating TINYINT NOT NULL,
    last_analysis DATE NOT NULL,
    pwr DECIMAL(10,2) NOT NULL
);

CREATE INDEX idx_transformer ON transformer_data (transformer_id);

CREATE TABLE transformer_analysis(
	id INT NOT NULL AUTO_INCREMENT,
    transformer_id VARCHAR(20) NOT NULL,
    sample_date DATE NOT NULL,
    hydrogen_gas INT NOT NULL,
    oxygen_gas INT NOT NULL,
    nitrogen_gas INT NOT NULL,
    carbon_monoxide_gas INT NOT NULL,
    methane_gas INT NOT NULL,
    carbon_dioxide_gas INT NOT NULL,
    ethylene_gas INT NOT NULL,
    ethane_gas INT NOT NULL,
    acetylene INT NOT NULL,
    oil_temperature DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_transformer FOREIGN KEY (transformer_id) REFERENCES transformer_data(transformer_id)
);